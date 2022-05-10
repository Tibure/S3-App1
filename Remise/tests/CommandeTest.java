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

class CommandeTest {
    PlatChoisi p1,p2,p3;

    @BeforeEach
    void setUp() {
        try {
            CommandeEtat etat = new Commande();
            CommandeEtat etatServi = new Servi();
            p1 = new PlatChoisi(new PlatAuMenu(4, "Description", 20.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p2 = new PlatChoisi(new PlatAuMenu(5, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p3 = new PlatChoisi(new PlatAuMenu(8, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);

            p1.setEtat(etat);
            p3.setEtat(etatServi);
        } catch(IngredientException e) {
            fail();
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeState() {
        assertFalse(new Commande().changeState(p1));
        assertTrue(new Commande().changeState(p2));
        assertFalse(new Commande().changeState(p3));
    }

    @Test
    void getEtat() {
        assertEquals(Etats.COMMANDE,p1.getEtat().getEtat());
        assertNotEquals(Etats.COMMANDE,p2.getEtat());
    }

    @Test
    void testToString() {
        assertEquals("Etat: Commande", new Commande().toString());
        assertEquals("Etat: Commande", p1.getEtat().toString());
    }
}