package tests;

import model.ingredients.Epice;
import model.ingredients.Viande;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViandeTest {

    Viande v1;
    @Test
    void constructeur() {
        try {
            v1 = new Viande("Boeuf", "Description");
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new Viande("", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            new Viande("Boeuf", "");
            fail();
        } catch (IngredientException e) {}


    }
}