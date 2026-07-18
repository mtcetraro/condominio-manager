package it.mtcetraro.condominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CondominioTest{

    @Test
    public void shouldReturnZero(){
        String nome = "Baia degli Ulivi";
        String luogo = "Sangineto";
        Condominio baia = new Condominio(nome, luogo);

        int numero = baia.getNumAppartamenti();
        assertEquals(0, numero);
    }

    @Test
    public void shouldReturnAppartamento(){
        // 1. Creo il condominio
        Condominio baia = new Condominio("Baia degli Ulivi", "Sangineto");

        // 2. Creo due appartamenti passando il condominio nel costruttore
        Appartamento app1 = new Appartamento("Vespasiano", 10, 500, baia);
        Appartamento app2 = new Appartamento("Cetraro", 15, 367, baia);

        // 3. Li associo al condominio
        baia.aggiungiAppartamento(app1);
        baia.aggiungiAppartamento(app2);

        // 4. Assertions
        assertEquals(2, baia.getNumAppartamenti());
        assertEquals("Baia degli Ulivi", app1.getCondominio().getNome());
    }
}