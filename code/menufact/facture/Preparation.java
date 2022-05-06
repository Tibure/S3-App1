package menufact.facture;

public class Preparation implements CommandeEtat{
    private Etats etat = Etats.PREPARATION;
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "Etat: Préparation";
    }
}
