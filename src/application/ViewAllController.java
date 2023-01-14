package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;

public class ViewAllController {

	@FXML
	private TilePane tilePane;

	@FXML
	private ScrollPane scrollPane;

	@FXML 
	private Button addProperty;

	@FXML 
	private TextField search;

	@FXML 
	private ComboBox<String> comboSort;

	@FXML 
	private BorderPane borderPane;

	@FXML
	private ScrollPane sp1; 

	int count = 0;
	private int nRows = 3;
	private int nCols = 3;

	private HouseList houseList;
	//private House h;

	private static final double ELEMENT_SIZE = 400;
	private static final double GAP = ELEMENT_SIZE / 10;

	public void initialize() throws ClassNotFoundException, IOException {

		System.out.println("Property Stage");
		//		scrollPane = new ScrollPane();
		//		tilePane = new TilePane();
		//comboSort.getItems().addAll("Price","Listing Date","No. of Bedrooms");
		tilePane.setPrefColumns(nCols);
		tilePane.setPrefRows(nRows);
		tilePane.setHgap(GAP);
		tilePane.setVgap(GAP);


		//houseList = DataHandler.readHouseList();
		initData();
	}

	public void initData() throws IOException {
		houseList = DataHandler.readHouseRentCSV();
		createElements(houseList);
	}

	private void createElements(HouseList houseList) {
		this.houseList = houseList;


		System.out.println("Houses found => " + houseList.size());

		tilePane.getChildren().clear();


		count = 0;
		System.out.println("Count :" +count);
		for (;count <houseList.size();) {
			for (int i = 1 ; i <= 3 && count < houseList.size() ; i++, count++ ) {
				House h = houseList.get(count);
				//System.out.println("After House creation");

				Label lbl= new Label();
				lbl.setPadding(new Insets(15, 15, 15, 15));
				//lbl.setPrefWidth(ELEMENT_SIZE);
				String s = "Room To Rent\n£"+h.getRent()+ " pcm\nBedrooms: "+h.getBedrooms()+"\nBathRooms: "+h.getBathrooms()+"\nPostCode: "+h.getPostcode();
				lbl.setText(s);
				lbl.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"),null,null)));
				tilePane.getChildren().add(lbl);
				//				System.out.println("Height : " + tilePane.getHeight());
				//				System.out.println("Width : " + tilePane.getWidth());
				//				System.out.println("R : " + tilePane.getPrefRows());
				//				System.out.println("C : " + tilePane.getPrefColumns());
				//				System.out.println("Child(s) : " + tilePane.getChildren() + " : " + tilePane.getChildren().size());
				//				System.out.println("--------------------------------------------------\n");


				lbl.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						System.out.println(h.getLatitude() + " : " + h.getLongitude());
						System.out.println("handle House Id:"+ h.getId());
						try {
							createNewScene(arg0,h);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}


				});			
			}
		}
	}

	public void createNewScene(Event e, House h) throws IOException {
		// TODO Auto-generated method stub

		System.out.println("View All House Id: "+ h.getId());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSingleProperty.fxml"));
		//BorderPane a = FXMLLoader.load(getClass().getResource("PropertyManagement.fxml")); 
		BorderPane a = loader.load();
		ViewSinglePropertyController controller = loader.getController();
		System.out.println(h.toString());
		controller.initData(h,houseList);

		borderPane.setCenter(a);
		sp1.setVisible(false);
		borderPane.setTop(null);


	}

	public void addPropertyListener() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UpdateHouse.fxml"));
			DialogPane updatePane = loader.load();

			UpdateHouseController updateController = loader.getController();
			//House h = updateController.addHouse();

			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			dialog.setDialogPane(updatePane);
			dialog.setTitle("Add Property");
			//			

			while(true)
			{

				Optional<ButtonType> clickedButton = dialog.showAndWait();
				if(clickedButton.get() == ButtonType.OK) {

					House h = updateController.addHouse();

					if(h != null) {
						houseList.addHouse(h);
						DataHandler.writeHouseRentCSV(houseList);
						createElements(houseList);
						return;
					}
					else {
						System.out.println("Error");
						continue;
					}
				}
				
				return;
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



}
