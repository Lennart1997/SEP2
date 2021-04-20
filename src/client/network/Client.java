package client.network;

import shared.transferobjects.*;
import shared.util.Subject;

import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Subject {

    InputChat sendMsg(String str);
    List<InputChat> getChat();
    InputUser username(String txt);
    List<InputUser> getUser();
    List<flights> getflights();
    List<seat> getSeat();
    //List<passenger> getPassenger();
    List<Myflightlist> getflightlist();
    passenger passernger(String FirstName, String LastName, String TelNumber);


    //---------------------------------------------------------------
  /*  void getUpdate(flights flights);
    void UpdateSeats();*/
    //---------------------------------------------------------------
    void createTicket(Myflightlist myflightlist);


    void startClient();

}
