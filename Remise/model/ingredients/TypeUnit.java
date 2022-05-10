package model.ingredients;

/**
 *
 * Enum des unité de mesure possible pour les ingrédient
 * @author beae0601 bure1301
 */
public enum TypeUnit {
    ML("mL"), L("L"), TASSE("tasses"), G("g"), KG("kg"), UNIT("unités");
    /**
     * Symbole de l'unité de mesure
     */
    public final String label;

    /**
     *
     * @param label Le symbole de l'unité de mesure
     */
    private TypeUnit(String label){
        this.label = label;
    }
}
