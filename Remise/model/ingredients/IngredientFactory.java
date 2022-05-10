package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 *
 * Classe pour construire des ingrédients précis
 * @author beae0601 bure1301
 */
public class IngredientFactory {
    /**
     * Méthode pour construire n'importe quel type d'ingrédient
     * @param aType Type d'ingrédient à construire
     * @param nom Nom de l'ingrédient
     * @param description Description de l'ingrédient
     * @return L'ingrédient construit
     * @throws IngredientException Lance une exception si un des paramètres n'est pas conforme
     */
    public Ingredient getIngredient(TypeIngredient aType, String nom, String description) throws IngredientException {
        switch (aType){
            case FRUIT:
                return new Fruit(nom, description);
            case LAITIER:
                return new Laitier(nom, description);
            case VIANDE:
                return new Viande(nom, description);
            case LEGUME:
                return new Legume(nom, description);
            case EPICE:
                return new Epice(nom, description);
            default:
                return null;
        }
    }
}
