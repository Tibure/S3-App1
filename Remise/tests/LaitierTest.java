package tests;

import model.ingredients.Epice;
import model.ingredients.Laitier;
import model.ingredients.exceptions.IngredientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaitierTest {

    Laitier l1;
    @Test
    void constructeur() {
        try {
            l1 = new Laitier("Lait", "Description");
        } catch (IngredientException e) {
            fail();
        }
    }

    @Test
    void constructeurInvalide(){
        try {
            new Laitier("", "Description");
            fail();
        } catch (IngredientException e) {}

        try {
            new Laitier("Lait", "");
            fail();
        } catch (IngredientException e) {}


    }
}