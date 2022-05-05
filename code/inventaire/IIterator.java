package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

public interface IIterator {
    public boolean hasNext();
    public IngredientInventaire next();

    public boolean insert(IngredientInventaire ingredient);

    public boolean remove(String nom, int quantite) throws IngredientException;
}
