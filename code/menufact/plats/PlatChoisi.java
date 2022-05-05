package menufact.plats;

import menufact.facture.CommandeEtat;
import menufact.plats.PlatAuMenu;
import menufact.plats.exceptions.PlatsException;

public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;
    private CommandeEtat etat;

    public PlatChoisi(PlatAuMenu plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public PlatAuMenu getPlat() {
        return plat;
    }

    public CommandeEtat getEtat() {
        return etat;
    }

    public void setEtat(CommandeEtat aEtat) throws PlatsException{
        if(etat == null) {
            etat = aEtat;
        } else {
            throw new PlatsException("Impossible de faire le changement d'état");
        }
    }

}
