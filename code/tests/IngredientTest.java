package tests;

import model.ingredients.Epice;
import model.ingredients.Ingredient;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    Ingredient i1, i2;
    @BeforeEach
    void setUp() {
        try {
            i1 = new Ingredient("Patate", "Description");
        } catch (IngredientException e) {
            fail();
        }

        try {
            i2 = new Ingredient("Fraise", "Description");
        } catch (IngredientException e) {
            fail();
        }


    }

    @Test
    void constructeurInvalide(){

            try {
                new Ingredient("", "Description");
                fail();
            } catch (IngredientException e) {}

            try {
                new Ingredient("Basilic", "");
                fail();
            } catch (IngredientException e) {}

    }

    @Test
    void getNom() {
        assertEquals(i1.getNom(), "Patate");
    }

    @Test
    void setNom() {
        try {
            i2.setNom("Lait");
        } catch (IngredientException e) {
            fail();
        }

        assertEquals(i2.getNom(), "Lait");

        try {
            i2.setNom("");
            fail();
        } catch (IngredientException e) {}

        assertEquals(i2.getNom(), "Lait");


    }

    @Test
    void getDescription() {
        assertEquals(i1.getDescription(), "Description");
    }

    @Test
    void setDescription() {
        try {
            i2.setDescription("Desc");
        } catch (IngredientException e) {
            fail();
        }

        assertEquals(i2.getDescription(), "Desc");

        try {
            i2.setDescription("");
            fail();
        } catch (IngredientException e) {}

        assertEquals(i2.getDescription(), "Desc");
    }

    @Test
    void getTypeIngredient() {
        assertEquals(i1.getTypeIngredient(), null);
    }

    @Test
    void setTypeIngredient() {
        i2.setTypeIngredient(TypeIngredient.EPICE);
        assertEquals(i2.getTypeIngredient(), TypeIngredient.EPICE);
    }

    @Test
    void testToString() {
        i2.setTypeIngredient(TypeIngredient.EPICE);
        assertEquals(i2.toString(), "model.ingredients.Ingredient{nom=Fraise, description=Description, " +
                "type=EPICE}");
    }
}