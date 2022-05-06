package model.menufact.facture.exceptions;

/**
 * Classe d'exception pour les factures
 */
public class FactureException extends Exception{
    /**
     * Constructeur par défaut
     * @param message Message d'erreur
     */
    public FactureException(String message){
        super("FactureException: " + message);
   }
}
