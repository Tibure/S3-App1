package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public class Impossible implements CommandeEtat{
    private Etats etat = Etats.IMPOSSIBLE;
    @Override
    public boolean changeState(PlatChoisi aPlatChoisi) throws PlatsException {
        aPlatChoisi.setEtat(this);
        return true;
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
