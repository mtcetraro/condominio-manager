package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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

    public boolean execLogin(Connection connection){
        System.out.println("Inserisci i dati di Login:\nNome Utente: ");
        Scanner scanner = new Scanner(System.in);
        String utente = scanner.nextLine();
        System.out.println("\nPassword: ");
        String password = scanner.nextLine();
        this.setUtente(utente);
        this.setPassword(password);
        scanner.close();
        
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
}
