package menufact.facture;

public class Servi implements CommandeEtat {
    private Etats etat = Etats.SERVI;
    @Override
    public void changeState(CommandeEtat state) {
        //groupe.setEtatGroupe(this);
        //dans groupe  -> setEtatGroupe ( etatGroupe = aEtatGroupe);
    }

    @Override
    public Etats getEtat() {
        return etat;
    }

    @Override
    public String toString(){
        return "Etat: Servi";
    }
}
