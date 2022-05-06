package model.menufact;

import model.menufact.exceptions.ClientException;

/**
 * Classe qui gère le client
 */
public class Client {
    /**
     * Identification du client
     */
    private int idClient;
    /**
     * Nom du client
     */
    private String nom;
    /**
     * Numéro de carte de crédit du client
     */
    private String numeroCarteCredit;

    /**
     * Constrcuteur avec paramètres
     * @param idClient Identification du client
     * @param nom Nom du client
     * @param numeroCarteCredit Numéro de carte de crédit du client
     * @throws ClientException Lance une exception si un des paramètres est non conforme
     */
    public Client(int idClient, String nom, String numeroCarteCredit) throws ClientException{
        setIdClient(idClient);
        setNom(nom);
        setNumeroCarteCredit(numeroCarteCredit);
    }

    /**
     *
     * @return L'identification du client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient L'identification du client
     * @throws ClientException Lance une exception si l'identification est négative
     */
    public void setIdClient(int idClient) throws ClientException{
        if(idClient >= 0)
            this.idClient = idClient;
        else
            throw new ClientException("L'identification du client ne peut pas être négative");
    }

    /**
     *
     * @return Le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom Le nom du client
     * @throws ClientException Lance une exception si le nom est vide
     */
    public void setNom(String nom) throws ClientException{
        if(nom.length() > 0)
            this.nom = nom;
        else
            throw new ClientException("Le nom du client ne peut pas être vide");
    }

    /**
     *
     * @return Le numéro de carte de crédit
     */
    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     *
     * @param numeroCarteCredit Le numéro de carte de crédit
     * @throws ClientException Lance une exception si le numéro est vide
     */
    public void setNumeroCarteCredit(String numeroCarteCredit) throws ClientException {
        if(numeroCarteCredit.length() > 0)
            this.numeroCarteCredit = numeroCarteCredit;
        else
            throw new ClientException("Le numéro de carte de crédit ne peut pas être vide");
    }

    /**
     *
     * @return Une String contenant les informations du client
     */
    @Override
    public String toString() {
        return "model.menufact.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                '}';
    }
}