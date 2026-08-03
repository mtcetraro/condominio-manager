package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private String Utente;
    private String Password;

    public void setUtente(String utente){
        this.Utente = utente;
    }

    public void setPassword(String password){
        this.Password = password;
    }

    public String getUtente(){
        return this.Utente;
    }

    public String getPassword(){
        return this.Password;
    }

    public boolean execLogin(Connection connection, String utente, String password){
        this.setUtente(utente);
        this.setPassword(password);
        
        String query = "SELECT * FROM Utente WHERE NomeUtente=? AND Password=?";
        try(PreparedStatement pstmt = connection.prepareStatement(query);){
            pstmt.setString(1, this.Utente);
            pstmt.setString(2, this.Password);
            try(ResultSet rs = pstmt.executeQuery()){
                return rs.next();
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Condominio> showCondomini(Connection connection, String amministratore){
        List<Condominio> CondominiTotal = new ArrayList<>();
        System.out.println("Questi sono i tuoi condomini:\n");
        String query = "SELECT p.Nome, p.CF, p.Indirizzo, p.Comune, p.Amministratore FROM Condominio p WHERE p.Amministratore = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, amministratore);
            try(ResultSet rs = pstmt.executeQuery()){
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCounter = rsmd.getColumnCount();
                for(int i = 1; i <= columnCounter; i++){
                    System.out.print(rsmd.getColumnName(i) + "\t\t");
                }
                System.out.println();
                while(rs.next()){
                    String nome = rs.getString("Nome");
                    String cf = rs.getString("CF");
                    String indirizzo = rs.getString("Indirizzo");
                    String comune = rs.getString("Comune");             // <-- Ora il Comune viene letto correttamente!
                    String amm = rs.getString("Amministratore");
                    Condominio condom = new Condominio(nome, cf, indirizzo, comune, amm);
                    CondominiTotal.add(condom);
                }
                return CondominiTotal;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
