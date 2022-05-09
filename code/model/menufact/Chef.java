package model.menufact;

import model.ingredients.Ingredient;
import model.ingredients.IngredientInventaire;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import model.menufact.facture.*;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe de chef
 */
public class Chef implements  Subscriber{
    /**
     * Nom du chef
     */
    private String nom;
    /**
     * Instance d'un chef
     */
    private static Chef instanceChef;

    /**
     * Méthode qui crée un chef en s'assurant qu'il n'y en pas d'autre déjà présent
     * @return L'instance du chef
     */
    public static Chef getInstance(){
        if(instanceChef == null){
            instanceChef = new Chef();
        }
        return instanceChef;
    }

    /**
     * @return Le nom du chef
     */
    public String getNom() {
        return  nom;
    }

    /**
     * @param aNom Attribue un nom au chef
     */
    public void setNom(String aNom) {
        if(aNom != null) {
            this.nom = aNom;
        }
    }

    /**
     * Change l'état du plat pour l'état de préparation
     * @param aPlat Plat choisi par le client
     * @throws PlatsException Lance une exception si le changement d'état n'as pas pu être effectué
     */
    public void preparerPlat(PlatChoisi aPlat) throws PlatsException{
        if(!(new Preparation().changeState(aPlat)))
            throw new PlatsException("Impossible de passer à l'état préparé");

    }

    /**
     * Change l'état du plat pour l'état terminé
     * @param aPlat Plat choisi par le client
     * @throws PlatsException Lance une exception si le changement d'état n'as pas pu être effectué
     */
    public void terminerPlat(PlatChoisi aPlat) throws PlatsException{
        if(!(new Termine().changeState(aPlat)))
            throw new PlatsException("Impossible de passer à l'état terminé");
    }

    /**
     * Change l'état du plat pour l'état servi
     * @param aPlat Plat choisi par le client
     * @throws PlatsException Lance une exception si le changement d'état n'as pas pu être effectué
     */
    public void servirPlat(PlatChoisi aPlat) throws PlatsException {
        if(!(new Servi().changeState(aPlat)))
            throw new PlatsException("Impossible de passer à l'état servi");
    }

    /**
     * Change l'état du plat pour l'état impossible
     * @param aPlat Plat choisi par le client
     * @throws PlatsException Lance une exception si le changement d'état n'as pas pu être effectué
     */
    public void impossiblePlat(PlatChoisi aPlat) throws PlatsException{
        if(!(new Impossible().changeState(aPlat)))
            throw new PlatsException("Impossible de passer à l'état impossible");
    }

    /**
     * Méthode qui passe par tout les états de préparation d'un plat
     * @param aPlat Plat choisi par le client
     * @return aPlat retourne le plat avec les états changés
     * @throws PlatsException Lance une exception si jamais il est impossible de retourner un plat
     */
    public PlatChoisi commanderPlat(PlatChoisi aPlat) throws IngredientException, PlatsException {
        aPlat.setEtat(new Commande());

        if(verifierSiPossible(aPlat)){
            preparerPlat(aPlat);
            terminerPlat(aPlat);
            servirPlat(aPlat);
            return aPlat;
        } else {
            impossiblePlat(aPlat);
            throw new PlatsException("Impossible de continuer");
        }
    }

    /**
     * @param aPlat Plat choisi par le client
     * @return Un booléan représentant la possibilité ou non de réaliser un plat
     * @throws IngredientException Lance une exception si les ingrédients nécessaires pour la préparation d'un plat ne sont pas disponibles
     */
    public boolean verifierSiPossible(PlatChoisi aPlat){
        Map< Ingredient, Double> ingredients = aPlat.getPlat().getIngredients();
        Inventaire inventaire = Inventaire.getInstance();
        IIterator iterator = inventaire.createIterator();

        IngredientInventaire ingredientsInventaire = null;
        List<Ingredient> ingredientsList = ingredients.keySet().stream().toList();




        boolean verif = true;

        for(int i = 0; i < ingredientsList.size() && verif; i++) {
            iterator.setPosition(0);
            if(!iterator.hasNext())
                verif = false;
            while (iterator.hasNext()) {
                ingredientsInventaire = iterator.next();

                if(ingredientsInventaire.getIngredient().getNom().equals(ingredientsList.get(i).getNom())) {
                    verif = true;
                    if (ingredientsInventaire.getQuantite() >= ingredients.get(ingredientsList.get(i)).intValue()) {
                        try {
                            iterator.remove(ingredientsList.get(i).getNom(), ingredients.get(ingredientsList.get(i)).intValue());
                        } catch (IngredientException e) {}
                        break;
                    }
                    else {
                        verif = false;
                        break;
                    }
                }
                else
                    verif = false;
            }
        }
        return  verif;
    }

    /**
     * @return Les informations du chef en texte
     */
    @Override
    public String toString(){
        return  "Chef {" +
                "Nom du chef: " + nom +
                "}" ;
    }

    /**
     * Méthode qui demande au chef de commencer la préparation d'un plat lorsqu'il recoit une commande (observer)
     * @param p Plat choisi par le client
     * @throws IngredientException Lance une exception si les ingrédients ne sont pas disponibles
     * @throws PlatsException Lance une exception si le plat ne peut pas être changé d'état
     */
    @Override
    public void update(PlatChoisi p) throws IngredientException, PlatsException {
        commanderPlat(p);
    }
}
