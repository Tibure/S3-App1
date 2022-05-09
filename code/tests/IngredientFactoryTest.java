package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientFactoryTest {

    IngredientFactory factory;

    Ingredient i1;
    @BeforeEach
    void setUp() {
        factory = new IngredientFactory();
    }

    @Test
    void getIngredient() {
        try {
            i1 = factory.getIngredient(TypeIngredient.FRUIT, "Fraise", "Description");
            factory.getIngredient(TypeIngredient.VIANDE, "Fraise", "Description");
            factory.getIngredient(TypeIngredient.LEGUME, "Fraise", "Description");
        } catch (IngredientException e) {
            fail();
        }

        assertTrue(i1.getTypeIngredient().equals(TypeIngredient.FRUIT) &&
                i1.getNom().equals("Fraise") &&
                i1.getDescription().equals("Description"));

        try {
            factory.getIngredient(TypeIngredient.FRUIT, "", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            factory.getIngredient(TypeIngredient.FRUIT, "Fruit", "");
            fail();
        } catch (IngredientException e) {}
    }
}