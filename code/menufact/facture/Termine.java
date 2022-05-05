package menufact.facture;

public class Termine implements CommandeEtat{

    @Override
    public void changeState(CommandeEtat state) {

    }
    @Override
    public String toString(){
        return "Etat: Termine";
    }
}
