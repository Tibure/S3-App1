package vue;

import ingredients.Ingredient;
import inventaire.Inventaire;
import menufact.Client;
import menufact.Menu;
import menufact.facture.Facture;

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
        System.out.println("Bienvenue dans MenuFact02!\n\n1. Afficher le menu\n2. Afficher la facture\n3. Afficher l'inventaire\n4. Afficher un ingrédient\n5. Afficher le client\n9. Quitter\nEntrez le numéro de l'option voulu!\n");
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
     * @param inventaire L'inventaire à afficher
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

}
