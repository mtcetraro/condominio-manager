package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Register{
    private String username;
    private String password;

    public Register(){
        this.username = null;
        this.password = null;
    }

    public Register(String user, String pass){
        this.username = user;
        this.password = pass;
    }

    public boolean makeRegistration(Connection conn, String user, String pass){
        String query = "INSERT INTO Utente(NomeUtente, Password) VALUES (?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
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
}