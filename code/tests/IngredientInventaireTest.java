package tests;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientInventaireTest {

    IngredientInventaire ii1, ii2;
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
            ii1 = new IngredientInventaire(i1, 50.0, TypeUnit.UNIT);
            ii2 = new IngredientInventaire(i2, 100.0, TypeUnit.G);
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new IngredientInventaire(null, 10.0, TypeUnit.UNIT);
            fail();
        } catch (IngredientException e) {}

        try {
            new IngredientInventaire(i1, -1.0, TypeUnit.UNIT);
            fail();
        } catch (IngredientException e) {}
    }

    @Test
    void getQuantite() {
        assertEquals(ii1.getQuantite(), 50.0);
    }

    @Test
    void setQuantite() {
        try {
            ii2.setQuantite(200.0);
        } catch (IngredientException e) {
            fail();
        }
        assertEquals(ii2.getQuantite(), 200.0);

        try {
            ii2.setQuantite(-1.0);
            fail();
        } catch (IngredientException e) {}
        assertEquals(ii2.getQuantite(), 200.0);
    }

    @Test
    void getUnit() {
        assertEquals(ii1.getUnit(), TypeUnit.UNIT);
    }

    @Test
    void setUnit() {
        ii2.setUnit(TypeUnit.KG);
        assertEquals(ii2.getUnit(), TypeUnit.KG);
    }

    @Test
    void getIngredient() {
        assertEquals(ii1.getIngredient(), i1);
    }

    @Test
    void setIngredient() {
        try {
            ii2.setIngredient(i3);
        } catch (IngredientException e) {
            fail();
        }

        assertEquals(ii2.getIngredient(), i3);

        try {
            ii2.setIngredient(null);
            fail();
        } catch (IngredientException e) {}
        assertEquals(ii2.getIngredient(), i3);
    }

    @Test
    void testToString() {
        assertEquals(ii1.toString(), "model.ingredients.IngredientInventaire{ingredient=" +
                "model.ingredients.Ingredient{nom=Fraise, description=Description, type=FRUIT}," +
                " quantité=50.0, unité=unités}");
    }
}