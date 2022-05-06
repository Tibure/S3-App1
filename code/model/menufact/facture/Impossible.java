package model.menufact.facture;

public class Impossible implements CommandeEtat{
    private Etats etat = Etats.IMPOSSIBLE;
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "État: Impossible";
    }
}
