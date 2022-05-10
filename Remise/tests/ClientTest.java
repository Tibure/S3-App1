package tests;

import model.menufact.Client;
import model.menufact.exceptions.ClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client c1, c2;
    @BeforeEach
    void setUp(){
        try {
            c1 = new Client(1, "Bob", "123456789");
            c2 = new Client(2, "Alice", "987654321");
        } catch (ClientException e) {
            fail();
        }


    }
    @Test
    void constructeurInvalide(){
        try {
            new Client(-1, "Bob", "123456789");
            fail();
        } catch (ClientException e) {}

        try {
            new Client(1, "", "123456789");
            fail();
        } catch (ClientException e) {}

        try {
            new Client(1, "Bob", "");
            fail();
        } catch (ClientException e) {}
    }

    @Test
    void getIdClient() {
        assertEquals(c1.getIdClient(), 1);
    }

    @Test
    void setIdClient() {
        try {
            c2.setIdClient(3);
        } catch (ClientException e) {
            fail();
        }

        assertEquals(c2.getIdClient(), 3);

        try {
            c2.setIdClient(-1);
            fail();
        } catch (ClientException e) {}

        assertEquals(c2.getIdClient(), 3);

    }

    @Test
    void getNom() {
        assertEquals(c1.getNom(), "Bob");
    }

    @Test
    void setNom() {
        try {
            c2.setNom("Bob");
        } catch (ClientException e) {
            fail();
        }

        assertEquals(c2.getNom(), "Bob");

        try {
            c2.setNom("");
            fail();
        } catch (ClientException e) {}

        assertEquals(c2.getNom(), "Bob");
    }

    @Test
    void getNumeroCarteCredit() {
        assertEquals(c1.getNumeroCarteCredit(), "123456789");
    }

    @Test
    void setNumeroCarteCredit() {
        try {
            c2.setNumeroCarteCredit("123456789");
        } catch (ClientException e) {
            fail();
        }

        assertEquals(c2.getNumeroCarteCredit(), "123456789");

        try {
            c2.setNumeroCarteCredit("");
            fail();
        } catch (ClientException e) {}

        assertEquals(c2.getNumeroCarteCredit(), "123456789");
    }

    @Test
    void testToString() {
        assertEquals(c1.toString(), "model.menufact.Client{idClient=1, nom=Bob, numeroCarteCredit=123456789}");
    }
}