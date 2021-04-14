package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface ClientText extends Subject {

    InputChat sendMsg(String text);
    List<InputChat> getChat();
    InputUser username(String txt);
    List<InputUser> getUser();

    List<flights> getflights();

    List<seat> getSeat();

    List<Myflightlist> getflightlist();
}


