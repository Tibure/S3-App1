package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatBuilder;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlatAuMenuTest {

    PlatAuMenu pm1, pm2;
    IngredientFactory factory;
    Ingredient i1, i2, i3;

    Map<Ingredient, Double> r1, r2, r3;
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

        r1 = Map.ofEntries(Map.entry(i1, 10.0));
        r2 = Map.ofEntries(Map.entry(i3, 200.0));
        r3 = Map.ofEntries(Map.entry(i2, 100.0));

        pm1 = new PlatAuMenu(1, "Plat1", 4.0, r1);
        pm2 = new PlatAuMenu(2, "Plat2", 8.0, r2);

    }

    @Test
    void testToString() {
        assertEquals(pm1.toString(), "model.menufact.plats.PlatAuMenu{code=1, description=Plat1, " +
                "prix=4.0}\n");
    }

    @Test
    void getCode() {
        assertEquals(pm1.getCode(), 1);
    }

    @Test
    void setCode() {
        pm2.setCode(4);
        assertEquals(pm2.getCode(), 4);
    }

    @Test
    void getDescription() {
        assertEquals(pm1.getDescription(), "Plat1");
    }

    @Test
    void setDescription() {
        pm2.setDescription("Description");
        assertEquals(pm2.getDescription(), "Description");
    }

    @Test
    void getPrix() {
        assertEquals(pm1.getPrix(), 4.0);
    }

    @Test
    void setPrix() {
        pm2.setPrix(12.0);
        assertEquals(pm2.getPrix(), 12.0);
    }

    @Test
    void getIngredients() {
        assertEquals(pm1.getIngredients(), r1);
    }

    @Test
    void setIngredient() {
        pm2.setIngredient(r3);
        assertEquals(pm2.getIngredients(), r3);
    }
}