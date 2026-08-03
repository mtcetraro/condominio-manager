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
import javafx.scene.input.MouseButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class DashboardView extends BorderPane{
    private VBox sidebar;
    private StackPane areaContenuto;
    
    public DashboardView(Stage stage, Condominio condominio){
        this.setPadding(new Insets(20));

        

        areaContenuto = new StackPane();
        areaContenuto.setMaxWidth(Double.MAX_VALUE);
        areaContenuto.setMaxHeight(Double.MAX_VALUE);
        areaContenuto.setAlignment(Pos.TOP_LEFT);

        sidebar = this.createSidebar(condominio);

        this.setLeft(sidebar);
        this.setCenter(areaContenuto);
    }

    private VBox createSidebar(Condominio condominio){
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(40, 20, 20, 20));
        sidebar.setSpacing(15);

        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.setStyle("-fx-background-color: #4a4a4a;  -fx-background-radius: 6px;");

        String nomeLogo = condominio.getNome();
        Label logo = new Label(nomeLogo);
        logo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label menu = new Label("MENU:");
        menu.setStyle("-fx-text-fill: #f4da68; -fx-font-size: 15px; -fx-font-weight: bold;"); 
        sidebar.setMargin(menu, new Insets(30, 0, 15, 0));

        sidebar.getChildren().addAll(logo, menu);

        VBox menuBar = new VBox();
        menuBar.setSpacing(15);

        Button home = new Button("Home");
        home.setStyle( "-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff");
        home.setPrefWidth(160);
        home.setPrefHeight(35);
        SVGPath iconaHome = new SVGPath();
        iconaHome.setContent("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z");
        //iconaHome.setStyle("-fx-text-fill: #ffffff;");
        home.setGraphic(iconaHome);
        home.setGraphicTextGap(15);
        home.setAlignment(Pos.CENTER_LEFT);
        home.setOnMouseExited(e -> home.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff"));
        home.setOnMouseEntered(e -> home.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #f4da68; -fx-text-fill: #ffffff"));

        Button view = new Button("Appartamenti");
        view.setStyle( "-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff");
        view.setPrefWidth(160);
        view.setPrefHeight(35);
        SVGPath iconaUtenti = new SVGPath();
        iconaUtenti.setContent("M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-.952 4.125 4.125 0 0 0-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 0 1 8.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0 1 11.964-3.07M12 6.375a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0Zm8.25 2.25a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z");
        view.setGraphic(iconaUtenti);
        view.setGraphicTextGap(15);
        view.setAlignment(Pos.CENTER_LEFT);
        view.setOnMouseExited(e -> view.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff"));
        view.setOnMouseEntered(e -> view.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #f4da68; -fx-text-fill: #ffffff"));
        view.setOnAction(e -> mostraContenuto(contenutoView(condominio)));

        Button costo = new Button("Spesa");
        costo.setStyle( "-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff");
        costo.setPrefWidth(160);
        costo.setPrefHeight(35);
        SVGPath euro = new SVGPath();
        euro.setContent("M14.25 7.756a4.5 4.5 0 1 0 0 8.488M7.5 10.5h5.25m-5.25 3h5.25M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z");
        costo.setGraphic(euro);
        costo.setGraphicTextGap(15);
        costo.setAlignment(Pos.CENTER_LEFT);
        costo.setOnMouseExited(e -> costo.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff"));
        costo.setOnMouseEntered(e -> costo.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #f4da68; -fx-text-fill: #ffffff"));

        menuBar.getChildren().addAll(home, view, costo);

        sidebar.getChildren().add(menuBar);

        return sidebar;
    }

    private void mostraContenuto(Node nuovoContenuto){
        areaContenuto.getChildren().clear();

        areaContenuto.getChildren().add(nuovoContenuto);
    }

    private Node contenutoView(Condominio condominio){
        VBox contenuto = new VBox();
        contenuto.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        contenuto.setPrefWidth(900);
        contenuto.setMinWidth(900);
        contenuto.setMaxWidth(900);
        contenuto.setPadding(new Insets(100, 0, 0, 100));

        GridPane grid = new GridPane();
        grid.setVgap(30);

        Label title = new Label("Appartamenti del condominio:");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #1e293b;");

        ObservableList<Appartamento> lista_appartamenti_obs = FXCollections.observableArrayList();
        ListView<Appartamento> lista_appartamenti = new ListView<>(lista_appartamenti_obs);
        lista_appartamenti.setPrefSize(600, 500);
        lista_appartamenti.setMaxWidth(600);
        
        caricaAppartamenti(condominio, lista_appartamenti_obs);

        lista_appartamenti.setOnMouseClicked(e ->{
            if(e.getClickCount()==2 && e.getButton()==MouseButton.PRIMARY){
                Appartamento appartamento_selezionato = lista_appartamenti.getSelectionModel().getSelectedItem();
                if(appartamento_selezionato == null){
                    mostraMessaggio(AlertType.ERROR, "Appartamento inesistente", "Seleziona un appartamento presente nella lista!");
                }else{
                    //Implementazione della schermata di specifica appartamento
                }
            }
        });

        grid.add(title, 0, 0, 2, 1);
        grid.add(lista_appartamenti, 0, 1, 2, 1);

        contenuto.getChildren().add(grid);


        return contenuto;
    }

    private void mostraMessaggio(AlertType type, String titolo, String messaggio){
        Alert alert = new Alert(type);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    private void caricaAppartamenti(Condominio condominio, ObservableList<Appartamento> lista_appartamenti_obs){
        Home home = new Home();
        List<Appartamento> Appartamenti = home.showAppartamenti(condominio);
        lista_appartamenti_obs.clear();
        lista_appartamenti_obs.addAll(Appartamenti);
    }
}
