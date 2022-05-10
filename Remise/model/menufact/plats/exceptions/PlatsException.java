package model.menufact.plats.exceptions;

public class PlatsException extends Exception {

    public PlatsException(String message){
        super("PlatsException: " + message);
    }
}
