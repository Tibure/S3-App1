package controller;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import inventaire.IIterator;
import inventaire.Inventaire;
import menufact.Client;
import menufact.Menu;
import menufact.facture.Facture;
import vue.ConsoleVue;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe qui controle la vue
 */
public class ConsoleController {
    /**
     *
     */
    Facture facture;
    /**
     *
     */
    Menu menu;
    /**
     *
     */
    Inventaire inventaire;
    /**
     *
     */
    Client client;


    //TODO : Mettre les bon paramètres pour les constructeur ou demander à l'utilisateur
    /**
     * Constructeur par défaut qui initialise tous les attributs
     */
    public ConsoleController(){
        facture = new Facture("DESCRIPTION");
        menu = new Menu("DESCRIPTION");
        inventaire = new Inventaire();
        client = new Client(1, "NOM", "NUMERO_CARTE");
    }

    /**
     * Méthode qui fait afficher le menu des options à l'écran puis réalise la bonne tâche
     * selon l'option choisi
     */
    public void choixMenu(){
        int choix;

        do{
            choix = ConsoleVue.choixUtilisateur();
        }while(!(choix > 0 && choix <= 9));

        switch (choix){
            case 1:
                afficherMenu();
                break;
            case 2:
                afficherFacture();
                break;
            case 3:
                afficherInventaire();
                break;
            case 4:
                ConsoleVue.afficherMessage("Entrez le nom de l'ingrédient");
                Scanner scanner = new Scanner(System.in);
                afficherIngredient(scanner.nextLine());
                break;
            case 5:
                afficherClient();
                break;
            case 6:
                genereMenu();
                break;
            case 7:
                genereFacture();
                break;
            case 8:
                fillInventaire();
                break;
            default:
                System.exit(1);


        }
    }

    /**
     *
     */
    private void afficherMenu(){
        ConsoleVue.afficherMenu(menu);
    }

    /**
     *
     */
    private void afficherFacture(){
        ConsoleVue.afficherFacture(facture);
    }

    /**
     *
     */
    private void afficherInventaire(){
        ConsoleVue.afficherInventaire(inventaire);
    }

    /**
     *
     * @param nom Nom de L'ingrédient à afficher
     */
    private void afficherIngredient(String nom){
        Ingredient ingredient = null;
        IIterator iterator = inventaire.createIterator();
        while(iterator.hasNext()){
            ingredient = iterator.next().getIngredient();
            if(ingredient.getNom().equals(nom))
                break;
        }
            if (ingredient != null) {
                ConsoleVue.afficherIngredient(ingredient);
            }
            else
                ConsoleVue.afficherErreur("Il n'y a pas de " + nom + " dans l'inventaire");
    }

    /**
     *
     */
    private void afficherClient(){
        ConsoleVue.afficherClient(client);
    }
    //TODO
    /**
     * Méthode pour générer le menu
     */
    private void genereMenu(){ConsoleVue.afficherMessage("TODO");}
    //TODO
    /**
     * Méthode pour générer une facture aléatoire
     */
    private void genereFacture(){ConsoleVue.afficherMessage("TODO");}

    //TODO
    /**
     * Méthode pour remplir l'inventaire avec des quantités aléatoires
     */
    private void fillInventaire(){ConsoleVue.afficherMessage("TODO");}


    //TODO: Try catch pour afficher les différentes erreurs
    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        while(true) {
            controller.choixMenu();
        }
    }
}
