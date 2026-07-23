package it.mtcetraro.condominio;

import java.sql.Connection;
import java.sql.SQLException;

public class Home {
    
    public boolean Accesso(String Username, String Password){
        try (Connection conn = DatabaseManager.getConnection()) {  
            Login login = new Login();
            boolean utente_loggato = login.execLogin(conn, Username, Password);
            return utente_loggato;

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dal database:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean Registrazione(String Username, String Password){
        try (Connection conn = DatabaseManager.getConnection()) {  
            Register register = new Register();
            boolean utente_registrato = register.makeRegistration(conn, Username, Password);
            return utente_registrato;

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dal database:");
            e.printStackTrace();
            return false;
        }
    }


}
