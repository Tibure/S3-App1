package tests;

import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.facture.*;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TermineTest {
    PlatChoisi p1,p2,p3;

    @BeforeEach
    void setUp() {
        try {
            CommandeEtat etat = new Termine();
            CommandeEtat etatPreparation = new Preparation();
            CommandeEtat etatServi = new Servi();
            p1 = new PlatChoisi(new PlatAuMenu(4, "Description", 20.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p2 = new PlatChoisi(new PlatAuMenu(5, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p3 = new PlatChoisi(new PlatAuMenu(8, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);

            p1.setEtat(etat);
            p2.setEtat(etatPreparation);
            p3.setEtat(etatServi);
        } catch(IngredientException e) {
            fail();
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeState() {
        assertFalse(new Termine().changeState(p1));
        assertTrue(new Termine().changeState(p2));
        assertFalse(new Termine().changeState(p3));
    }

    @Test
    void getEtat() {
        assertEquals(Etats.TERMINE,p1.getEtat().getEtat());
        assertNotEquals(Etats.TERMINE,p2.getEtat().getEtat());
    }

    @Test
    void testToString() {
        assertEquals("Etat: Termine", new Termine().toString());
        assertEquals("Etat: Termine", p1.getEtat().toString());
    }
}