package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.println("Benvenuto su CondominioApp\nConnessione al database\n");
        // Inseriamo Connessione, Statement e ResultSet nel try-with-resources
        // così Java chiuderà tutto automaticamente liberando la memoria nel DB
        try (Connection conn = DatabaseManager.getConnection()) {  
            Login login = new Login();
            boolean utente_loggato = login.execLogin(conn);
            if(utente_loggato){
                System.out.println("Loggato");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dal database:");
            e.printStackTrace();
        }
    }
}