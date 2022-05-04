package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<IngredientInventaire> lesIngredients = new ArrayList<IngredientInventaire>();

    public void ajouter (IngredientInventaire ingredient)
    {
        lesIngredients.add(ingredient);
    }

}
