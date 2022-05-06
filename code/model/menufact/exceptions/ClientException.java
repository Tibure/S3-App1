package model.menufact.exceptions;

/**
 * Classe d'exception pour la classe client
 */
public class ClientException extends Exception{
    /**
     * Constrcuteur par défaut
     * @param message Message d'erreur
     */
    public ClientException(String message){
        super("ClientException: " + message);
    }
}
