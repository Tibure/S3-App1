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
    private Map<Ingredient, Integer> ingredients;
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
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @param ingredients Liste d'ingrédients
     */
    public void setIngredients(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @param proportion Proportion du plat enfant
     */
    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    /**
     * @param kcal Calories du plat
     */
    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    /**
     * @param chol Cholestérol du plat
     */
    public void setChol(double chol) {
        this.chol = chol;
    }

    /**
     * @param gras Gras du plat
     */
    public void setGras(double gras) {
        this.gras = gras;
    }

    /**
     * @param code Code du plat
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @param description Description du plat
     */
    public void setDescription(String description) {
        this.description = description;
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
