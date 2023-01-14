package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ViewSinglePropertyController  implements Initializable{

	private House selectedHouse;

	@FXML
	private Label type;

	@FXML
	private Label bedrooms;

	@FXML
	private Label bathrooms;

	@FXML
	private Label postcode;

	@FXML
	private Label size;

	@FXML
	private Label rent;

	@FXML
	private Label furnishing;

	@FXML
	private Label garden;

	@FXML
	private Label listed;

	@FXML
	private Label message;

	@FXML
	private Label latitude;

	@FXML
	private Label longitude;

	@FXML
	private Label distanceLabel;

	@FXML
	private Label distanceValM;

	@FXML
	private Label distanceValK;


	@FXML
	private Button update;


	@FXML
	private ComboBox<String> comboPlaces;


	@FXML
	private Button back;

	private HouseList houseList;

	private PlaceList placeList;
	private PlaceOfInterest placeOfInterest;
	private ArrayList<String> places;
	private String selectedPlace = "";
	private static final DecimalFormat df = new DecimalFormat("0.00");

	public void initData(House house, HouseList hList)
	{
		selectedHouse = house;
		//System.out.println(selectedHouse.toString());
		//type.textProperty().bindBidirectional(null);
		type.setText(selectedHouse.getType());
		postcode.setText(selectedHouse.getPostcode());
		bedrooms.setText(Integer.toString(selectedHouse.getBedrooms())+" Bedrooms");
		bathrooms.setText(Integer.toString(selectedHouse.getBathrooms())+" Bathrooms");
		size.setText(Integer.toString(selectedHouse.getSize()));
		furnishing.setText(selectedHouse.getFurnishingStatus());
		garden.setText(selectedHouse.isGarden()?"Yes":"No");
		rent.setText("£"+Integer.toString(selectedHouse.getRent())+ " pcm");
		listed.setText(selectedHouse.getListed());
		latitude.setText(Double.toString(selectedHouse.getLatitude()));
		longitude.setText(Double.toString(selectedHouse.getLongitude()));
		message.setVisible(false);
		distanceLabel.setVisible(false);
		distanceValM.setVisible(false);
		distanceValK.setVisible(false);
		houseList = hList;
		comboPlaces.getItems().addAll( places);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Inside view Single Property");
		try {
			placeList = DataHandler.readPlaceCSV();
			places = new ArrayList();
			for(PlaceOfInterest p: placeList) {
				//if(!(c.isRented())) {
				places.add(p.getPlace());
				//}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ComboBoxListener() {		

		selectedPlace = comboPlaces.getValue();
		System.out.println(comboPlaces.getValue());
		//for loop in places and calculate distance
		for(PlaceOfInterest p: placeList) {
			if(p.getPlace().equals(selectedPlace)) {
				String s = ViewSinglePropertyController.distance(p.getLatitude(), p.getLongitude(), selectedHouse.getLatitude(), selectedHouse.getLongitude());
				distanceLabel.setText(selectedPlace);
				String strArray[] = s.split(",");
				
				distanceValK.setText(strArray[0]+" Kilometers");
				distanceValM.setText(strArray[1]+" Miles");
				distanceLabel.setVisible(true);
				distanceValM.setVisible(true);
				distanceValK.setVisible(true);
			}
		}
		//submit.setVisible(true);

	}

	public void updateListener() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UpdateHouse.fxml"));
			DialogPane updatePane = loader.load();

			UpdateHouseController updateController = loader.getController();
			updateController.setHouse(selectedHouse);

			Dialog<ButtonType> dialog = new Dialog();
			dialog.setDialogPane(updatePane);
			dialog.setTitle("Update Property");

			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if(clickedButton.get() == ButtonType.OK) {
				House h;
				h = updateController.getHouse();
				System.out.println("House Id getHouse : "+ h.getId());
				type.setText(h.getType());
				postcode.setText(h.getPostcode());
				bedrooms.setText(Integer.toString(h.getBedrooms())+" Bedrooms");
				bathrooms.setText(Integer.toString(h.getBathrooms())+" Bathrooms");
				size.setText(Integer.toString(h.getSize()));
				furnishing.setText(h.getFurnishingStatus());
				garden.setText(h.isGarden()?"Yes":"No");
				rent.setText("£"+Integer.toString(h.getRent())+ " pcm");
				listed.setText(h.getListed());


				houseList.get(h.getId()-1).setType(h.getType());
				houseList.get(h.getId()-1).setPostcode(h.getPostcode());
				houseList.get(h.getId()-1).setBedrooms(h.getBathrooms());
				houseList.get(h.getId()-1).setBathrooms(h.getBathrooms());
				houseList.get(h.getId()-1).setSize(h.getSize());
				houseList.get(h.getId()-1).setFurnishingStatus(h.getFurnishingStatus());
				houseList.get(h.getId()-1).setGarden(h.isGarden());
				houseList.get(h.getId()-1).setRent(h.getRent());
				DataHandler.writeHouseRentCSV(houseList);
				initData(h,houseList);

				message.setText("Updated Successfully");
				message.setVisible(true);

			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static String distance(double lat1, double lon1, double lat2, double lon2) {
		String str="";
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return "0";
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			//			if (unit.equals("K")) {
			dist = dist * 1.609344;
			str+=df.format(dist);
			//			} else if (unit.equals("N")) {
			dist = dist * 0.8684;
			str+=","+df.format(dist);
			//}
			System.out.println("Distance: "+str);
			return str;
		}
	}

}
