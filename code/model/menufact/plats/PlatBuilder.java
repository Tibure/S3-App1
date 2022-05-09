package model.menufact.plats;

import model.ingredients.Ingredient;
import model.menufact.plats.exceptions.PlatsException;

import java.util.Map;

/**
 * Classe qui crée le type de plats selon les paramètres reçus
 */
public class PlatBuilder {
    /**
     * Code du plat
     */
    private int code;
    /**
     * Description du plat
     */
    private String description;
    /**
     * Prix du plat
     */
    private double prix;
    /**
     * Liste d'ingrédients composant le plat
     */
    private Map<Ingredient, Double> ingredients;
    /**
     * Proportion d'un plat du menu enfant par rapport à un plat adulte
     */
    private double proportion;
    /**
     * Quantité de calories du plat
     */
    private double kcal;
    /**
     * Quantité de cholestérol du plat
     */
    private double chol;
    /**
     * Quantité de gras du plat
     */
    private double gras;

    /**
     * @param prix Prix du plat
     *
     */
    public PlatBuilder setPrix(double prix) {
        this.prix = prix;
        return this;
    }

    /**
     * @param ingredients Liste d'ingrédients
     */
    public PlatBuilder setIngredients(Map<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    /**
     * @param proportion Proportion du plat enfant
     */
    public PlatBuilder setProportion(double proportion) {
        this.proportion = proportion;
        return this;
    }

    /**
     * @param kcal Calories du plat
     */
    public PlatBuilder setKcal(double kcal) {
        this.kcal = kcal;
        return this;
    }

    /**
     * @param chol Cholestérol du plat
     */
    public PlatBuilder setChol(double chol) {
        this.chol = chol;
        return this;
    }

    /**
     * @param gras Gras du plat
     */
    public PlatBuilder setGras(double gras) {
        this.gras = gras;
        return this;
    }

    /**
     * @param code Code du plat
     */
    public PlatBuilder setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * @param description Description du plat
     */
    public PlatBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return Le nouveau plat associé aux paramètres envoyés
     * @throws PlatsException Lance une erreur si les paramètres du builder ne permettent pas de créer un plat
     */
    public PlatAuMenu getResult() throws PlatsException {

        if (code != 0 && description != null && prix != 0) {
            if (proportion == 0 && kcal == 0 && chol == 0 && gras == 0) {
                return new PlatAuMenu(code, description, prix, ingredients);
            } else if (proportion != 0 && kcal == 0 && chol == 0 && gras == 0) {
                return new PlatEnfant(code, description, prix, proportion, ingredients);
            } else if (proportion == 0 & kcal != 0 && chol != 0 && gras != 0) {
                return new PlatSante(code, description, prix, kcal, chol, gras, ingredients);
            }
        }
        throw new PlatsException("Les paramètres choisis ne correspondent pas à un plat");
    }
}
