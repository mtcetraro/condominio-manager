package it.mtcetraro.condominio;

public class AppartamentoTabella {
    private String condominio;
    private String tabella;
    private String appartamento;
    private int millesimi;
    private double tassa;

    public AppartamentoTabella(String condominio, String tabella, String appartamento, int millesimi, double tassa){
        this.condominio = condominio;
        this.tabella = tabella;
        this.appartamento = appartamento;
        this.millesimi = millesimi;
        this.tassa = tassa;
    }

    @Override
    public String toString(){
        return this.appartamento + " -  " + "Millesimi: " + this.millesimi + " - " + this.tassa + "€";
    }
}
