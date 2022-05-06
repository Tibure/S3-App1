package model.ingredients;

import model.ingredients.exceptions.IngredientException;

public class IngredientFactory {
    public Ingredient getIngredient(TypeIngredient aType) throws IngredientException {
        switch (aType){
            case FRUIT:
                return new Fruit();
            case LAITIER:
                return new Laitier();
            case VIANDE:
                return new Viande();
            case LEGUME:
                return new Legume();
            case EPICE:
                return new Epice();
            default:
                throw new IngredientException("Type d'ingrédient invalide.");
        }
    }
}
