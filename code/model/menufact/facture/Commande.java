package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public class Commande implements CommandeEtat {
    private Etats etat = Etats.COMMANDE;
    @Override
    public boolean changeState(PlatChoisi aPlatChoisi) throws PlatsException {
        if(aPlatChoisi.getEtat().getEtat() == null){
            aPlatChoisi.setEtat(this);
            return true;
        }
        return false;
    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "Etat: Commande";
    }
}
