package model.menufact.facture;

import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

/**
 * Classe enfant de commandeEtat pour gérer l'état Commandé
 * @author beae0601 bure1301
 */
public class Commande implements CommandeEtat {
    /**
     * À l'état commandé
     */
    private Etats etat = Etats.COMMANDE;

    /**
     * @param aPlatChoisi Plat que l'on désire changer d'état de préparation
     * @return Un booléean si le changement est réussi ou non
     */
    @Override
    public boolean changeState(PlatChoisi aPlatChoisi) {
        if(aPlatChoisi.getEtat() == null){
            aPlatChoisi.setEtat(this);
            return true;
        }
        return false;
    }

    /**
     * @return L'état actuel
     */
    @Override
    public Etats getEtat() {
        return etat;
    }

    /**
     * @return L'état commandé en texte
     */
    @Override
    public String toString(){
        return "Etat: Commande";
    }
}
