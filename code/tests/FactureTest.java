package tests;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import model.menufact.Chef;
import model.menufact.Client;
import model.menufact.exceptions.ClientException;
import model.menufact.facture.Etats;
import model.menufact.facture.Facture;
import model.menufact.facture.FactureEtat;
import model.menufact.facture.exceptions.FactureException;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatBuilder;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FactureTest {

    Facture f1, f2;
    Client client;
    Chef chef;

    PlatAuMenu pm1, pm2;

    Ingredient i1, i2, i3;
    IngredientInventaire ii1, ii2, ii3;
    IngredientFactory factory;
    Inventaire inv1;
    @BeforeEach
    void setUp() {
        factory = new IngredientFactory();
        try {
            i1 = factory.getIngredient(TypeIngredient.FRUIT, "Fraise", "Description");
            i2 = factory.getIngredient(TypeIngredient.EPICE, "Poivre", "Description");
            i3 = factory.getIngredient(TypeIngredient.LAITIER, "Lait", "Description");
        } catch (IngredientException e) {
            fail();
        }

        try {
            ii1 = new IngredientInventaire(i1, 50.0, TypeUnit.UNIT);
            ii2 = new IngredientInventaire(i2, 100.0, TypeUnit.G);
            ii3 = new IngredientInventaire(i3, 2000.0, TypeUnit.ML);
        } catch (IngredientException e) {
            fail();
        }


        f1 = new Facture("Description");
        f2 = new Facture("Description");
        try {
            client = new Client(1, "Bob", "123456789");
        } catch (ClientException e) {
            fail();
        }
        f2.associerClient(client);
        chef = Chef.getInstance();
        f2.associerChef(chef);

        try {
            pm1 = new PlatBuilder().setCode(1).setDescription("Plat1").setPrix(4.0).setIngredients(Map.ofEntries(Map.entry(i1, 10.0))).getResult();
            pm2 = new PlatBuilder().setCode(2).setDescription("Plat2").setPrix(8.0).setIngredients(Map.ofEntries(Map.entry(i3, 200.0))).getResult();
        } catch (PlatsException e) {
            fail();
        }

        inv1 = Inventaire.getInstance();
        IIterator iterator = inv1.createIterator();

        try {
            iterator.insert(ii1);
            iterator.insert(ii2);
            iterator.insert(ii3);
        } catch (IngredientException e) {
            fail();
        }


    }

    @Test
    void associerClient() {
        f1.associerClient(client);
    }

    @Test
    void associerChef() {
        f1.associerChef(chef);
    }

    @Test
    void sousTotal() {
        try {
            f2.ajoutePlat(new PlatChoisi(pm1, 2));
            f2.ajoutePlat(new PlatChoisi(pm2, 1));
        } catch (Exception e) {
          fail();
        }

        assertEquals(f2.sousTotal(), 16.0);

    }

    @Test
    void total() {
        try {
            f2.ajoutePlat(new PlatChoisi(pm1, 2));
            f2.ajoutePlat(new PlatChoisi(pm2, 1));
        } catch (Exception e) {
            fail();
        }

        assertEquals(f2.total(), 18.396);
    }

    @Test
    void payer() {
        f1.payer();
        assertEquals(f1.getEtat(), FactureEtat.PAYEE);
    }

    @Test
    void fermer() {
        f1.fermer();
        assertEquals(f1.getEtat(), FactureEtat.FERMEE);
    }

    @Test
    void ouvrir() {
        f1.fermer();
        try {
            f1.ouvrir();
        } catch (FactureException e) {
            fail();
        }
        assertEquals(f1.getEtat(), FactureEtat.OUVERTE);

        f1.payer();
        try {
            f1.ouvrir();
            fail();
        } catch (FactureException e) {}

        assertEquals(f1.getEtat(), FactureEtat.PAYEE);

    }

    @Test
    void getEtat() {
        assertEquals(f1.getEtat(), FactureEtat.OUVERTE);
    }

    @Test
    void ajoutePlat() {
        try {
            f2.ajoutePlat(new PlatChoisi(pm1, 1));
        } catch (Exception e) {
            fail();
        }

        f2.fermer();

        try {
            f2.ajoutePlat(new PlatChoisi(pm2, 1));
            fail();
        } catch (Exception e) {}

    }

    @Test
    void testToString() {
        try {
            f2.ajoutePlat(new PlatChoisi(pm1, 2));
            f2.ajoutePlat(new PlatChoisi(pm2, 1));
        } catch (Exception e) {
            fail();
        }

        assertEquals(f2.toString(), "model.menufact.facture.Facture{date=" + new Date() +
                ", description=Description, etat=OUVERTE, platchoisi=["+ new PlatChoisi(pm1, 2).toString() + ", " + new PlatChoisi(pm2, 1).toString() + "], " +
                "courant=-1, client=" + client.toString() + ", Sous-total=16.0, TPS=0.8, TVQ=1.596, Total=18.396}");
    }

    @Test
    void genererFacture() {
        try {
            f2.ajoutePlat(new PlatChoisi(pm1, 2));
            f2.ajoutePlat(new PlatChoisi(pm2, 1));
        } catch (Exception e) {
            fail();
        }

        assertEquals(f2.genererFacture(), "Facture generee.\nDate:" +new Date() + "\nDescription: " +
                "Description\nClient:Bob\nLes plats commandes:\nSeq   Plat         Prix   Quantite\n" +
                "1     Plat1  4.0      2\n" +
                "2     Plat2  8.0      1\n" +
                "          TPS:               0.8\n          TVQ:               1.596\n" +
                "          Le total est de:   18.396\n");
    }
}