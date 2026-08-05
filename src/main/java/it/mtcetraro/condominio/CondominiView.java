package it.mtcetraro.condominio;
import javafx.collections.ObservableList;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class CondominiView extends VBox {
    private ListView<Condominio> listviewcondominio;
    private ObservableList<Condominio> listaCondomini;
    private Button aggiungi;

    public CondominiView(Stage stage, String Username){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(20));

        GridPane grid = new GridPane();
        
        Label title = new Label("I tuoi condomini:");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #1e293b;");

        grid.add(title, 0, 0, 1, 1);

        aggiungi = new Button("Aggiungi");
        aggiungi.setPrefWidth(80);
        aggiungi.setStyle("-fx-background-color: #393939; -fx-text-fill: #ffffff; -fx-font-weight: bold");
        aggiungi.setOnMouseEntered(e -> aggiungi.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;"
        ));
        aggiungi.setOnMouseExited(e -> aggiungi.setStyle(
            "-fx-background-color: #393939; -fx-text-fill: #ffffff; -fx-font-weight: bold"
        ));

        aggiungi.setOnAction(e -> aggiungiCondominio(stage, Username));

        grid.add(aggiungi, 1, 0, 1, 1);
        grid.setHalignment(aggiungi, HPos.RIGHT);

        // 4. Per farlo coincidere con la ListView, dici alla Colonna 0 di espandersi al massimo
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS); // Occupa tutto lo spazio vuoto spingendo la Colonna 1 a destra
        grid.getColumnConstraints().add(col0);

        // Creiamo l'ObservableList (la lista di dati)
        listaCondomini = FXCollections.observableArrayList();

        listviewcondominio = new ListView<>(listaCondomini);
        listviewcondominio.setPrefHeight(300);
        

        caricaCondomini(Username);

        listviewcondominio.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2 && e.getButton()==MouseButton.PRIMARY){
                Condominio condominio_selezionato = listviewcondominio.getSelectionModel().getSelectedItem();

                if(condominio_selezionato == null){
                    mostraMessaggio(AlertType.ERROR, "Errore di selezione", "Stai selezionando una riga vuota. Seleziona uno dei tuoi condomini!");
                }else{
                    DashboardView dashboard = new DashboardView(stage, condominio_selezionato);
                    Scene scene = new Scene(dashboard, 1300, 800);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });

        listviewcondominio.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.D){
                Condominio condominio_selezionato = listviewcondominio.getSelectionModel().getSelectedItem();
                if(condominio_selezionato == null){
                    mostraMessaggio(AlertType.ERROR, "Condominio inesistente", "Seleziona un condominio presente nella lista");
                }else{
                    Home home = new Home();
                    boolean eliminato = home.condominioDelete(condominio_selezionato);
                }
            }
        });

        this.getChildren().addAll(grid, listviewcondominio);
    }

    private void caricaCondomini(String Username){
        Home home = new Home();
        List<Condominio> Condomini = home.mostraCondomino(Username);
        this.listaCondomini.clear();
        this.listaCondomini.addAll(Condomini);
    }

    private void aggiungiCondominio(Stage stage, String amministratore){
        InserimentoCondominioView inserimentoCondominioView = new InserimentoCondominioView(stage, amministratore);
        Scene scene = new Scene(inserimentoCondominioView, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void mostraMessaggio(AlertType type, String titolo, String messaggio){
        Alert alert = new Alert(type);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}
