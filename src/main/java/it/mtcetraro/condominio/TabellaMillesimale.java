package it.mtcetraro.condominio;

public class TabellaMillesimale {
    private String tabella;
    private String condominio;

    public TabellaMillesimale(String tabella, String condominio){
        this.tabella = tabella;
        this.condominio = condominio;
    }
    
    public String getTabella(){
        return this.tabella;
    }

    public String getCondominio(){
        return this.condominio;
    }


    @Override
    public String toString(){
        return this.tabella;
    }
}
