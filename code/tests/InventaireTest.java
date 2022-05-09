package tests;

import model.ingredients.*;
import model.ingredients.exceptions.IngredientException;
import model.inventaire.IIterator;
import model.inventaire.Inventaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    Inventaire inv1, inv2, inv3;
    Ingredient i1, i2, i3;
    IngredientInventaire ii1, ii2, ii3;
    IngredientFactory factory;
    @BeforeEach
    void setUp() {
        inv1 = Inventaire.getInstance();
        IIterator iterator = inv1.createIterator();
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
            ii3 = new IngredientInventaire(i3, 2000.0, TypeUnit.ML);
        } catch (IngredientException e) {
            fail();
        }
        Map<String, Double> map = new HashMap<>();
        IngredientInventaire ingredient = null;
        while(iterator.hasNext()){
            ingredient = iterator.next();
            map.put(ingredient.getIngredient().getNom(), ingredient.getQuantite());
        }
        for(String str: map.keySet()){
            try {
                iterator.remove(str, map.get(str).intValue());
            } catch (IngredientException e) {
                fail();
            }
        }
    }

    @Test
    void getInstance() {
        inv2 = Inventaire.getInstance();
        assertEquals(inv1, inv2);
    }

    @Test
    void testToString() {
        inv3 = Inventaire.getInstance();
        IIterator iterator = inv3.createIterator();
        try {
            iterator.insert(ii1);
            iterator.insert(ii2);
            iterator.insert(ii3);
        } catch (IngredientException e) {
            fail();
        }
        assertEquals(inv3.toString(), "model.inventaire.Inventaire{ingredients=" +
                "model.ingredients.IngredientInventaire{ingredient=model.ingredients.Ingredient{" +
                "nom=Fraise, description=Description, type=FRUIT}, quantité=50.0, unité=unités}\n" +
                "model.ingredients.IngredientInventaire{ingredient=model.ingredients.Ingredient{" +
                "nom=Poivre, description=Description, type=EPICE}, quantité=100.0, unité=g}\n" +
                "model.ingredients.IngredientInventaire{ingredient=model.ingredients.Ingredient{" +
                "nom=Lait, description=Description, type=LAITIER}, quantité=2000.0, unité=mL}}");
    }

    @Test
    void testInsert(){
        inv3 = Inventaire.getInstance();
        IIterator iterator = inv3.createIterator();
        try {
            assertTrue(iterator.insert(ii1));
            assertTrue(iterator.insert(ii1));
        } catch (IngredientException e) {
            fail();
        }

    }

    @Test
    void testRemove(){
        inv3 = Inventaire.getInstance();
        IIterator iterator = inv3.createIterator();
        try {
            iterator.insert(ii1);
            iterator.insert(ii2);
            iterator.insert(ii3);
        } catch (IngredientException e) {
            fail();
        }

        try {
            assertTrue(iterator.remove("Fraise", 10));
        } catch (IngredientException e) {
            fail();
        }

        try {
            assertTrue(iterator.remove("Fraise", 40));
        } catch (IngredientException e) {
            fail();
        }
        try {
            assertFalse(iterator.remove("Boeuf", 2));
        } catch (IngredientException e) {
            fail();
        }

        try {
            assertFalse(iterator.remove("Poivre", 101));
        } catch (IngredientException e) {
            fail();
        }

    }

    @Test
    void testNext(){
        inv3 = Inventaire.getInstance();
        IIterator iterator = inv3.createIterator();
        try {
            iterator.insert(ii1);
            iterator.insert(ii2);
            iterator.insert(ii3);
        } catch (IngredientException e) {
            fail();
        }

        while (iterator.hasNext())
            assertTrue(iterator.next() != null);


        assertTrue(iterator.next() == null);

    }
}