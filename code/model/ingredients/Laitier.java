package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 *
 * Classe enfant d'ingrédient pour gérer les produits laitiers
 * @author beae0601 bure1301
 */
public class Laitier extends Ingredient{
    /**
     * Constrcuteur avec paramètres
     * @param nom Le nom de l'ingrédient
     * @param description La description de l'ingrédient
     * @throws IngredientException Lance une exception si un des paramètres est non conforme
     */
    public Laitier(String nom, String description) throws IngredientException {
        super(nom, description);
        setTypeIngredient(TypeIngredient.LAITIER);
    }
}
