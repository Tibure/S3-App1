package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

/**
 * Interface pour itérérer sur l'inventaire
 */
public interface IIterator {
    /**
     *
     * @return Vrai si réussi, faux sinon
     */
    public boolean hasNext();

    /**
     *
     * @return L'élément courant
     */
    public IngredientInventaire next();

    /**
     *
     * @param ingredient L'élément à insérer
     * @return Vrai si réussi, faux sinon
     */
    public boolean insert(IngredientInventaire ingredient);

    /**
     *
     * @param nom Nom de l'ingrédient
     * @param quantite Quantité à retirer
     * @return Vrai si réussi, faux sinon
     * @throws IngredientException Lance une exception si la quantité est invalide
     */
    public boolean remove(String nom, int quantite) throws IngredientException;
}
