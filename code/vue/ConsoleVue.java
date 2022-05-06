package vue;

import model.ingredients.Ingredient;
import model.inventaire.Inventaire;
import model.menufact.Client;
import model.menufact.Menu;
import model.menufact.facture.Facture;

import java.util.Scanner;

/**
 * Classe qui contrôle les intéractions avec l'utilisateur
 */
public class ConsoleVue {

    /**
     * Méthode pour afficher le menu des options à l'utilisateur puis lui demander d'entrer son choix
     * @return Le choix de l'utilisateur
     */
    public static int choixUtilisateur(){
        System.out.println("Bienvenue dans MenuFact02!\n\n1. Afficher le menu\n2. Afficher la facture\n3. Afficher l'model.inventaire\n" +
                "4. Afficher un ingrédient\n5. Afficher le client\n6. Générer le menu\n7. Générer une facture\n8. Remplir l'model.inventaire\n" +
                "9. Quitter\nEntrez le numéro de l'option voulu!\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }

    /**
     *
     * @param menu Le menu à afficher
     */
    public static void afficherMenu(Menu menu){
        System.out.println(menu.toString());
    }

    /**
     *
     * @param facture La facture à afficher
     */
    public static void afficherFacture(Facture facture){
        System.out.println(facture.toString());
    }

    /**
     *
     * @param inventaire L'model.inventaire à afficher
     */
    public static void afficherInventaire(Inventaire inventaire){
        System.out.println(inventaire.toString());
    }

    /**
     *
     * @param ingredient L'ingrédient à afficher
     */
    public static void afficherIngredient(Ingredient ingredient){
        System.out.println(ingredient.toString());
    }

    /**
     *
     * @param client Le client à afficher
     */
    public static void afficherClient(Client client){
        System.out.println(client.toString());

    }

    /**
     *
     * @param erreur Le message d'erreur à afficher
     */
    public static void afficherErreur(String erreur){
        System.out.println(erreur);
    }

    /**
     *
     * @param message Le message à afficher
     */
    public static void afficherMessage(String message){
        System.out.println(message);
    }

}
