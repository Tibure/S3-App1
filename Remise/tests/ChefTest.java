package tests;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import model.menufact.Chef;
import model.menufact.facture.*;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {
    Chef c1, c2, c3;
    PlatChoisi p1, p2;
    Inventaire inv;
    IngredientFactory factory;
    @BeforeEach
    void setUp() {

        c1 = Chef.getInstance();
        c3 = Chef.getInstance();
        try {
            p1 = new PlatChoisi(new PlatAuMenu(1, "Description", 10.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p2 = new PlatChoisi(new PlatAuMenu(2, "Description", 10.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Framboise", "Description"), 2.0))), 1);
        } catch (IngredientException e) {
            throw new RuntimeException(e);
        }
        factory = new IngredientFactory();
        inv = Inventaire.getInstance();
        IIterator iterator = inv.createIterator();
        IngredientInventaire ingredient = null;
        while(iterator.hasNext()){
            ingredient = iterator.next();
            try {
                iterator.remove(ingredient.getIngredient().getNom(), (int) ((ingredient.getQuantite())-1));
            } catch (IngredientException e) {
                fail();
            }
        }
    }

    @Test
    void getInstance() {
        c2 = Chef.getInstance();
        assertEquals(c1, c2);
    }

    @Test
    void getNom() {
        c3.setNom("Bob");
        assertEquals(c3.getNom(), "Bob");
    }

    @Test
    void setNom() {
        c3.setNom("Alice");
        assertEquals(c3.getNom(), "Alice");
    }

    @Test
    void preparerPlat() {
        p1.setEtat(new Commande());
        try {
            c3.preparerPlat(p1);
        } catch (PlatsException e) {
            fail();
        }

        assertEquals(p1.getEtat().getEtat(), Etats.PREPARATION);

        p1.setEtat(new Servi());
        try {
            c3.preparerPlat(p1);
            fail();
        } catch (PlatsException e) {}

    }

    @Test
    void terminerPlat() {
        p1.setEtat(new Preparation());

        try {
            c3.terminerPlat(p1);
        } catch (PlatsException e) {
            fail();
        }

        p1.setEtat(new Commande());

        try {
            c3.terminerPlat(p1);
            fail();
        } catch (PlatsException e) {}
    }

    @Test
    void servirPlat() {
        p1.setEtat(new Termine());

        try {
            c3.servirPlat(p1);
        } catch (PlatsException e) {
            fail();
        }

        p1.setEtat(new Preparation());

        try {
            c3.servirPlat(p1);
            fail();
        } catch (PlatsException e) {}
    }

    @Test
    void impossiblePlat() {
        p1.setEtat(new Commande());

        try {
            c3.impossiblePlat(p1);
        } catch (PlatsException e) {
            fail();
        }

        p1.setEtat(new Preparation());

        try {
            c3.impossiblePlat(p1);
            fail();
        } catch (PlatsException e) {}
    }

    @Test
    void commanderPlat() {

        try {
            c3.commanderPlat(p2);
            fail();
        } catch (Exception e) {}


        try {
            inv.createIterator().insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 4.0, TypeUnit.UNIT));
            c3.commanderPlat(p1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void verifierSiPossible() {
        assertFalse(c3.verifierSiPossible(p2));

        try {
            inv.createIterator().insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.FRUIT, "Framboise", "Description"), 1.0, TypeUnit.UNIT));
        } catch (IngredientException e) {
            fail();
        }

        assertFalse(c3.verifierSiPossible(p2));

        try {
            inv.createIterator().insert(new IngredientInventaire(factory.getIngredient(TypeIngredient.FRUIT, "Framboise", "Description"), 4.0, TypeUnit.UNIT));
        } catch (IngredientException e) {
            fail();
        }

        assertTrue(c3.verifierSiPossible(p2));

    }

    @Test
    void testToString() {
        c3.setNom("Bob");

        assertEquals(c3.toString(), "Chef {Nom du chef: Bob}");
    }
}