package it.mtcetraro.condominio;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public String toString() {
        // Ritorna il testo che vuoi vedere visivamente nella ListView
        return this.nome + " - " + this.comune + " - " + this.amministratore; 
    }

}