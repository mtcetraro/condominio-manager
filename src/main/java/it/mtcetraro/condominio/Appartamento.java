package it.mtcetraro.condominio;

public class Appartamento{
    //attributi
    private String proprietario;
    private int numero_civico;
    private int metri_quadri;
    private Condominio condominio;

    public Appartamento(String proprietario, int numero_civico, int metri_quadri, Condominio appartenenza){
        this.proprietario = proprietario;
        this.numero_civico = numero_civico;
        this.metri_quadri = metri_quadri;
        this.condominio = appartenenza;
    }

    public Condominio getCondominio(){
        return this.condominio;
    }
}