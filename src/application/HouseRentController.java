package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class HouseRentController implements Initializable{

	private House selectedHouse;
	private CustomerList cList;
	private ArrayList<String> availableCustomers;
	private String selectedCustomer = "";
	private Customer customer;
	private HouseList hList;
	private RentedHouse rentedHouse;
	private RentedHouseList rhList;

	@FXML
	private ComboBox<String> customerList;

	@FXML
	private DatePicker startDate;


	@FXML
	private Button submit;
	
	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private Label label3;

	@FXML
	private DatePicker endDate;

	@FXML
	private TextArea displayArea;


	public void initData(House house)
	{
		selectedHouse = house;
		System.out.println(selectedHouse.toString());
		label1.setText(selectedHouse.getFurnishingStatus()+" "+selectedHouse.getType()+" with \n"+selectedHouse.getBedrooms()+" Bedrooms and "+selectedHouse.getBathrooms()+" Bathrooms in "+selectedHouse.getPostcode());
		if(selectedHouse.isGarden()) {
			label2.setText("Comes with Garden");
		}
		else {
			label2.setText("Doesn't come with Garden");
		}
		label3.setText("Rent: £"+selectedHouse.getRent());
		customerList.getItems().addAll( availableCustomers);
		


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Inside view Single Property to Rent");
		try {
			cList = DataHandler.readCustomerList();
			availableCustomers = new ArrayList();
			for(Customer c: cList) {
				//if(!(c.isRented())) {
				availableCustomers.add(c.getCustomerId()+" - "+c.getName());
				//}
			}
			hList = DataHandler.readHouseRentCSV(); // read existing book list from file
			System.out.println(hList.getHouses().size());
			//DataHandler.writeToFile(hList);
			
			
			rhList = DataHandler.readRentedHouseList(); // read existing hire list from file
			//BookHire.setHireCount(hList.getHires().size());
			RentedHouse.setRentCount(rhList.getRented().size());
			submit.setVisible(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // read existing student list from file

	}

	public void ComboBoxListener() {		
		//		 customerList.valueProperty().addListener(new ChangeListener<String>() {
		//		       
		//
		//				@Override
		//				public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		//					// TODO Auto-generated method stub
		//					selectedCustomer = newValue;
		//					System.out.println(selectedCustomer);
		//					
		//					
		//				}    
		//		    });
		selectedCustomer = customerList.getValue();
		System.out.println(customerList.getValue());
		submit.setVisible(true);

	}
	public void submitListener() throws IOException {
		String[] splitted = selectedCustomer.split(" - ");
		int selectedCustomerId = Integer.parseInt(splitted[0]);
		LocalDate sd = startDate.getValue();
		LocalDate ed = endDate.getValue();
		boolean b = true;
		LocalDate today = LocalDate.now();
		if(!((today.isEqual(sd)) || today.isBefore(sd))) {
			b = false;
			System.out.println("Start Date should be today's or after today's date");
		}
		if(ed.isBefore(sd)) {
			b = false;
			System.out.println("End date should be after start date");
		}
		if(!b) {
			return;
		}
		customer = new Customer(selectedCustomerId);
		
		for(Customer c: cList) {
			if(c.getCustomerId() == selectedCustomerId) {
				
				customer.setBdate(c.getBdate());
				customer.setEmail(c.getEmail());
				customer.setName(c.getName());
				customer.setGender(c.getGender());
				customer.setPhoneNumber(c.getPhoneNumber());	
			}
		}
		
		//this.selectedHouse.setRentStatus(true);
		rentedHouse = new RentedHouse(selectedHouse, customer, sd, ed);
		
		displayArea.setText(rentedHouse.printInvoice());
		//System.out.println("Invoice generating soon!");
		rhList.addRentedHouse(rentedHouse);
		hList.get(selectedHouse.getId()-1).setRentStatus(true);
		System.out.println(rhList.getRented().size());
		System.out.println(selectedHouse.getId());
		DataHandler.writeToFile(rhList);
		//DataHandler.writeToFile(hList);
		DataHandler.writeHouseRentCSV(hList);
//		hList.addHire(hire);
//		DataHandler.writeToFile(hList);
//	    DataHandler.writeToFile(bList);//update book list in the file
//		hireButton.setVisible(false);	
	}
}