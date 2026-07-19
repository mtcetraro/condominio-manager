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

    public String getProprietario(){
        return this.proprietario;
    }

    public int getNumeroCivico(){
        return this.numero_civico;
    }

    public int getMetriQuadri(){
        return this.metri_quadri;
    }

    public void setProprietario(String proprietario){
        this.proprietario = proprietario;
    }

    public void setNumeroCivico(int numero){
        this.numero_civico = numero;
    }

    public void setMetriQuadri(int metri){
        this.metri_quadri = metri;
    }

    public void setCondominio(Condominio condominio){
        this.condominio = condominio;
    }

}