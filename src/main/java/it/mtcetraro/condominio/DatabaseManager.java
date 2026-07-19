package it.mtcetraro.condominio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Cambia le credenziali con quelle del tuo PostgreSQL locale
    private static final String URL = "jdbc:postgresql://localhost:5432/condominio_test";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "imieidb2026"; 

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione riuscita");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}