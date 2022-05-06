package model.menufact.plats;

/**
 * Classe qui contrôle les plats du menu
 */
public class PlatAuMenu {
    /**
     * Code du plat
     */
    private int code;
    /**
     * Description du plat
     */
    private String description;
    /**
     * Prix total du plat
     */
    private double prix;

    /**
     * Constructeur avec paramètres de la classe PlatAuMenu
     * @param code Code du plat
     * @param description Description du plat
     * @param prix Prix total du plat
     */
    public PlatAuMenu(int code, String description, double prix) {
        this.code = code;
        this.description = description;
        this.prix = prix;
    }

    /**
     * Constructeur de base
     */
    public PlatAuMenu() {
    }

    /**
     *
     * @return Les informations du plat
     */
    @Override
    public String toString() {
        return "model.menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                "}\n";
    }

    /**
     *
     * @return Le code du plat
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code Applique un nouveau code à l'objet
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return La description du plat
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description Ajoute une description à un objet
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return Le prix d'un plat
     */
    public double getPrix() {
        return prix;
    }

    /**
     *
     * @param prix Ajoute un prix à un plat (objet)
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }
}
