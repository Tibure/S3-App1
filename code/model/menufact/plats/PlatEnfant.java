package model.menufact.plats;
import model.menufact.plats.exceptions.PlatsException;

public class PlatEnfant extends PlatAuMenu{
    private double proportion;

    public PlatEnfant() {
    }

    public PlatEnfant(int code, String description, double prix, double proportion) throws PlatsException {
        super(code, description, prix);
        //this.proportion = proportion;
        setProportion(proportion);
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double aProportion) throws PlatsException {
        if(aProportion > 1){
            throw new PlatsException("Un plat pour enfant ne peut pas être plus grand qu'un plat du menu adulte.");
        }
        if(aProportion < 0){
            throw new PlatsException("Un plat pour enfant ne peut pas être une valeur négative.");
        }

        this.proportion = aProportion;
    }

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
