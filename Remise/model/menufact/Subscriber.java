package model.menufact;

import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

/**
 * Classe qui permet d'appeler une notification (observer) au chef lorsqu'un plat est recu
 * @author beae0601 bure1301
 */
public interface Subscriber {
    public void update(PlatChoisi p) throws IngredientException, PlatsException;
}
