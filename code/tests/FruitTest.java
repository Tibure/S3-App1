package tests;

import model.ingredients.Epice;
import model.ingredients.Fruit;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    Fruit f1;
    @Test
    void constructeur() {
        try {
            f1 = new Fruit("Fraise", "Description");
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new Fruit("", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            new Fruit("Fraise", "");
            fail();
        } catch (IngredientException e) {}


    }
}