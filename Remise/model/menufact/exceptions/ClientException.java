package model.menufact.exceptions;

/**
 *
 * Classe d'exception pour la classe client
 * @author beae0601 bure1301
 */
public class ClientException extends Exception{
    /**
     * Constructeur par défaut
     * @param message Message d'erreur
     */
    public ClientException(String message){
        super("ClientException: " + message);
    }
}
