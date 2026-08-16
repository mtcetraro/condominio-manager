package it.mtcetraro.condominio;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

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

    public boolean inserisci(Connection conn){
        String query = "INSERT INTO SPESA(NumFattura, Condominio, Tipologia, Cifra, DataPagamento, TabellaMillessimale) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.fattura);
            pstmt.setString(2, this.condominio);
            pstmt.setString(3, this.tipologia);
            pstmt.setDouble(4, this.cifra);
            pstmt.setDate(5, this.data);
            pstmt.setString(6, tabella);
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
        return "Num.Fattura: " + this.fattura + " - " + String.valueOf(this.cifra)+"€" + " - " + this.tipologia + " - " + String.valueOf(this.data); 
    }
}
