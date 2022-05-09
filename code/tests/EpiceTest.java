package tests;

import model.ingredients.Epice;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpiceTest {

    Epice e1;
    @Test
    void constructeur() {
        try {
            e1 = new Epice("Basilic", "Description");
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new Epice("", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            new Epice("Basilic", "");
            fail();
        } catch (IngredientException e) {}


    }


}