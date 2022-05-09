package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatEnfant;
import model.menufact.plats.PlatSante;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlatSanteTest {

    PlatSante ps1, ps2;
    IngredientFactory factory;
    Ingredient i1, i2, i3;

    Map<Ingredient, Double> r1, r2, r3;
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

            ps1 = new PlatSante(1, "Plat1", 4.0, 100.0, 200.0, 300.0, r1);
            ps2 = new PlatSante(2, "Plat2", 8.0, 300.0, 200.0, 100.0, r2);

    }

    @Test
    void testToString() {
        assertEquals(ps1.toString(), "model.menufact.plats.PlatSante{kcal=100.0, chol=" +
                "200.0, gras=300.0} " + "model.menufact.plats.PlatAuMenu{code=1, description=Plat1, " +
                "prix=4.0}\n");
    }


    @Test
    void getKcal() {
        assertEquals(ps1.getKcal(), 100.0);
    }

    @Test
    void getChol() {
        assertEquals(ps1.getChol(), 200.0);
    }

    @Test
    void getGras() {
        assertEquals(ps1.getGras(), 300.0);
    }
}