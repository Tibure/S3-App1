package tests;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import model.menufact.Chef;
import model.menufact.Client;
import model.menufact.Menu;
import model.menufact.exceptions.ClientException;
import model.menufact.exceptions.MenuException;
import model.menufact.facture.Facture;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatBuilder;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    Menu m1, m2;
    PlatAuMenu pm1, pm2;

    Ingredient i1, i2, i3;
    IngredientFactory factory;
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
            pm1 = new PlatBuilder().setCode(1).setDescription("Plat1").setPrix(4.0).setIngredients(Map.ofEntries(Map.entry(i1, 10.0))).getResult();
            pm2 = new PlatBuilder().setCode(2).setDescription("Plat2").setPrix(8.0).setIngredients(Map.ofEntries(Map.entry(i3, 200.0))).getResult();
        } catch (PlatsException e) {
            fail();
        }

        m1 = new Menu("Description");
        m2 = new Menu("Description");
    }

    @Test
    void ajoute() {
        m1.ajoute(pm1);
        assertEquals(m1.platCourant(), pm1);

    }

    @Test
    void setPosition() {
        m1.ajoute(pm1);
        m1.ajoute(pm2);

        //Set position valide
        m1.setPosition(0);
        assertEquals(m1.platCourant(), pm1);

        //Set position invalide donc ne fonctionne pas
        m1.setPosition(-1);
        assertEquals(m1.platCourant(), pm1);

        //Set position invalide donc ne fonctionne pas
        m1.setPosition(2);
        assertEquals(m1.platCourant(), pm1);
    }

    @Test
    void platCourant() {
        assertEquals(m1.platCourant(), null);

        m1.ajoute(pm1);
        assertEquals(m1.platCourant(), pm1);
    }

    @Test
    void positionSuivante() {
        m1.ajoute(pm1);
        m1.ajoute(pm2);

        try {
            m1.positionSuivante();
            fail();
        } catch (MenuException e) {}

        m1.setPosition(0);

        try {
            m1.positionSuivante();
        } catch (MenuException e) {
            fail();
        }
        assertEquals(m1.platCourant(), pm2);
    }

    @Test
    void positionPrecedente() {
        m1.ajoute(pm1);
        m1.ajoute(pm2);

        try {
            m1.positionPrecedente();
        } catch (MenuException e) {
            fail();
        }
        assertEquals(m1.platCourant(), pm1);

        try {
            m1.positionPrecedente();
            fail();
        } catch (MenuException e) {}
    }

    @Test
    void testToString() {
        m1.ajoute(pm1);
        m1.ajoute(pm2);

        assertEquals(m1.toString(), "model.menufact.Menu{description=Description, " +
                "courant=1, plat=\n[" + pm1.toString() + ", " + pm2.toString() + "]}");
    }
}