package it.mtcetraro.condominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public List<Condominio> mostraCondomino(String Username){
        try(Connection conn = DatabaseManager.getConnection()){
            Login login = new Login();
            List<Condominio> Condomini = login.showCondomini(conn, Username);
            return Condomini;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean InserimentoCondominio(Condominio condominio){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean condominio_inserito = condominio.submission(conn);
            return condominio_inserito;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Appartamento> showAppartamenti(Condominio condominio){
        try(Connection conn = DatabaseManager.getConnection()){
            List<Appartamento> Appartamenti = condominio.showAppart(conn);
            return Appartamenti;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean appartamentoInserito(Appartamento appartamento){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean inserimento = appartamento.inserisci(conn);
            return inserimento;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAppartamento(Appartamento appartamento){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean delete = appartamento.delete(conn);
            return delete;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean condominioDelete(Condominio condominio){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean condominio_rimosso = condominio.rimozione(conn);
            return condominio_rimosso;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

