package tests;

import model.ingredients.Ingredient;
import model.ingredients.IngredientFactory;
import model.ingredients.TypeIngredient;
import model.ingredients.exceptions.IngredientException;
import model.menufact.plats.PlatAuMenu;
import model.menufact.plats.PlatBuilder;
import model.menufact.plats.PlatEnfant;
import model.menufact.plats.PlatSante;
import model.menufact.plats.exceptions.PlatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlatBuilderTest {
    PlatBuilder pb1, pb2, pb3;
    PlatAuMenu pm, pm1;
    PlatEnfant pe, pe1;
    PlatSante ps, ps1;
    IngredientFactory factory;
    Ingredient i1, i2, i3;

    Map<Ingredient, Double> r1, r2, r3;
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

        r1 = Map.ofEntries(Map.entry(i1, 10.0));
        r2 = Map.ofEntries(Map.entry(i3, 200.0));
        r3 = Map.ofEntries(Map.entry(i2, 100.0));

        pm1 = new PlatAuMenu(1, "Plat1", 4.0, r1);
        try {
            pe1 = new PlatEnfant(2, "Plat2", 8.0, 0.5, r2);
        } catch (PlatsException e) {
            fail();
        }
        ps1 = new PlatSante(3, "Plat3", 10.0, 100.0, 200.0, 300.0, r3);

        pb1 = new PlatBuilder().setCode(1).setDescription("Plat1").setPrix(4.0).setIngredients(r1);
        pb2 = new PlatBuilder().setCode(2).setDescription("Plat2").setPrix(8.0).setProportion(0.5).setIngredients(r2);
        pb3 = new PlatBuilder().setCode(3).setDescription("Plat3").setPrix(10.0).setKcal(100.0).setChol(200.0).setGras(300.0).setIngredients(r3);
    }

    @Test
    void getResult() {
        //PlatAuMenu
        try {
            pm = pb1.getResult();
        } catch (PlatsException e) {
            fail();
        }
        assertTrue(pm.getCode() == pm1.getCode() &&
                pm.getDescription().equals(pm1.getDescription()) &&
                pm.getPrix() == pm1.getPrix() &&
                pm.getIngredients().equals(pm1.getIngredients()));

        //PlatEnfant
        try {
            pe = (PlatEnfant) pb2.getResult();
        } catch (PlatsException e) {
            fail();
        }
        assertTrue(pe.getCode() == pe1.getCode() &&
                pe.getDescription().equals(pe1.getDescription()) &&
                pe.getPrix() == pe1.getPrix() &&
                pe.getProportion() == pe1.getProportion() &&
                pe.getIngredients().equals(pe1.getIngredients()));

        //PlatSante
        try {
            ps = (PlatSante) pb3.getResult();
        } catch (PlatsException e) {
            fail();
        }
        assertTrue(ps.getCode() == ps1.getCode() &&
                ps.getDescription().equals(ps1.getDescription()) &&
                ps.getPrix() == ps1.getPrix() &&
                ps.getKcal() == ps1.getKcal() &&
                ps.getChol() == ps1.getChol() &&
                ps.getGras() == ps1.getGras() &&
                ps.getIngredients().equals(ps1.getIngredients()));
    }

    @Test
    void getResultInvalide(){
        //PlatMenu sans code
        try {
            new PlatBuilder().setDescription("Description").setPrix(10.0).setIngredients(r1).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatMenu sans description
        try {
            new PlatBuilder().setCode(1).setPrix(10.0).setIngredients(r1).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatMenu sans prix
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setIngredients(r1).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatMenu sans ingredients
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).getResult();
            fail();
        } catch (PlatsException e) {}


        //PlatEnfant plus kCal
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setProportion(0.5).setKcal(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatEnfant plus chol
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setProportion(0.5).setChol(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatEnfant plus gras
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setProportion(0.5).setGras(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatSante plus proportion
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setProportion(0.5).setKcal(100.0).setChol(100.0).setGras(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatSante sans kCal
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setChol(100.0).setGras(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatSante sans chol
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setKcal(100.0).setGras(100.0).getResult();
            fail();
        } catch (PlatsException e) {}

        //PlatSante sans gras
        try {
            new PlatBuilder().setCode(1).setDescription("Description").setPrix(10.0).setIngredients(r1).setKcal(100.0).setChol(100.0).getResult();
            fail();
        } catch (PlatsException e) {}



    }
}