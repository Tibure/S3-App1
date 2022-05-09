package model.menufact;

import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public interface Subscriber {
    public void update(PlatChoisi p) throws IngredientException, PlatsException;
}
