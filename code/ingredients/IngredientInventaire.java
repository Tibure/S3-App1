package ingredients;

import ingredients.exceptions.IngredientException;

public class IngredientInventaire {
    private Ingredient ingredient;
    private int quantite;
    private String unit;

    public IngredientInventaire(Ingredient ingredient, int quantite, String unit) throws IngredientException{
        this.ingredient = ingredient;
        setQuantite(quantite);
        setUnit(unit);
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws IngredientException{

        if (quantite < 0)
            throw new IngredientException("Il n'est pas possible d'avoir une quantité negative");
        else
            this.quantite = quantite;
    }

    public String getUnit() {return unit;}

    public void setUnit(String unit) throws IngredientException
    {
        if(!unit.isEmpty())
            this.unit = unit;
        else
            throw new IngredientException("Il n'est possible d'avoir une unité nulle");
    }

    public Ingredient getIngredient() {return ingredient;}

    public void setIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "ingredients.IngredientInventaire{" +
                "ingredient=" + ingredient +
                ", quantité=" + quantite +
                ", unité=" + unit +
                '}';
    }
}
