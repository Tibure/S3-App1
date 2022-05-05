package menufact.facture;

public class Preparation implements CommandeEtat{
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public String toString(){
        return "Etat: Préparation";
    }
}
