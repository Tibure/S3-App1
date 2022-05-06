package menufact.facture;

public class Termine implements CommandeEtat{
    private Etats etat = Etats.TERMINE;
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "Etat: Termine";
    }
}
