package it.mtcetraro.condominio;

import java.sql.Connection;
import java.sql.SQLException;

public class Home {
    
    public void Accesso(){
        try (Connection conn = DatabaseManager.getConnection()) {  
            Login login = new Login();
            boolean utente_loggato = login.execLogin(conn);
            if(utente_loggato){
                System.out.println("Loggato");
                login.showCondomini(conn);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dal database:");
            e.printStackTrace();
        }
    }

}
