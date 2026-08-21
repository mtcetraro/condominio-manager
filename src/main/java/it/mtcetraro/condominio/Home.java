package it.mtcetraro.condominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Proprietario> showProprietari(Condominio condominio){
        try(Connection conn = DatabaseManager.getConnection()){
            List<Proprietario> proprietari = condominio.showProp(conn);
            return proprietari;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserimentoProp(Proprietario proprietario){
        try(Connection conn = DatabaseManager.getConnection()){
            return proprietario.inserimento(conn);
        }catch(SQLException e){
            e.printStackTrace();
            return false;
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

    public boolean appartamentoModificato(Appartamento vecchio, Appartamento nuovo){
        try(Connection conn = DatabaseManager.getConnection()){
            return vecchio.modifica(conn, nuovo);
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificaProprietario(Proprietario vecchio, Proprietario nuovo){
        try(Connection conn = DatabaseManager.getConnection()){
            return vecchio.modifica(conn, nuovo);
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

    public boolean inserisciSpesa(Spesa spesa){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean spesaInserita = spesa.inserisci(conn);
            return spesaInserita;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Double> estraiSpesa(Condominio condominio, int anno){
        try(Connection conn = DatabaseManager.getConnection()){
            Map<String, Double> mapp = condominio.estraiPerTipo(conn, anno);
            return mapp;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Spesa> getSpesa(Condominio condominio, int anno){
        try(Connection conn = DatabaseManager.getConnection()){
            List<Spesa> spese = condominio.caricaSpesaAnno(conn, anno);
            return spese;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean modificaSpesa(Spesa spesa_vecchia, Spesa spesa_nuova){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean modificata = spesa_nuova.modifica(conn, spesa_vecchia);
            return modificata;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean eliminaSpesa(Spesa spesa){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean rimossa = spesa.rimuovi(conn);
            return rimossa;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<TabellaMillesimale> getTab(Condominio condominio){
        try(Connection conn = DatabaseManager.getConnection()){
            List<TabellaMillesimale> tabelle = condominio.caricaTabelle(conn);
            return tabelle;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserimentoAppXTab(Appartamento appartamento, String tabella, int millesimi){
        try(Connection conn = DatabaseManager.getConnection()){
            boolean inserito = appartamento.inserisciTabella(conn, tabella, millesimi);
            return inserito;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<AppartamentoTabella> showAppxTab(Condominio condominio, String tabella){
        try(Connection conn = DatabaseManager.getConnection()){
            List<AppartamentoTabella> lista = condominio.showAppTab(conn, tabella);
            return lista;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getMillesimiTot(Condominio condominio, String tabella){
        try(Connection conn = DatabaseManager.getConnection()){
            int millesimi = condominio.getMillesimiPresenti(conn, tabella);
            return millesimi;
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
}

