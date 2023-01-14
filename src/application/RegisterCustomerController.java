package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RegisterCustomerController {
	@FXML
	private TextField name;

	@FXML
	private TextField email;

	@FXML
	private TextField phoneNumber;

	@FXML
	private DatePicker dateOfBirth;

	@FXML
	private RadioButton male;

	@FXML
	private RadioButton female;

	@FXML
	private ToggleGroup gender;

	@FXML 
	private Button register;
	
	@FXML 
	private Label message;


	private Customer customer;
	private CustomerList customerList;

	public void initialize() throws ClassNotFoundException, IOException {
		customerList = DataHandler.readCustomerList();
		System.out.println("Welcome Register Customer Page");
		message.setVisible(false);
	}

	public void registerListener() throws IOException {
		//System.out.println("Button CLicked");
		boolean b = true;
		name.setStyle("-fx-border-color:none;");
		email.setStyle("-fx-border-color:none;");
		phoneNumber.setStyle("-fx-border-color:none;");
		for(Customer c: customerList) {
			if(c.getEmail().toLowerCase().equals(email.getText().toLowerCase())) {
				email.setStyle("-fx-border-color:red;");
				b = false;
				message.setText("Email already registered. Please use a different email");
				message.setStyle("-fx-text-fill: red;");
				message.setVisible(true);
			}
		}
		if(!(isValidName(name.getText()))) {
			name.setStyle("-fx-border-color:red;");
			b = false;
		}

		if(!(isValidEmail(email.getText()))) {
			email.setStyle("-fx-border-color:red;");
			b = false;
		}


		if(!(isValidPhoneNumber(phoneNumber.getText()))) {
			phoneNumber.setStyle("-fx-border-color:red;");
			b = false;
		}
		if(!b) 
			return;
		customer = new Customer(customerList.getCustomers().size()+1);
		System.out.println("Customer Id: "+ customer.getCustomerId());
		customer.setName(name.getText());
		customer.setEmail(email.getText());
		customer.setPhoneNumber(phoneNumber.getText());
		customer.setGender((male.isSelected())?"Male":"Female");
		LocalDate date = dateOfBirth.getValue();
		//System.out.println("Birth date selected: "+ date);
		customer.setBdate(date);
		customerList.addCustomer(customer);

		DataHandler.writeToFile(customerList);
		message.setText("Registered Successfully");
		message.setStyle("-fx-text-fill: green;");
		message.setVisible(true);
		


	}

	private boolean isValidPhoneNumber(String text) {
		// TODO Auto-generated method stub
		String expression = "^\\d{10}$"; 
		return text.matches(expression);
	}

	private boolean isValidEmail(String text) {
		// TODO Auto-generated method stub
		//System.out.println(text.length());
		if(text.isEmpty()) {
			return false;
		}
		String expression = "^(.+)@(.+)$"; 
		return text.matches(expression);
	}

	private boolean isValidName(String text) {
		// TODO Auto-generated method stub

		String expression = "^[a-zA-Z\\s]+";
		//System.out.println(text.matches(expression));
		return text.matches(expression);
	}



}
