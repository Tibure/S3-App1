package controller;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IInventaire;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import model.menufact.Chef;
import model.menufact.Client;
import model.menufact.Menu;
import model.menufact.exceptions.ClientException;
import model.menufact.facture.Facture;
import model.menufact.facture.exceptions.FactureException;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;
import vue.ConsoleVue;

import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

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

    private static final Map<String, TypeUnit> fruits = Map.ofEntries(entry("Pomme", TypeUnit.UNIT), entry("Poire", TypeUnit.UNIT), entry("Pêche", TypeUnit.UNIT),
            entry("Ananas", TypeUnit.UNIT), entry("Mangue", TypeUnit.UNIT), entry("Raisin", TypeUnit.G), entry("Kiwi", TypeUnit.UNIT), entry("Banane", TypeUnit.UNIT),
            entry("Fraise", TypeUnit.G), entry("Framboise", TypeUnit.G), entry("Bleuet", TypeUnit.G));

    private static final Map<String, TypeUnit> legumes = Map.ofEntries(entry("Patate", TypeUnit.UNIT), entry("Carotte", TypeUnit.UNIT), entry("Tomate", TypeUnit.UNIT),
            entry("Laitue", TypeUnit.UNIT), entry("Oignon", TypeUnit.UNIT), entry("Concombre", TypeUnit.UNIT), entry("Poivron", TypeUnit.UNIT), entry("Navet", TypeUnit.UNIT),
            entry("Céleri", TypeUnit.UNIT), entry("Choux", TypeUnit.UNIT), entry("Courge", TypeUnit.UNIT));

    private static final Map<String, TypeUnit> epices = Map.ofEntries(entry("Sel", TypeUnit.G), entry("Poivre", TypeUnit.G), entry("Basilic", TypeUnit.G),
            entry("Coriande", TypeUnit.G), entry("Persil", TypeUnit.G), entry("Romarin", TypeUnit.G), entry("Cumin", TypeUnit.G), entry("Curry", TypeUnit.G),
            entry("Origan", TypeUnit.G), entry("Cerfeuil", TypeUnit.G), entry("Cannelle", TypeUnit.G));

    private static final Map<String, TypeUnit> laitiers = Map.ofEntries(entry("Lait", TypeUnit.ML), entry("Crème", TypeUnit.ML), entry("Yogurt", TypeUnit.TASSE),
            entry("Fromage cheddar", TypeUnit.G), entry("Fromage suisse", TypeUnit.G), entry("Crème sûre", TypeUnit.ML), entry("Beurre", TypeUnit.G), entry("Lait au chocolat", TypeUnit.ML));

    private static final Map<String, TypeUnit> viandes = Map.ofEntries(entry("Boeuf", TypeUnit.G), entry("Porc", TypeUnit.G), entry("Poulet", TypeUnit.G),
            entry("Saumon", TypeUnit.G), entry("Truite", TypeUnit.G), entry("Thon", TypeUnit.G), entry("Veau", TypeUnit.G), entry("Agneau", TypeUnit.G),
            entry("Canard", TypeUnit.G), entry("Lapin", TypeUnit.G), entry("Oeuf", TypeUnit.G));

    private PlatAuMenu p1, p2, p3, p4, p5, p6;

    /**
     * Méthode qui fait afficher le menu des options à l'écran puis réalise la bonne tâche
     * selon l'option choisie
     */
    public void choixMenu(){
        int choix;

        do{
            choix = ConsoleVue.choixUtilisateur();
        }while(!(choix > 0 && choix <= 10));

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
            case 9:
                genereClient();
                break;
            default:
                System.exit(1);


        }
    }

    /**
     *
     */
    private void afficherMenu(){
        if(menu != null)
            ConsoleVue.afficherMenu(menu);
        else
            ConsoleVue.afficherErreur("Le menu n'a pas été généré");
    }

    /**
     *
     */
    private void afficherFacture(){
        if(facture != null)
            ConsoleVue.afficherFacture(facture);
        else
            ConsoleVue.afficherErreur("La facture n'a pas été générée");
    }

    /**
     *
     */
    private void afficherInventaire(){
        if(inventaire != null)
            ConsoleVue.afficherInventaire(inventaire);
        else
            ConsoleVue.afficherErreur("L'inventaire n'a pas été rempli");
    }

    /**
     *
     * @param nom Nom de L'ingrédient à afficher
     */
    private void afficherIngredient(String nom){
        if(inventaire != null) {
            Ingredient ingredient = null;
            IIterator iterator = inventaire.createIterator();
            while (iterator.hasNext()) {
                ingredient = iterator.next().getIngredient();
                if (ingredient.getNom().equals(nom))
                    break;
            }
            if (ingredient != null) {
                ConsoleVue.afficherIngredient(ingredient);
            } else
                ConsoleVue.afficherErreur("Il n'y a pas de " + nom + " dans l'inventaire");
        }
        else
            ConsoleVue.afficherErreur("L'inventaire n'a pas été généré");
    }

    /**
     *
     */
    private void afficherClient(){
        if(client != null)
            ConsoleVue.afficherClient(client);
        else
            ConsoleVue.afficherErreur("Le client n'a pas été généré");
    }
    /**
     * Méthode pour générer le menu
     */
    private void genereMenu(){
        IngredientFactory factory = new IngredientFactory();
        Map<Ingredient, Integer> saladeDeFruits = null, salade = null, smoothie = null, brochettePoulet = null, steak = null, filetDeSaumon = null;
        try {
            saladeDeFruits = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.FRUIT, "Poire", "Description Poire"), 1),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Ananas", "Description Ananas"), 1),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Raisin", "Description Raisin"), 50),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Pêche", "Description Pêche"), 1),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Kiwi", "Description Kiwi"), 1));

            salade = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.LEGUME, "Laitue", "Description Laitue"), 1),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Tomate", "Description Tomate"), 1),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Concombre", "Description Concombre"), 1),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Poivron", "Description Poivron"), 1));

            smoothie = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.FRUIT, "Banane", "Description Banane"), 1),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Fraise", "Description Fraise"), 75),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Framboise", "Description Framboise"), 50),
                    entry(factory.getIngredient(TypeIngredient.FRUIT, "Bleuet", "Description Bleuet"), 60));

            brochettePoulet = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.VIANDE, "Poulet", "Description Poulet"), 200),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Poivron", "Description Poivron"), 1),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Oignon", "Description Oignon"), 1),
                    entry(factory.getIngredient(TypeIngredient.LAITIER, "Beurre", "Description Beurre"), 20),
                    entry(factory.getIngredient(TypeIngredient.LAITIER, "Crème", "Description Crème"), 125));

            steak = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.VIANDE, "Boeuf", "Description Boeuf"), 200),
                    entry(factory.getIngredient(TypeIngredient.LEGUME, "Patate", "Description Patate"), 2),
                    entry(factory.getIngredient(TypeIngredient.LAITIER, "Crème sûre", "Description Crème sûre"), 50),
                    entry(factory.getIngredient(TypeIngredient.LAITIER, "Lait", "Description Lait"), 250),
                    entry(factory.getIngredient(TypeIngredient.EPICE, "Poivre", "Description Poivre"), 5));

            filetDeSaumon = Map.ofEntries(entry(factory.getIngredient(TypeIngredient.VIANDE, "Saumon", "Description Saumon"), 200),
                    entry(factory.getIngredient(TypeIngredient.EPICE, "Basilic", "Description Basilic"), 5),
                    entry(factory.getIngredient(TypeIngredient.EPICE, "Cerfeuil", "Description Raisin"), 5),
                    entry(factory.getIngredient(TypeIngredient.EPICE, "Poivre", "Description Poivre"), 5),
                    entry(factory.getIngredient(TypeIngredient.EPICE, "Persil", "Description Persil"), 5));

        }catch (IngredientException e){
            ConsoleVue.afficherErreur(e.getMessage());
        }
        this.menu = new Menu("Description du menu");
    //TODO Ajouter des plats santés et pour enfant et modifie une fois qu'il y a un builder
        this.menu.ajoute(p1 = new PlatAuMenu(1, "Salade de fruit", 8.99, saladeDeFruits));
        this.menu.ajoute(p2 = new PlatAuMenu(2, "Salade", 11.99, salade));
        this.menu.ajoute(p3 = new PlatAuMenu(3, "Smoothie", 5.99, smoothie));
        this.menu.ajoute(p4 = new PlatAuMenu(4, "Brochette de poulet", 17.99, brochettePoulet));
        this.menu.ajoute(p5 = new PlatAuMenu(5, "Steak et frites", 22.99, steak));
        this.menu.ajoute(p6 = new PlatAuMenu(6, "Filet de saumon", 19.99, filetDeSaumon));
    }

    /**
     * Méthode pour générer une facture aléatoire
     */
    private void genereFacture(){
        this.facture = new Facture("Ma facture");
        this.facture.associerChef(Chef.getInstance());
        this.facture.associerClient(this.client);

        try {
            this.facture.ajoutePlat(new PlatChoisi(p5, 1));
            this.facture.ajoutePlat(new PlatChoisi(p1, 1));
            this.facture.ajoutePlat(new PlatChoisi(p4, 1));
            this.facture.ajoutePlat(new PlatChoisi(p2, 1));
            this.facture.ajoutePlat(new PlatChoisi(p3, 2));
        } catch (FactureException e1) {
            ConsoleVue.afficherErreur(e1.getMessage());
        } catch (PlatsException e2) {
            ConsoleVue.afficherErreur(e2.getMessage());
        } catch (IngredientException e3) {
            ConsoleVue.afficherErreur(e3.getMessage());
        }
    }

    /**
     * Méthode pour remplir l'inventaire avec des quantités aléatoires
     */
    private void fillInventaire(){
        inventaire = Inventaire.getInstance();
        IngredientFactory factory = new IngredientFactory();
        IIterator iterator = inventaire.createIterator();
        try {
            for(Map.Entry<String, TypeUnit> entree: fruits.entrySet()){
                iterator.insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.FRUIT, entree.getKey(), "Description " + entree.getKey()), (int)(Math.random()*2000), entree.getValue()));
            }
            for(Map.Entry<String, TypeUnit> entree: legumes.entrySet()){
                iterator.insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.LEGUME, entree.getKey(), "Description " + entree.getKey()), (int)(Math.random()*100), entree.getValue()));
            }
            for(Map.Entry<String, TypeUnit> entree: epices.entrySet()){
                iterator.insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.EPICE, entree.getKey(), "Description " + entree.getKey()), (int)(Math.random()*2000), entree.getValue()));
            }
            for(Map.Entry<String, TypeUnit> entree: laitiers.entrySet()){
                iterator.insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.LAITIER, entree.getKey(), "Description " + entree.getKey()), (int)(Math.random()*2000), entree.getValue()));
            }
            for(Map.Entry<String, TypeUnit> entree: viandes.entrySet()){
                iterator.insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.VIANDE, entree.getKey(), "Description " + entree.getKey()), (int)(Math.random()*20000), entree.getValue()));
            }


        }catch (IngredientException e){
            ConsoleVue.afficherErreur(e.getMessage());
        }



    }

    /**
     * Méthode pour générer un client
     */
    private void genereClient(){
        int id;
        String nom;
        String carte;
        Scanner scanner = new Scanner(System.in);
        ConsoleVue.afficherMessage("Entrez le code d'identification du client\n");
        id = scanner.nextInt();
        scanner.nextLine();
        ConsoleVue.afficherMessage("Entrez le nom du client\n");
        nom = scanner.nextLine();
        ConsoleVue.afficherMessage("Entrez le numéro de carte de crédit du client\n");
        carte = scanner.nextLine();

        try {
            this.client = new Client(id, nom, carte);
        } catch (ClientException e) {
            ConsoleVue.afficherErreur(e.getMessage());
        }
    }


    //TODO: Try catch pour afficher les différentes erreurs
    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        while(true) {
            controller.choixMenu();
        }
    }
}
