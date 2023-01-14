package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;

public class AvailablePropertyController {

	@FXML
	private TilePane tilePane;

	@FXML
	private ScrollPane scrollPane;

	@FXML 
	private Button homePane;

	@FXML 
	private Button registerPane;

	@FXML 
	private Button propertyPane;

	@FXML 
	private BorderPane borderPane;

	@FXML
	private ScrollPane sp1; 

	int count = 0;
	private int nRows = 3;
	private int nCols = 3;

	private HouseList houseList;
	private RentedHouseList rentedHouseList;

	private static final double ELEMENT_SIZE = 400;
	private static final double GAP = ELEMENT_SIZE / 10;

	public void initialize() throws ClassNotFoundException, IOException {

		System.out.println("Available Property Stage");
		//		scrollPane = new ScrollPane();
		//		tilePane = new TilePane();
		tilePane.setPrefColumns(nCols);
		tilePane.setPrefRows(nRows);
		tilePane.setHgap(GAP);
		tilePane.setVgap(GAP);


		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			//houseList = DataHandler.readHouseList();
			houseList  = DataHandler.readHouseRentCSV();
			rentedHouseList = DataHandler.readRentedHouseList();

			createElements();
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
	}

	private void createElements() {

		System.out.println("Houses found => " + houseList.size());

		tilePane.getChildren().clear();
		LocalDate today = LocalDate.now();

		count = 0;
		for (;count < houseList.size();) {
			for (int i = 1 ; i <= 3 && count < houseList.size() ; i++, count++ ) {
				House h = houseList.get(count);
				if(!(h.getRentStatus())) {

					Label lbl= new Label();
					lbl.setPadding(new Insets(15, 15, 15, 15));
					//lbl.setPrefWidth(ELEMENT_SIZE);
					String s = "Room To Rent\n£"+h.getRent()+ " pcm\nBedrooms: "+h.getBedrooms()+"\nBathRooms: "+h.getBathrooms()+"\nPostCode: "+h.getPostcode();
					lbl.setText(s);
					lbl.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"),null,null)));
					tilePane.getChildren().add(lbl);

					lbl.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event arg0) {
							// TODO Auto-generated method stub
							System.out.println(h.getLatitude() + " : " + h.getLongitude());
							try {
								createNewScene(arg0,h);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}


					});			
				}
				else {
					for(RentedHouse rh : rentedHouseList) {
						if(rh.getHouse().getId()==h.getId() && rh.getEnddate().isBefore(today)) {
							h.setRentStatus(false);
							//Remove rentedHouse 
							Label lbl= new Label();
							lbl.setPadding(new Insets(15, 15, 15, 15));
							//lbl.setPrefWidth(ELEMENT_SIZE);
							String s = "Room To Rent\n£"+h.getRent()+ " pcm\nBedrooms: "+h.getBedrooms()+"\nBathRooms: "+h.getBathrooms()+"\nPostCode: "+h.getPostcode();
							lbl.setText(s);
							lbl.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"),null,null)));
							tilePane.getChildren().add(lbl);

							lbl.setOnMouseClicked(new EventHandler<Event>() {

								@Override
								public void handle(Event arg0) {
									// TODO Auto-generated method stub
									System.out.println(h.getLatitude() + " : " + h.getLongitude());
									try {
										createNewScene(arg0,h);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}


							});	
							
							break;
						}
					}
				}
			}
		}
	}

	public void createNewScene(Event e, House h) throws IOException {
		// TODO Auto-generated method stub


		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSinglePropertyToRent.fxml"));
		//BorderPane a = FXMLLoader.load(getClass().getResource("PropertyManagement.fxml")); 
		BorderPane a = loader.load();
		ViewSinglePropertyToRentController controller = loader.getController();
		System.out.println(h.toString());
		controller.initData(h);

		borderPane.setCenter(a);
		sp1.setVisible(false);
		Object x = borderPane.getTop();
		borderPane.setTop(null);


	}

}
