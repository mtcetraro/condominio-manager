package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main{
    public static void main(){
        System.out.println("Benvenuto su CondominioApp\nConnessione al database\n");
        String query = "SELECT * FROM Utente";

        // Inseriamo Connessione, Statement e ResultSet nel try-with-resources
        // così Java chiuderà tutto automaticamente liberando la memoria nel DB
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);    
            System.out.println("Dati presenti nel database:\n");

            // Il metodo rs.next() sposta il cursore alla riga successiva. 
            // Ritorna 'true' se c'è una riga, 'false' quando i dati sono finiti.
            while (rs.next()) {
                // Estraiamo i dati specificando il nome della colonna della tabella SQL
                String Utente = rs.getString("NomeUtente");
                String Password = rs.getString("Password");
                // Stampiamo i record a video
                System.out.println("NomeUtente: " + Utente + " | Password: " + Password);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dal database:");
            e.printStackTrace();
        }
    }
}