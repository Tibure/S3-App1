package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

public interface CommandeEtat{
    public boolean changeState(PlatChoisi aPlatChoisi) throws PlatsException;

    public Etats getEtat();
}
