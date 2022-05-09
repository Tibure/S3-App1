package model.menufact.plats;
import model.ingredients.Ingredient;
import model.menufact.plats.exceptions.PlatsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui contrôle les plats du menu enfant
 */
public class PlatEnfant extends PlatAuMenu{
    /**
     * Proportion du plat enfant par rapport au menu adulte
     */
    private double proportion;

    /**
     * Constructeur de base de la classe PlatEnfant
     */
    public PlatEnfant() {
    }

    /**
     * Constructeur avec paramètres de la classe PlatEnfant
     * @param code
     * @param description Description du plat
     * @param prix Prix total du plat
     * @param proportion Proportion du plat par rapport au menu régulier
     * @throws PlatsException Lance une exception lorsque la proportion n'est pas conforme
     */
    public PlatEnfant(int code, String description, double prix, double proportion, Map<Ingredient, Double> ingredients) throws PlatsException {
        setProportion(proportion);
        Map<Ingredient, Double> ingredientsKid = new HashMap<>();
        for(Ingredient ing: ingredients.keySet()){
            ingredientsKid.put(ing, ingredients.get(ing)*proportion);
        }
        this.setCode(code);
        this.setDescription(description);
        this.setPrix(prix*proportion);
        this.setIngredient(ingredientsKid);

    }

    /**
     *
     * @return la proportion de l'objet PlatEnfant
     */
    public double getProportion() {
        return proportion;
    }

    /**
     * Méthode pour ajouter une proportion à un plat du menu enfant
     * @param aProportion proportion à appliquer au plat
     * @throws PlatsException Lance une exception lorsque la proportion n'est pas conforme (plus grande que le menu adulte, valeur négative)
     */
    public void setProportion(double aProportion) throws PlatsException {
        if(aProportion > 1){
            throw new PlatsException("Un plat pour enfant ne peut pas être plus grand qu'un plat du menu adulte.");
        }
        if(aProportion < 0){
            throw new PlatsException("Un plat pour enfant ne peut pas être une valeur négative.");
        }

        this.proportion = aProportion;
    }

    /**
     *
     * @return une string contenant les informations du PlatEnfant
     */
    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
