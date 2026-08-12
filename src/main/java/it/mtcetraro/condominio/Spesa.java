package it.mtcetraro.condominio;
import java.sql.Date;

public class Spesa {
    private String fattura;
    private String condominio;
    private String tipologia;
    private Double cifra;
    private Date data;
    private String tabella;

    public Spesa(String fattura, String condominio, String tipologia, Double cifra, Date data, String tabella){
        this.fattura = fattura;
        this.condominio = condominio;
        this.tipologia = tipologia;
        this.cifra = cifra;
        this.data = data;
        this.tabella = tabella;
    }


}
