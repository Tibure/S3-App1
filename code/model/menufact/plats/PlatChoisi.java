package model.menufact.plats;

import model.menufact.facture.CommandeEtat;
import model.menufact.plats.exceptions.PlatsException;

/**
 * Classe qui contrôles les plats choisis
 */
public class PlatChoisi {
    /**
     * Plat du menu qui est choisi (objet)
     */
    private PlatAuMenu plat;
    /**
     * Quantité du plat désiré
     */
    private int quantite;
    /**
     * État de la préparation du plat
     */
    private CommandeEtat etat;

    /**
     * Constructeur avec paramètres de PlatChoisi
     * @param plat Plat choisi envoyé en commande
     * @param quantite Quantité de plat
     */
    public PlatChoisi(PlatAuMenu plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
    }

    /**
     *
     * @return Les informations du plat choisi
     */
    @Override
    public String toString() {
        return "model.menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    /**
     *
     * @return La quantité du plat choisi
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite Applique une quantité au plat choisi
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     *
     * @return Le plat choisi
     */
    public PlatAuMenu getPlat() {
        return plat;
    }

    /**
     *
     * @return L'état de préparation du plat en cuisine
     */
    public CommandeEtat getEtat() {
        return etat;
    }

    /**
     *
     * @param aEtat État que le plat est actuellement
     * @throws PlatsException Lance une exception si le changement d'état est impossible
     */
    public void setEtat(CommandeEtat aEtat) throws PlatsException{
        if(etat == null) {
            this.etat = aEtat;
        }else if(!etat.changeState(this)) {
            throw new PlatsException("Impossible de faire le changement d'état");
        }
    }
}
