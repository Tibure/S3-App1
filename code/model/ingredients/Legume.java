package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 * Classe enfant d'ingrédient pour gérer les légumes
 */
public class Legume extends Ingredient{
    /**
     * Constrcuteur avec paramètres
     * @param nom Le nom de l'ingrédient
     * @param description La description de l'ingrédient
     * @throws IngredientException Lance une exception si un des paramètres est non conforme
     */
    public Legume(String nom, String description) throws IngredientException {
        super(nom, description);
        setTypeIngredient(TypeIngredient.LEGUME);
    }
}
