package it.mtcetraro.condominio;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    

    public String getCondominio() {
        return this.condominio;
    }

    public String getInterno() {
        return this.interno;
    }

    public String getSubalterno() {
        return this.subalterno;
    }

    public String getFoglio() {
        return this.foglio;
    }

    public String getParticella() {
        return this.particella;
    }

    public int getSpesapersonale() {
        return this.spesapersonale;
    }

    public String getProprietario() {
        return this.proprietario;
    }

    public boolean inserisci(Connection conn){
        String query = "INSERT INTO Appartamento(Condominio, Interno, Subalterno, Foglio, Particella, SpesaPersonale, Proprietario) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.condominio);
            pstmt.setString(2, this.interno);
            pstmt.setString(3, this.subalterno);
            pstmt.setString(4, this.foglio);
            pstmt.setString(5, this.particella);
            pstmt.setInt(6, this.spesapersonale);
            pstmt.setString(7, this.proprietario);

            int rows = pstmt.executeUpdate();
            if(rows == 1){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Connection conn){
        String query = "DELETE FROM Appartamento WHERE interno = ? and Condominio = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.interno);
            pstmt.setString(2, this.condominio);
            int rows = pstmt.executeUpdate();
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

    public boolean modifica(Connection conn, Appartamento nuovo){
        String query = "UPDATE Appartamento SET Interno = ?, Subalterno = ?, Foglio = ?, Particella = ?, SpesaPersonale = ?, Proprietario = ? WHERE Interno = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, nuovo.getInterno());
            pstmt.setString(2, nuovo.getSubalterno());
            pstmt.setString(3, nuovo.getFoglio());
            pstmt.setString(4, nuovo.getParticella());
            pstmt.setInt(5, nuovo.getSpesapersonale());
            pstmt.setString(6, nuovo.getProprietario());
            pstmt.setString(7, this.interno);

            int rows = pstmt.executeUpdate();
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
    public String toString(){
        return this.interno + " - " + this.proprietario;
    }
}