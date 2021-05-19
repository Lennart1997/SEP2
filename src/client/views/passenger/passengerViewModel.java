package client.views.passenger;

import client.model.ClientModel;
import client.model.SaveInfo;
import javafx.application.Platform;
import javafx.beans.property.*;
import shared.transferobjects.*;
import shared.util.utils;

import java.beans.PropertyChangeEvent;

public class passengerViewModel {

    private ClientModel clientModel;

    //passenger
    private StringProperty FirstName;
    private StringProperty LastName;
    private StringProperty TelNumber;
    private StringProperty Email;

    //shoppingcart
    private StringProperty FlightName;
    private StringProperty departure;
    private StringProperty arrival;
    private StringProperty seatProperty;
    private StringProperty ClassType;
    private StringProperty price;

    //error
    private StringProperty error;

    public passengerViewModel(ClientModel clientModel) {
        this.clientModel = clientModel;
        FirstName = new SimpleStringProperty();
        LastName = new SimpleStringProperty();
        TelNumber = new SimpleStringProperty();
        Email = new SimpleStringProperty();
        FlightName = new SimpleStringProperty();
        departure = new SimpleStringProperty();
        arrival = new SimpleStringProperty();
        seatProperty = new SimpleStringProperty();
        ClassType = new SimpleStringProperty();
        price = new SimpleStringProperty();
        error = new SimpleStringProperty();

       clientModel.addListener(utils.NEWFLIGHT, this::onNewTicket);
       clientModel.addListener(utils.NEWSEAT, this::onNewTicket);
    }


    public void SetShoppingCart() {

        Flights flights = SaveInfo.getInstance().getFlights();
        Seat seat = SaveInfo.getInstance().getSeat();

        clientModel.readFlightsFromShoppingCart(flights.flightName,flights.getDepartures(),flights.getArrivals());
        clientModel.readSeatFromShoppingCart(seat.seatNumber,seat.classType);
        clientModel.readPrice(flights.price);

        Platform.runLater(() -> {
            FlightName.setValue(flights.flightName);
            departure.setValue(flights.getDepartures());
            arrival.setValue(flights.getArrivals());
            seatProperty.setValue(seat.seatNumber);
            ClassType.setValue(seat.classType);
            price.setValue(String.valueOf(flights.price));

        });
    }


    public void getPassengerInformation() {

            if (FirstName.getValue() != null && !"".equals(FirstName.getValue()) && LastName.getValue() != null && !"".equals(LastName.getValue()) && TelNumber.getValue() != null && !"".equals(TelNumber.getValue())  && Email.getValue() != null && !"".equals(Email.getValue()) && Email.getValue().contains("@")) {

                String FirstName = this.FirstName.getValue();
                String LastName = this.LastName.getValue();
                String TelNumber = this.TelNumber.getValue();
                String Email = this.Email.getValue();
                SaveInfo.getInstance().setPassenger(clientModel.Createpassernger(FirstName,LastName,TelNumber,Email));

            }

    }

    public boolean validatePassengerInformation(){

        if (FirstName.getValue() == null ) {
            error.set("FirstName cannot be empty");
            return false;
        }
         if (LastName.getValue() == null ) {
            error.set("LastName cannot be empty");
            return false;
        }
         if (TelNumber.getValue() == null ) {
            error.set("TelNumber cannot be empty");
            return false;
        }

         if (TelNumber.getValue().length()> 8 && TelNumber.getValue().length() < 8 ) {
            error.set("TelNumber must be 8 digits");
            return false;
        }
         if (Email.getValue() == null) {
            error.set("Email cannot be empty ");
            return false;
        }
         if (!Email.getValue().contains("@")) {
            error.set("Email must contain '@' ");
            return false;
        }

         else {

            return true;
        }

    }

    public void clearFields() {
        FirstName.setValue("");
        LastName.setValue("");
        TelNumber.setValue("");
        Email.setValue("");
        error.setValue("");

    }


    public String getFlightName() {
        return FlightName.get();
    }

    public StringProperty flightNameProperty() {
        return FlightName;
    }

    public String getDeparture() {
        return departure.get();
    }

    public StringProperty departureProperty() {
        return departure;
    }

    public String getArrival() {
        return arrival.get();
    }

    public StringProperty arrivalProperty() {
        return arrival;
    }

    public String getSeatProperty() {
        return seatProperty.get();
    }

    public StringProperty seatPropertyProperty() {
        return seatProperty;
    }

    public String getClassType() {
        return ClassType.get();
    }

    public StringProperty classTypeProperty() {
        return ClassType;
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public String getFirstName()
    {
        return FirstName.get();
    }

    public StringProperty firstNameProperty()
    {
        return FirstName;
    }

    public String getLastName()
    {
        return LastName.get();
    }

    public StringProperty lastNameProperty()
    {
        return LastName;
    }

    public String getTelNumber()
    {
        return TelNumber.get();
    }

    public StringProperty telNumberProperty()
    {
        return TelNumber;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    private void onNewTicket(PropertyChangeEvent event) {
        SetShoppingCart();
    }


}
