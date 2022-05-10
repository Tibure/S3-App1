package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.facture.Commande;
import model.menufact.facture.Etats;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatChoisi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlatChoisiTest {

    PlatAuMenu pm1, pm2;
    PlatChoisi pc1, pc2;
    IngredientFactory factory;
    Ingredient i1, i3;

    Map<Ingredient, Double> r1, r2;
    @BeforeEach
    void setUp() {
        factory = new IngredientFactory();
        try {
            i1 = factory.getIngredient(TypeIngredient.FRUIT, "Fraise", "Description");
            i3 = factory.getIngredient(TypeIngredient.LAITIER, "Lait", "Description");
        } catch (IngredientException e) {
            fail();
        }

        r1 = Map.ofEntries(Map.entry(i1, 10.0));
        r2 = Map.ofEntries(Map.entry(i3, 200.0));

        pm1 = new PlatAuMenu(1, "Plat1", 4.0, r1);
        pm2 = new PlatAuMenu(2, "Plat2", 8.0, r2);

        pc1 = new PlatChoisi(pm1, 1);
        pc2 = new PlatChoisi(pm2, 2);
    }

    @Test
    void testToString() {
        assertEquals(pc1.toString(), "model.menufact.plats.PlatChoisi{quantite=1, " +
                "plat=" + pm1.toString() + "}");
    }

    @Test
    void getQuantite() {
        assertEquals(pc1.getQuantite(), 1);
    }

    @Test
    void setQuantite() {
        pc2.setQuantite(4);
        assertEquals(pc2.getQuantite(), 4);
    }

    @Test
    void getPlat() {
        assertEquals(pc1.getPlat(), pm1);
    }

    @Test
    void getEtat() {
        assertEquals(pc1.getEtat(), null);
    }

    @Test
    void setEtat() {
        pc2.setEtat(new Commande());
        assertEquals(pc2.getEtat().getEtat(), Etats.COMMANDE);
    }
}