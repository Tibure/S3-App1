package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 * Classe combinant un ingrédient avec sa quantité et ses unitées
 */
public class IngredientInventaire {
    /**
     * Ingrédient
     */
    private Ingredient ingredient;
    /**
     * Quantité en model.inventaire
     */
    private int quantite;
    /**
     * Unité de mesure de l'ingrédient
     */
    private TypeUnit unit;

    /**
     * Constructeur prenant tous les attributs
     * @param ingredient Ingrédient
     * @param quantite  Quantité de l'ingrédient
     * @param unit  Unitée de mesure de l'ingrédient
     * @throws IngredientException Lance une exception si la quantité n'est pas valide
     */
    public IngredientInventaire(Ingredient ingredient, int quantite, TypeUnit unit) throws IngredientException{
        this.ingredient = ingredient;
        setQuantite(quantite);
        setUnit(unit);
    }

    /**
     *
     * @return La quantité
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite La quantité à modifier
     * @throws IngredientException Lance une qexception si la quantité n'est pas valide
     */
    public void setQuantite(int quantite) throws IngredientException{

        if (quantite < 0)
            throw new IngredientException("Il n'est pas possible d'avoir une quantité negative");
        else
            this.quantite = quantite;
    }

    /**
     *
     * @return L'unité de mesure
     */
    public TypeUnit getUnit() {return unit;}

    /**
     *
     * @param unit L'unité de mesure à modifier
     */
    public void setUnit(TypeUnit unit)
    {
            this.unit = unit;
    }

    /**
     *
     * @return L'ingrédient
     */
    public Ingredient getIngredient() {return ingredient;}

    /**
     *
     * @param ingredient L'ingrédient à modifier
     */
    public void setIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    /**
     *
     * @return Une String contenant l'ingrédient, la quantité et l'unité de mesure
     */
    @Override
    public String toString() {
        return "model.ingredients.IngredientInventaire{" +
                "ingredient=" + ingredient +
                ", quantité=" + quantite +
                ", unité=" + unit.label +
                '}';
    }
}
