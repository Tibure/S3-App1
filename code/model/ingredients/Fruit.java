package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 *
 * Classe enfant d'ingrédient pour gérer les fruits
 * @author beae0601 bure1301
 */
public class Fruit extends Ingredient{
    /**
     * Constrcuteur avec paramètres
     * @param nom Le nom de l'ingrédient
     * @param description La description de l'ingrédient
     * @throws IngredientException Lance une exception si un des paramètres est non conforme
     */
    public Fruit(String nom, String description) throws IngredientException {
        super(nom, description);
        setTypeIngredient(TypeIngredient.FRUIT);
    }
}
