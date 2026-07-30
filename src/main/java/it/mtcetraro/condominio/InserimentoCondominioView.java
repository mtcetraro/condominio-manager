package it.mtcetraro.condominio;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;  
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class InserimentoCondominioView extends VBox{
    private TextField nomeCondominio;
    private TextField indirizzoCondominio;
    private Button inviaCondominio;
    private Button back;
    
    public InserimentoCondominioView(Stage stage, String Username){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));

        GridPane grid = new GridPane();
        //grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(15);
        grid.setPadding(new Insets(30));
        
        // Stile del pannello centrale (schermata tipo "card" bianca con angoli arrotondati)
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        Label title = new Label("Aggiungi un nuovo condominio");
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #1e293b");
        grid.add(title, 0, 0, 2, 1);

        Label nome = new Label("Nome:");
        nome.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        nomeCondominio = new TextField();
        nomeCondominio.setPromptText("Nome del condominio...");
        nomeCondominio.setPrefWidth(250);
        nomeCondominio.setStyle("\"-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;\"");

        grid.add(nome, 0, 1, 2, 1);
        grid.add(nomeCondominio, 0, 2, 2, 1);


        Label address = new Label("Indirizzo:");
        address.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        indirizzoCondominio = new TextField();
        indirizzoCondominio.setPromptText("Indirizzo del condominio...");
        indirizzoCondominio.setPrefWidth(250);
        indirizzoCondominio.setStyle("\"-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;\"");

        grid.add(address, 0, 3, 2, 1);
        grid.add(indirizzoCondominio, 0, 4, 2, 1);

        back = new Button("Indietro");
        back.setPrefWidth(80);
        back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        back.setOnMouseExited(e -> back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));
        back.setOnMouseEntered(e -> back.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));

        grid.add(back, 0, 5, 1, 1);

        inviaCondominio = new Button("Invia");
        inviaCondominio.setPrefWidth(80);
        inviaCondominio.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        inviaCondominio.setOnMouseExited(e -> inviaCondominio.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));
        inviaCondominio.setOnMouseEntered(e -> inviaCondominio.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold;"));

        grid.add(inviaCondominio, 1, 5, 1, 1);
        grid.setHalignment(inviaCondominio, HPos.RIGHT);
        
        inviaCondominio.setOnAction(e -> sendCondominio(Username, stage));
        back.setOnAction(e -> backToCondomini(stage, Username));


        this.getChildren().add(grid);

    }

    private void sendCondominio(String amministratore, Stage stage){
        String nomeCondom = nomeCondominio.getText();
        String indirizzoCondom = indirizzoCondominio.getText();
        Home home = new Home();

        Condominio condominio = new Condominio(nomeCondom, indirizzoCondom, amministratore);
        boolean inserimento = home.InserimentoCondominio(condominio);

        if(nomeCondom.isEmpty() || indirizzoCondom.isEmpty()){
            mostraMessaggio(AlertType.ERROR, "Riempi tutti i campi dati", "Non stai fornendo tutte le informazioni richieste!");
        }else if(inserimento){
            mostraMessaggio(AlertType.CONFIRMATION, "Inserimento riuscito", "Il tuo nuovo condominio è stato registrato!");
            CondominiView condominioView = new CondominiView(stage, amministratore);
            Scene scene = new Scene(condominioView, 450, 500);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void mostraMessaggio(AlertType tipo, String titolo, String messaggio) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    private void backToCondomini(Stage stage, String username){
        CondominiView condominioView = new CondominiView(stage, username);
        Scene scene = new Scene(condominioView, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

}
