package tests;

import model.ingredients.Epice;
import model.ingredients.Legume;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LegumeTest {

    Legume l1;
    @Test
    void constructeur() {
        try {
            l1 = new Legume("Patate", "Description");
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new Legume("", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            new Legume("Patate", "");
            fail();
        } catch (IngredientException e) {}


    }
}