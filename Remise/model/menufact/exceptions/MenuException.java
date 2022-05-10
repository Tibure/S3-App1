package model.menufact.exceptions;

/**
 *
 * Classe d'exception pour le menu
 * @author beae0601 bure1301
 */
public class MenuException extends Exception{
    /**
     * Constructeur par défaut
     * @param message Message d'erreur
     */
    public MenuException(String message){
        super("MenuException: " + message);
    }
}

