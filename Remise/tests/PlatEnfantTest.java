package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatBuilder;
import model.menufact.plats.PlatEnfant;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlatEnfantTest {
    PlatEnfant pe1, pe2;
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
        try {
            pe1 = new PlatEnfant(1, "Plat1", 4.0, 0.5, r1);
            pe2 = new PlatEnfant(2, "Plat2", 8.0, 0.5, r2);
        } catch (PlatsException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new PlatEnfant(3, "Plat3", 10.0, -0.1, r1);
            fail();
        } catch (PlatsException e) {}

        try {
            new PlatEnfant(4, "Plat4", 10.0, 1.1, r1);
            fail();
        } catch (PlatsException e) {}
    }

    @Test
    void testToString() {
        assertEquals(pe1.toString(), "PlatEnfant{proportion=0.5} " + "model.menufact.plats.PlatAuMenu{code=1, description=Plat1, " +
                "prix=2.0}\n");
    }



    @Test
    void getProportion() {
        assertEquals(pe1.getProportion(), 0.5);
    }

    @Test
    void setProportion() {
        try {
            pe2.setProportion(0.8);
        } catch (PlatsException e) {
            fail();
        }

        assertEquals(pe2.getProportion(), 0.8);

        try {
            pe2.setProportion(-0.1);
        } catch (PlatsException e) {}

        try {
            pe2.setProportion(1.1);
        } catch (PlatsException e) {}

    }

}