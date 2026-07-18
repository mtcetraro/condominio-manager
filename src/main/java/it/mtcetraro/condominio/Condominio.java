package it.mtcetraro.condominio;
import java.util.List;
import java.util.ArrayList;

public class Condominio{
    //attributi
    private String nome;
    private String luogo;
    private List<Appartamento> appartamenti;


    public Condominio(String nome, String luogo){
        this.nome = nome;
        this.luogo = luogo;
        this.appartamenti = new ArrayList<>();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setLuogo(String luogo){
        this.luogo = luogo;
    }

    public void aggiungiAppartamento(Appartamento appartamento){
        this.appartamenti.add(appartamento);
    }

    public int getNumAppartamenti(){
        return this.appartamenti.size();
    }

    public List<Appartamento> getAppartamenti(){
        return this.appartamenti;
    }

    public String getNome(){
        return this.nome;
    }

    public String getLuogo(){
        return this.luogo;
    }
}