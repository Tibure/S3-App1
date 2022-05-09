package tests;

import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.facture.*;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiTest {

    PlatChoisi p1,p2,p3;

    @BeforeEach
    void setUp() {
        try {
            CommandeEtat etat = new Servi();
            CommandeEtat etatCommande = new Commande();
            CommandeEtat etatTermine = new Termine();
            p1 = new PlatChoisi(new PlatAuMenu(4, "Description", 20.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p2 = new PlatChoisi(new PlatAuMenu(5, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);
            p3 = new PlatChoisi(new PlatAuMenu(8, "Description", 30.0, Map.ofEntries(Map.entry(new IngredientFactory().getIngredient(TypeIngredient.FRUIT, "Fraise", "Description"), 2.0))), 1);

            p1.setEtat(etat);
            p2.setEtat(etatCommande);
            p3.setEtat(etatTermine);
        } catch(IngredientException e) {
            fail();
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeState() {
        assertFalse(new Servi().changeState(p1));
        assertFalse(new Servi().changeState(p2));
        assertTrue(new Servi().changeState(p3));
    }

    @Test
    void getEtat() {
            assertEquals(Etats.SERVI,p1.getEtat().getEtat());
            assertNotEquals(Etats.SERVI,p2.getEtat().getEtat());
    }

    @Test
    void testToString() {
        assertEquals("Etat: Servi", new Servi().toString());
        assertEquals("Etat: Servi", p1.getEtat().toString());
    }
}