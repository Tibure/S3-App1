package model.ingredients.exceptions;

/**
 * Classe d'exception pour les ingrédients
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
