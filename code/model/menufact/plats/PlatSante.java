package model.menufact.plats;

/**
 * Classe qui contrôle les plats du menu santé
 */
public class PlatSante extends PlatAuMenu {
    /**
     * Valeur calorique d'un plat
     */
    private double kcal;
    /**
     * Quantité de cholestérol du plat
     */
    private double chol;
    /**
     * Quantité de gras du plat
     */
    private double gras;

    /**
     * Constructeur avec paramètres de la classe PlatSante
     * @param code Code du produit
     * @param description Description du plat
     * @param prix Prix total du plat
     * @param kcal Quantité de calories du plat
     * @param chol Quantité de cholestérol du plat
     * @param gras Quantité de gras du plat
     */
    public PlatSante(int code, String description, double prix, double kcal, double chol, double gras) {
        super(code, description, prix);
        this.kcal = kcal;
        this.chol = chol;
        this.gras = gras;
    }

    /**
     * Constructeur de base de la classe PlatSante
     */
    public PlatSante() {
    }

    /**
     *
     * @return Caractéristiques du plat santé
     */
    @Override
    public String toString() {
        return "model.menufact.plats.PlatSante{" +
                "kcal=" + kcal +
                ", chol=" + chol +
                ", gras=" + gras +
                "} " + super.toString();
    }

    /**
     *
     * @return La valeur calorique de l'objet
     */
    public double getKcal() {
        return kcal;
    }

    /**
     *
     * @return La valeur du cholestérol de l'objet
     */
    public double getChol() {
        return chol;
    }

    /**
     *
     * @return La valeur de gras de l'objet
     */
    public double getGras() {
        return gras;
    }
}
