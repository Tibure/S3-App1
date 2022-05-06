package menufact.facture;

public class Commande implements CommandeEtat {
    private Etats etat = Etats.COMMANDE;
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "Etat: Commande";
    }
}
