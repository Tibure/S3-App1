package menufact.facture;

public class Commande implements CommandeEtat {

    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public String toString(){
        return "Etat: Commande";
    }
}
