package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public class Impossible implements CommandeEtat{
    private Etats etat = Etats.IMPOSSIBLE;
    @Override
    public boolean changeState(PlatChoisi aPlatChoisi){
        if(aPlatChoisi.getEtat().getEtat() == Etats.COMMANDE){
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
        return "État: Impossible";
    }
}
