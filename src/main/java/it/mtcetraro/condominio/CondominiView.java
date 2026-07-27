package it.mtcetraro.condominio;
import javafx.collections.ObservableList;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
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

public class CondominiView extends VBox {
    private ListView<Condominio> listviewcondominio;
    private ObservableList<Condominio> listaCondomini;

    public CondominiView(Stage stage, String Username){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));
        
        Label title = new Label("I tuoi condomini:");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #1e293b;");

        // Creiamo l'ObservableList (la lista di dati)
        listaCondomini = FXCollections.observableArrayList();

        listviewcondominio = new ListView<>(listaCondomini);
        listviewcondominio.setPrefHeight(300);

        caricaCondomini(Username);

        this.getChildren().addAll(title, listviewcondominio);
    }

    private void caricaCondomini(String Username){
        Home home = new Home();
        List<Condominio> Condomini = home.mostraCondomino(Username);
        this.listaCondomini.clear();
        this.listaCondomini.addAll(Condomini);
    }
}
