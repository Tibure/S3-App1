package model.menufact.facture;

public interface CommandeEtat{
    public void changeState(CommandeEtat state);

    public Etats getEtat();
}
