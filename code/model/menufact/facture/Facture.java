package model.menufact.facture;

import model.ingredients.exceptions.IngredientException;
import model.menufact.Chef;
import model.menufact.Client;
import model.menufact.Subscriber;
import model.menufact.facture.exceptions.FactureException;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Une facture du systeme Menufact
 * @author Domingo Palao Munoz
 * @version 1.0
 */
public class Facture {
    private Date date;
    private String description;
    private FactureEtat etat;
    private ArrayList<PlatChoisi> platchoisi = new ArrayList<PlatChoisi>();
    private int courant;
    private Client client;
    private Chef chef;

    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();


    /**********************Constantes ************/
    private final double TPS = 0.05;
    private final double TVQ = 0.09975;

    /**
     *
     * @param client le client de la facture
     */
    public void associerClient (Client client)
    {
        this.client = client;
    }

    public void associerChef(Chef aChef) {
        this.chef = aChef;
        subscribers.add(aChef);
    }

    /**
     * Calcul du sous total de la facture
     * @return le sous total
     */
    public double sousTotal()
    {
        double soustotal=0;
         for (PlatChoisi p : platchoisi)
             soustotal += p.getQuantite() * p.getPlat().getPrix();
        return soustotal;
    }

    /**
     *
     * @return le total de la facture
     */
    public double total(){
        return sousTotal()+tps()+tvq();
    }

    /**
     *
     * @return la valeur de la TPS
     */
    private double tps(){
        return TPS*sousTotal();
    }

    /**
     *
     * @return la valeur de la TVQ
     */
    private  double tvq(){
        return TVQ*sousTotal();
    }

    /**
     * Permet de chager l'état de la facture à PAYEE
     */
    public void payer()
    {
       etat = FactureEtat.PAYEE;
    }
    /**
     * Permet de chager l'état de la facture à FERMEE
     */
    public void fermer()
    {
       etat = FactureEtat.FERMEE;
    }

    /**
     * Permet de changer l'état de la facture à OUVERTE
     * @throws FactureException en cas que la facture soit PAYEE
     */
    public void ouvrir() throws FactureException
    {
        if (etat == FactureEtat.PAYEE)
            throw new FactureException("La facture ne peut pas être reouverte.");
        else
            etat = FactureEtat.OUVERTE;
    }

    /**
     *
     * @return l'état de la facture
     */
    public FactureEtat getEtat()
    {
        return etat;
    }

    /**
     *
     * @param description la description de la Facture
     */
    public Facture(String description) {
        date = new Date();
        etat = FactureEtat.OUVERTE;
        courant = -1;
        this.description = description;
    }

    /**
     *
     * @param p un plat choisi
     * @throws FactureException Seulement si la facture est OUVERTE
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException, PlatsException, IngredientException {
        if (etat == FactureEtat.OUVERTE) {

            notifySubscribers(p);

            platchoisi.add(p);
        }
        else
            throw new FactureException("On peut ajouter un plat seulement sur une facture OUVERTE.");
    }

    /**
     * Méthode pour avertir les abonnés qu'un nouveau plat a été ajouter à la facture
     * @param p Plat à envoyer aux abonnés
     * @throws IngredientException Lance une exception si les ingrédients nécessaires ne sont pas dans l'inventaire
     * @throws PlatsException Lance une exeception si un changement d'état illégal arrive
     */
    private void notifySubscribers(PlatChoisi p) throws IngredientException, PlatsException {
        for(Subscriber sub: subscribers){
            sub.update(p);
        }
    }

    /**
     *
     * @return le contenu de la facture en chaîne de caracteres
     */
    @Override
    public String toString() {
        return "model.menufact.facture.Facture{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", platchoisi=" + platchoisi +
                ", courant=" + courant +
                ", client=" + client +
                ", Sous-total=" + sousTotal() +
                ", TPS=" + TPS*sousTotal() +
                ", TVQ=" + TVQ*sousTotal() +
                ", Total=" + total()+
                '}';
    }

    /**
     *
     * @return une chaîne de caractères avec la facture à imprimer
     */
    public String genererFacture()
    {
        String lesPlats = new String();
        String factureGenere = new String();

        int i =1;


        factureGenere =   "Facture generee.\n" +
                          "Date:" + date + "\n" +
                          "Description: " + description + "\n" +
                          "Client:" + client.getNom() + "\n" +
                          "Les plats commandes:" + "\n" + lesPlats;

        factureGenere += "Seq   Plat         Prix   Quantite\n";
        for (PlatChoisi plat : platchoisi)
        {
            factureGenere +=  i + "     " + plat.getPlat().getDescription() +  "  " + plat.getPlat().getPrix() +  "      " + plat.getQuantite() + "\n";
            i++;
        }

        factureGenere += "          TPS:               " + tps() + "\n";
        factureGenere += "          TVQ:               " + tvq() + "\n";
        factureGenere += "          Le total est de:   " + total() + "\n";

        return factureGenere;
    }
}
