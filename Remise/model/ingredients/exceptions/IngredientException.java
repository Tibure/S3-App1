package model.ingredients.exceptions;

/**
 *
 * Classe d'exception pour les ingrédients
 * @author beae0601 bure1301
 */
public class IngredientException extends Exception{
    /**
     * Constructeru par défaut
     * @param message Message d'erreur
     */
    public IngredientException(String message){
        super("IngredientException: " + message);
    }
}
