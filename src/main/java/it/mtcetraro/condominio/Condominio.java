package it.mtcetraro.condominio;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Condominio{
    //attributi
    private String nome;
    private String cf;
    private String indirizzo;
    private String comune;
    private String amministratore;


    public Condominio(){
        this.nome = null;
        this.cf = null;
        this.indirizzo = null;
        this.comune = null;
        this.amministratore = null;
    }

    public Condominio(String nome, String Cf, String luogo, String comune, String amministratore){
        this.nome = nome;
        this.cf = Cf;
        this.indirizzo = luogo;
        this.comune = comune;
        this.amministratore = amministratore;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setLuogo(String luogo){
        this.indirizzo = luogo;
    }

    public void setAmministratore(String amministratore){
        this.amministratore = amministratore;
    }

    public String getNome(){
        return this.nome;
    }

    public String getLuogo(){
        return this.indirizzo;
    }

    public String getAmministratore(){
        return this.amministratore;
    }

    public boolean submission(Connection conn){
        String query = "INSERT INTO CONDOMINIO(Nome, CF, Indirizzo, Comune, Amministratore) VALUES (?,?,?,?,?)";
        try(PreparedStatement psmt = conn.prepareStatement(query)){
            psmt.setString(1, this.nome);
            psmt.setString(2, this.cf);
            psmt.setString(3, this.indirizzo);
            psmt.setString(4, this.comune);
            psmt.setString(5, this.amministratore);
            int rows = psmt.executeUpdate();
            if(rows==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Appartamento> showAppart(Connection conn){
        List<Appartamento> appartamenti = new ArrayList<>();
        String query = "SELECT * FROM Appartamento WHERE Condominio = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.nome);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String interno = rs.getString("Interno");
                    String subalterno = rs.getString("Subalterno");
                    String foglio = rs.getString("Foglio");
                    String particella = rs.getString("Particella");
                    int spesaPersonale = rs.getInt("SpesaPersonale");
                    String Proprietario = rs.getString("Proprietario");
                    Appartamento appartamento = new Appartamento(this.nome, interno, subalterno, foglio, particella, spesaPersonale, Proprietario);
                    appartamenti.add(appartamento);
                }
                return appartamenti;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean rimozione(Connection conn){
        String query = "DELETE FROM Condominio WHERE CF=?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.cf);
            int rows = pstmt.executeUpdate();
            if(rows==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Proprietario> showProp(Connection conn){
        List<Proprietario> proprietari = new ArrayList<>();
        String query = "SELECT DISTINCT p.CF, p.Nome, p.Cognome, p.Telefono, p.Email, p.Residenza FROM Appartamento a JOIN Proprietario p ON a.Proprietario = p.CF WHERE a.Condominio = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.nome);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String Cod = rs.getString("CF");
                    String Name = rs.getString("Nome");
                    String Cogn = rs.getString("Cognome");
                    String Phone = rs.getString("Telefono");
                    String Email = rs.getString("Email");
                    String Residenza = rs.getString("Residenza");

                    Proprietario proprietario = new Proprietario(Cod, Name, Cogn, Phone, Email, Residenza);
                    proprietari.add(proprietario);
                }
                return proprietari;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return proprietari;
        }
    }

    public Map<String, Double> estraiPerTipo(Connection conn, int anno){
        Map<String, Double> mappa = new HashMap<>();
        //TODO: GROUP BY anche per Tabella Millesimale
        //TODO: Aggiungere nel WHERE anche il Date per i filtri di anni 
        String query = "SELECT Tipologia, SUM(Cifra) AS Cifra FROM Spesa WHERE Condominio=? AND EXTRACT(YEAR FROM DataPagamento) = ? GROUP BY Tipologia";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.nome);
            pstmt.setInt(2, anno);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String tipologia = rs.getString("Tipologia");
                    Double cifra = rs.getDouble("Cifra");
                
                    mappa.put(tipologia, cifra);
                }
                return mappa;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return mappa;
        }
    }

    public List<Spesa> caricaSpesaAnno(Connection conn, int anno){
        List<Spesa> spese = new ArrayList<>();
        //TODO: GROUP BY anche per Tabella Millesimale
        //TODO: Aggiungere nel WHERE anche il Date per i filtri di anni 
        String query = "SELECT NumFattura, Tipologia, DataPagamento, TabellaMillessimale, Cifra AS Cifra FROM Spesa WHERE Condominio=? AND EXTRACT(YEAR FROM DataPagamento) = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.nome);
            pstmt.setInt(2, anno);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String tipologia = rs.getString("Tipologia");
                    Double cifra = rs.getDouble("Cifra");
                    String fattura = rs.getString("NumFattura");
                    Date data = rs.getDate("DataPagamento");
                    String tabella = rs.getString("TabellaMillessimale");

                    Spesa spesa = new Spesa(fattura, this.nome, tipologia, cifra, data, tabella);
                    spese.add(spesa);
                }
                return spese;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return spese;
        }
    }


    public List<TabellaMillesimale> caricaTabelle(Connection conn){
        List<TabellaMillesimale> tabelle = new ArrayList<>();
        String query = "SELECT Tabella FROM TabellaMillesimale WHERE Condominio=?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, this.nome);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String tabe = rs.getString("Tabella");

                    TabellaMillesimale tabella = new TabellaMillesimale(tabe, this.nome);
                    tabelle.add(tabella);
                }
                return tabelle;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return tabelle;
        }
    }


    @Override
    public String toString() {
        // Ritorna il testo che vuoi vedere visivamente nella ListView
        return this.nome + " - " + this.comune + " - " + this.amministratore; 
    }

}