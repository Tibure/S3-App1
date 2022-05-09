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

import java.util.Map;
import java.util.Set;

public class Chef implements  Subscriber{
    private String nom;
    private static Chef instanceChef;

    public static Chef getInstance(){
        if(instanceChef == null){
            instanceChef = new Chef();
        }
        return instanceChef;
    }

    public String getNom() {
        return  nom;
    }

    public void setNom(String aNom) {
        if(aNom != null) {
            this.nom = aNom;
        }
    }

    public void preparerPlat(PlatChoisi aPlat) throws PlatsException{
        aPlat.setEtat(new Preparation());
    }

    public void terminerPlat(PlatChoisi aPlat) throws PlatsException{
        aPlat.setEtat(new Termine());
    }

    public void servirPlat(PlatChoisi aPlat) throws PlatsException {
        aPlat.setEtat(new Servi());
        //return aPlat;
    }

    public void impossiblePlat(PlatChoisi aPlat) throws PlatsException{
        aPlat.setEtat(new Impossible());
    }

    /**
     *
     * @param aPlat
     * @return aPlat modifie avec
     * @throws PlatsException
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
    public boolean verifierSiPossible(PlatChoisi aPlat) throws IngredientException {
        Map< Ingredient, Integer> ingredients = aPlat.getPlat().getIngredients();
        Inventaire inventaire = Inventaire.getInstance();
        IIterator iterator = inventaire.createIterator();

        IngredientInventaire ingredientNecessaire = null;
        Ingredient[] ingredientsTab = (Ingredient[]) ingredients.keySet().toArray();



        boolean verif = true;

        for(int i = 0; i < ingredientsTab.length && verif; i++) {
            while (iterator.hasNext()) {
                ingredientNecessaire = iterator.next();

                if(ingredientNecessaire.getIngredient().getNom().equals(ingredientsTab[i].getNom())) {
                    verif = true;
                    if (ingredientNecessaire.getQuantite() <= ingredients.get(ingredientsTab[i]).intValue()) {
                        iterator.remove(ingredientsTab[i].getNom(), ingredients.get(ingredientsTab[i]).intValue());
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
    @Override
    public String toString(){
        return  "Chef {" +
                "Nom du chef: " + nom +
                "}" ;
    }

    @Override
    public void update(PlatChoisi p) throws IngredientException, PlatsException {
        commanderPlat(p);
    }
}
