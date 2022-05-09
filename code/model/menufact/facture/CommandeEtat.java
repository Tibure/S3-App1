package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

/**
 * Interface qui permet de changer les états d'un plat
 */
public interface CommandeEtat{
    /**
     * @param aPlatChoisi Plat que l'on désire changer d'état de préparation
     * @return Un booléan qui indique si le changement d'état est réussi ou non
     * @throws PlatsException Si le plat ne permet pas le changmement vers le nouveal état
     */
    public boolean changeState(PlatChoisi aPlatChoisi);

    /**
     * @return L'état actuel d'un plat
     */
    public Etats getEtat();
}
