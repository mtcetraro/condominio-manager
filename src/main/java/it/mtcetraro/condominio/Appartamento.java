package it.mtcetraro.condominio;

public class Appartamento{
    //attributi
    private String condominio;
    private String interno;
    private String subalterno;
    private String foglio;
    private String particella;
    private int spesapersonale;
    private String proprietario;

    public Appartamento(String condominio, String interno, String subalterno, String foglio, String particella, int spesapersonale, String proprietario){
        this.condominio = condominio;
        this.interno = interno;
        this.subalterno = subalterno;
        this.foglio = foglio;
        this.particella = particella;
        this.spesapersonale = spesapersonale;
        this.proprietario = proprietario;
    }

    @Override 
    public String toString(){
        return this.interno + " - " + this.proprietario;
    }
}