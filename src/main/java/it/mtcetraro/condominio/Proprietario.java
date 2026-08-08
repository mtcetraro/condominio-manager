package it.mtcetraro.condominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proprietario {
    private String codice_fiscale;
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String residenza;

    public Proprietario(String codice, String nome, String cognome, String telefono, String email, String residenza){
        this.codice_fiscale = codice;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.residenza = residenza;
    }


    public boolean inserimento(Connection conn){
        String query = "SELECT * FROM Proprietario WHERE CF=?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.codice_fiscale);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return true;
                }else{
                    String query2 = "INSERT INTO Proprietario(CF, Telefono, Residenza, Email, Nome, Cognome) VALUES (?,?,?,?,?,?)";
                    try(PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                        pstmt2.setString(1, this.codice_fiscale);
                        pstmt2.setString(2, this.telefono);
                        pstmt2.setString(3, this.residenza);
                        pstmt2.setString(4, this.email);
                        pstmt2.setString(5, this.nome);
                        pstmt2.setString(6, this.cognome);

                        int rows = pstmt2.executeUpdate();
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
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString(){
        return this.codice_fiscale + " - " + this.cognome + " - " + this.telefono;
    }

}
