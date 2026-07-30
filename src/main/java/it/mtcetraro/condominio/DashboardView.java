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

public class DashboardView extends BorderPane{
    private VBox sidebar;
    private StackPane areaContenuto;
    
    public DashboardView(Stage stage, Condominio condominio){
        this.setPadding(new Insets(20));

        

        StackPane areaContenuto = new StackPane();
        areaContenuto.setPadding(new Insets(20));
        areaContenuto.setMaxWidth(Double.MAX_VALUE);
        areaContenuto.setMinWidth(Double.MAX_VALUE);
        areaContenuto.setStyle("-fx-background-color: #1a87db; -fx-background-radius: 6px");

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
        sidebar.setStyle("-fx-background-color: #000000;  -fx-background-radius: 6px");

        String nomeLogo = condominio.getNome();
        Label logo = new Label(nomeLogo);
        logo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label menu = new Label("Menu:");
        menu.setStyle("-fx-text-fill: #64748b; -fx-font-size: 12px; -fx-font-weight: bold;");
        sidebar.setMargin(menu, new Insets(30, 0, 15, 0));

        sidebar.getChildren().addAll(logo, menu);

        VBox menuBar = new VBox();
        menuBar.setSpacing(15);

        Button home = new Button("Home");
        home.setStyle( "-fx-text-size:13; -fx-text-weight: bold; -fx-cursor: hand; -fx-background-color: #c3b05b; -fx-text-fill: #ffffff");
        home.setPrefWidth(120);
        home.setPrefHeight(35);
        SVGPath iconaHome = new SVGPath();
        iconaHome.setContent("M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z");
        iconaHome.setStyle("-fx-text-fill: #ffffff;");
        home.setGraphic(iconaHome);
        home.setGraphicTextGap(15);
        home.setAlignment(Pos.CENTER_LEFT);
        menuBar.getChildren().addAll(home);

        sidebar.getChildren().add(menuBar);

        return sidebar;
    }
}
