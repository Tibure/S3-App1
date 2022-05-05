package menufact.facture;

public class Impossible implements CommandeEtat{
    @Override
    public void changeState(CommandeEtat state) {

    }

    @Override
    public String toString(){
        return "État: Impossible";
    }
}
