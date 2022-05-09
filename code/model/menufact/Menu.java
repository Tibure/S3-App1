package model.menufact;

import model.menufact.exceptions.MenuException;
import model.menufact.plats.PlatAuMenu;

import java.util.ArrayList;

/**
 * Classe qui gère le menu
 * @author beae0601 bure1301
 */
public class Menu {
    /**
     * Description du menu
     */
    private String description;
    /**
     * Indice du plat courant du menu
     */
    private int courant;
    /**
     * Liste des plats du menu
     */
    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    /**
     * Constructeur avec paramètre
     * @param description Description du menu
     */
    public Menu(String description) {
        courant = -1;
        this.description = description;
    }

    /**
     *
     * @param p Plat à ajouter au menu
     */
    public void ajoute (PlatAuMenu p)
    {
        plat.add(p);
        courant++;
    }

    /**
     * Modifie l'indice du plat courant
     * @param i L'indice à assigner au courant
     */
    public void setPosition(int i)
    {
        if(i >= 0 && i < plat.size())
            courant = i;
    }

    /**
     *
     * @return Le plat courant
     */
    public PlatAuMenu platCourant()
    {
        if(courant >= 0 && courant <= plat.size())
            return plat.get(courant);
        else
            return null;
    }

    /**
     *
     * @throws MenuException Lance une exception si on est déjà au dernier plat
     */
    public void positionSuivante() throws MenuException
    {
        if (courant+1 >= plat.size())
            throw new MenuException("On depasse le nombre maximale de plats.");
        else
            courant++;
    }

    /**
     *
     * @throws MenuException Lance une exception si on est déjà au premier plat
     */
    public void positionPrecedente() throws MenuException
    {
        if (courant-1 < 0)
            throw new MenuException("On depasse le nombre minimale de plats");
        else
            courant--;
    }

    /**
     *
     * @return Une String contenant toutes les informations du menu
     */
    @Override
    public String toString() {
        return "model.menufact.Menu{" +
                "description=" + description +
                ", courant=" + courant +
                ", plat=" + "\n" + plat +
                '}';
    }
}
