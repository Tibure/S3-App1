package menufact;

import menufact.facture.*;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatsException;

public class Chef {
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

    public PlatChoisi commanderPlat(PlatChoisi aPlat) throws PlatsException{
        aPlat.setEtat(new Commande());

        if(verifierSiPossible(aPlat)){
            preparerPlat(aPlat);
            terminerPlat(aPlat);
            servirPlat(aPlat);
            return aPlat;
        } else {
            //return null; ?
            throw new PlatsException("Impossible de continuer.");
        }
    }
    public boolean verifierSiPossible(PlatChoisi aPlat) throws PlatsException{
        //check if ingredients disponibles
        boolean verif = true;
        //if("pasAssez"){
        if(verif){
            aPlat.setEtat(new Impossible());
            verif = false;
        }
        return  verif;
    }




    @Override
    public String toString(){
        return  "Chef {" +
                "Nom du chef: " + nom +
                "}" ;
    }
}
