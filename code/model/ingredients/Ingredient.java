package model.ingredients;

import model.ingredients.exceptions.IngredientException;

/**
 * Classe parent qui represente les ingrédients
 */
public class Ingredient {
    /**
     * Nom de l'ingrédient
     */
    private String nom;
    /**
     * Description de l'ingrédient
     */
    private String description;
    /**
     * Type de l'ingrédient
     */
    private TypeIngredient typeIngredient;

    /**
     * Constructeur avec paramètres
     * @param nom Nom de l'ingrédient
     * @param description Description de l'ingrédient
     * @throws IngredientException Lance une exception si un des paramètres n'est pas conforme
     */
    public Ingredient(String nom, String description) throws IngredientException{
        setNom(nom);
        setDescription(description);
    }

    /**
     *
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom Le nom
     * @throws IngredientException Lance une exception si le nom est vide
     */
    public void setNom(String nom) throws IngredientException{
        if(nom.length() > 0)
            this.nom = nom;
        else
            throw new IngredientException("Le nom de l'ingrédient ne peut pas être vide");
    }

    /**
     *
     * @return La description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description La description
     * @throws IngredientException Lance une exception si la description est vide
     */
    public void setDescription(String description) throws IngredientException{
        if(description.length() > 0)
            this.description = description;
        else
            throw new IngredientException("La description de l'ingrédient ne peut pas être vide");
    }

    /**
     *
     * @return Le type d'ingrédient
     */
    public TypeIngredient getTypeIngredient() {
        return typeIngredient;
    }

    /**
     *
     * @param typeIngredient Le type d'ingrédient
     */
    public void setTypeIngredient(TypeIngredient typeIngredient) {
        this.typeIngredient = typeIngredient;
    }

    /**
     *
     * @return Une String contenant les informations sur l'ingrédient
     */
    @Override
    public String toString(){
        return "model.ingredients.Ingredient{" +
                "nom=" + nom +
                ", description=" + description +
                ", type=" + typeIngredient.name() +
                '}';

    }
}
