package vue;

import ingredients.Ingredient;
import inventaire.Inventaire;
import menufact.Client;
import menufact.Menu;
import menufact.facture.Facture;

import java.util.Scanner;

public class ConsoleVue {


    public static int choixUtilisateur(){
        System.out.println("Bienvenue dans MenuFact02!\n\n1. Afficher le menu\n2. Afficher la facture\n3. Afficher l'inventaire\n4. Afficher un ingrédient\n5. Afficher le client\n9. Quitter\nEntrez le numéro de l'option voulu!\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }

    public static void afficherMenu(Menu menu){
        System.out.println(menu.toString());
    }

    public static void afficherFacture(Facture facture){
        System.out.println(facture.toString());
    }

    public static void afficherInventaire(Inventaire inventaire){
        System.out.println(inventaire.toString());
    }

    public static void afficherIngredient(Ingredient ingredient){
        System.out.println(ingredient.toString());
    }

    public static void afficherClient(Client client){
        System.out.println(client.toString());

    }

    public static void afficherErreur(String erreur){
        System.out.println(erreur);
    }

}
