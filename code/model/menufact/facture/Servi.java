package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public class Servi implements CommandeEtat {
    private Etats etat = Etats.SERVI;
    @Override
    public boolean changeState(PlatChoisi aPlatChoisi) throws PlatsException {
        if(aPlatChoisi.getEtat().getEtat() == Etats.TERMINE){
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
        return "Etat: Servi";
    }
}
