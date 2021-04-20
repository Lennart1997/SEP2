package client.model;

import client.network.Client;
import shared.transferobjects.*;
import shared.util.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ClientTextManager implements ClientText {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;

    public ClientTextManager(Client client) {
        this.client = client;
        client.startClient();
        client.addListener(utils.NEWCHAT, this::onNewInputChat);
        client.addListener(utils.NEWUSER, this::onNewInputUser);
        client.addListener(utils.NEWPASSENGER, this::onNewPassenger);

    }

    private void onNewPassenger(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }


    private void onNewInputChat(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    private void onNewInputUser(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    @Override
    public InputChat sendMsg(String text) {
        return client.sendMsg(text);
    }

    @Override
    public List<InputChat> getChat() {
        return client.getChat();
    }

    @Override
    public InputUser username(String txt) {
        return client.username(txt);
    }

    @Override
    public List<InputUser> getUser() {
       return client.getUser();
    }

    @Override
    public List<flights> getflights() {
        return client.getflights();
    }

    @Override
    public List<seat> getSeat() {
            return client.getSeat();
    }

    /*@Override
    public List<passenger> getPassenger() {
        return client.getPassenger();
    }
*/
    @Override
    public List<Myflightlist> getflightlist() {
        return client.getflightlist();
    }

    @Override public passenger passernger(String FirstName, String LastName,
        String TelNumber)
    {
        return client.passernger(FirstName, LastName, TelNumber);
    }

    @Override
    public void createTicket(Myflightlist myflightlist) {





        client.createTicket(myflightlist);

    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}


