package it.mtcetraro.condominio;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Condominio{
    //attributi
    private String nome;
    private String luogo;
    private String amministratore;

    public Condominio(){
        this.nome = null;
        this.luogo = null;
        this.amministratore = null;
    }

    public Condominio(String nome, String luogo, String amministratore){
        this.nome = nome;
        this.luogo = luogo;
        this.amministratore = amministratore;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setLuogo(String luogo){
        this.luogo = luogo;
    }

    public void setAmministratore(String amministratore){
        this.amministratore = amministratore;
    }

    public String getNome(){
        return this.nome;
    }

    public String getLuogo(){
        return this.luogo;
    }

    public String getAmministratore(){
        return this.amministratore;
    }

    public boolean submission(Connection conn){
        String query = "INSERT INTO CONDOMINIO(NomeCondominio, Indirizzo, Amministratore) VALUES (?,?,?)";
        try(PreparedStatement psmt = conn.prepareStatement(query)){
            psmt.setString(1, this.nome);
            psmt.setString(2, this.luogo);
            psmt.setString(3, this.amministratore);
            int rows = psmt.executeUpdate();
            if(rows==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        // Ritorna il testo che vuoi vedere visivamente nella ListView
        return this.nome + " - " + this.luogo + " - " + this.amministratore; 
    }

}