package ui;

import java.io.IOException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Flight;
import model.Screen;
import model.Time;

public class FlightsScreenController {
	
	private Screen screen;
	
    @FXML
    private MenuButton searchBy;

    @FXML
    private TableView<Flight> flightsList;

    @FXML
    private TextField flightToSearch;

    @FXML
    private TextField numberOfFlights;

	@FXML
    public void initialize() {
    	
    }
    
    @SuppressWarnings("unchecked")
	public void refreshTable() {
    	flightsList.getColumns().clear();
    	ObservableList<Flight> data = FXCollections.observableArrayList();
    	data.addAll(screen.getFlights());
        
    	TableColumn<Flight, Date> dates = new TableColumn<Flight, Date>("Date");
        dates.setMinWidth(100);
        dates.setCellValueFactory(new PropertyValueFactory<>("date"));
    	
        TableColumn<Flight, Time> departureTimes = new TableColumn<Flight, Time>("Time");
        departureTimes.setMinWidth(100);
        departureTimes.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        
        TableColumn<Flight, String> airlines = new TableColumn<Flight, String>("Airline");
        airlines.setMinWidth(100);
        airlines.setCellValueFactory(new PropertyValueFactory<>("airline"));
        
        TableColumn<Flight, Integer> codes = new TableColumn<Flight, Integer>("Flight");
        codes.setMinWidth(100);
        codes.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        TableColumn<Flight, String> destinations = new TableColumn<Flight, String>("Destination");
        destinations.setMinWidth(100);
        destinations.setCellValueFactory(new PropertyValueFactory<>("destination"));
        
        TableColumn<Flight, Integer> gates = new TableColumn<Flight, Integer>("Gate");
        gates.setMinWidth(100);
        gates.setCellValueFactory(new PropertyValueFactory<>("gate"));
        
        flightsList.setItems(data);
        flightsList.getColumns().addAll(dates, departureTimes, airlines, codes, destinations, gates);
    }
    
    public void refreshAfterSort() {
    	flightsList.getItems().clear();
    	ObservableList<Flight> data = FXCollections.observableArrayList();
    	data.addAll(screen.getFlights());
    	flightsList.setItems(data);
    	flightsList.refresh();
    }
    
    @FXML
    void generateRandomFlights(ActionEvent event) {
    	try {
    		int numberOfFlights = Integer.parseInt(this.numberOfFlights.getText());
    		screen = new Screen(numberOfFlights);
    		refreshTable();
    	} catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Illegal value, please enter a positive integer.");
    		alert.show();
    		alert.setOnCloseRequest(new EventHandler<DialogEvent>(){
				@Override
				public void handle(DialogEvent arg0) {
					numberOfFlights.setText("");
				}
    		});
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	numberOfFlights.setText("");
    }

	@FXML
    void search(ActionEvent event) {
		int index = -1;
		try {
			if(searchBy.getUserData().equals("airline")) {
	    		index = screen.searchFlightByAirline(flightToSearch.getText());    		
	    	} else if(searchBy.getUserData().equals("code")) {	
	    		index = screen.searchFlightByCode(Integer.parseInt(flightToSearch.getText()));
	    	} else if(searchBy.getUserData().equals("date")) {
	    		index = screen.searchFlightByDate(flightToSearch.getText());
	    	} else if(searchBy.getUserData().equals("time")) {
	    		index = screen.searchFlightByTime(flightToSearch.getText());
	    	} else if(searchBy.getUserData().equals("gate")) {
	    		index = screen.searchFlightByGate(Integer.parseInt(flightToSearch.getText()));
	    	} else if(searchBy.getUserData().equals("destination")) {
	    		index = screen.searchFlightByDestination(flightToSearch.getText());
	    	}
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Illegal input value, please select a criteria and provide a correct input");
    		alert.show();
		}
		if(index > -1) {
			showFoundFlight(index);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Flight with the specified info was not found, please select a criteria and provide a correct input");
    		alert.show();
		}
		flightToSearch.setText("");
		searchBy.setUserData("");
    }
    
    @SuppressWarnings("unchecked")
	public void showFoundFlight(int index) {
    	Stage stage = new Stage();
    	Scene scene = new Scene(new Group());
    	stage.setTitle("Flight");
        stage.setWidth(620);
        stage.setHeight(150);
        stage.setResizable(false);
        Label label = new Label("Found flight:");
        label.setFont(new Font("Arial", 20));
        TableView<Flight> tv = new TableView<Flight>();
        ObservableList<Flight> data = FXCollections.observableArrayList();
    	data.add(screen.getFlights()[index]);
        
    	TableColumn<Flight, Date> dates = new TableColumn<Flight, Date>("Date");
        dates.setMinWidth(100);
        dates.setCellValueFactory(new PropertyValueFactory<>("date"));
    	
        TableColumn<Flight, Time> departureTimes = new TableColumn<Flight, Time>("Time");
        departureTimes.setMinWidth(100);
        departureTimes.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        
        TableColumn<Flight, String> airlines = new TableColumn<Flight, String>("Airline");
        airlines.setMinWidth(100);
        airlines.setCellValueFactory(new PropertyValueFactory<>("airline"));
        
        TableColumn<Flight, Integer> codes = new TableColumn<Flight, Integer>("Flight");
        codes.setMinWidth(100);
        codes.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        TableColumn<Flight, String> destinations = new TableColumn<Flight, String>("Destination");
        destinations.setMinWidth(100);
        destinations.setCellValueFactory(new PropertyValueFactory<>("destination"));
        
        TableColumn<Flight, Integer> gates = new TableColumn<Flight, Integer>("Gate");
        gates.setMinWidth(100);
        gates.setCellValueFactory(new PropertyValueFactory<>("gate"));
        
        tv.setItems(data);
        tv.getColumns().addAll(dates, departureTimes, airlines, codes, destinations, gates);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, tv);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchByAirline(ActionEvent event) {
    	searchBy.setUserData("airline");
    }

    @FXML
    void searchByDate(ActionEvent event) {
    	searchBy.setUserData("date");
    }

    @FXML
    void searchByDestination(ActionEvent event) {
    	searchBy.setUserData("destination");
    }

    @FXML
    void searchByFlightCode(ActionEvent event) {
    	searchBy.setUserData("code");
    }

    @FXML
    void searchByGate(ActionEvent event) {
    	searchBy.setUserData("gate");
    }

    @FXML
    void searchByTime(ActionEvent event) {
    	searchBy.setUserData("time");
    }


    @FXML
    void sortByDate(ActionEvent event) {
    	screen.sortByDate();
    	refreshAfterSort();
    }
    
    @FXML
    void sortByAirline(ActionEvent event) {
    	screen.sortByAirline();
    	refreshAfterSort();
    }

    @FXML
    void sortByDestination(ActionEvent event) {
    	screen.sortByDestination();
    	refreshAfterSort();
    }

    @FXML
    void sortByFlightCode(ActionEvent event) {
    	screen.sortByCode();
    	refreshAfterSort();
    }

    @FXML
    void sortByGate(ActionEvent event) {
    	screen.sortByGate();
    	refreshAfterSort();
    }

    @FXML
    void sortByTime(ActionEvent event) {
    	screen.sortByDepartureTime();
    	refreshAfterSort();
    }

}
