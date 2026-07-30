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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class DashboardView extends BorderPane{
    private Button home;
    private Button view;
    private Button costo;
    
    public DashboardView(Stage stage, Condominio condominio){
        this.setPadding(new Insets(20));

        

        StackPane areaContenuto = new StackPane();
        areaContenuto.setPadding(new Insets(20));
        areaContenuto.setMaxWidth(Double.MAX_VALUE);
        areaContenuto.setMinWidth(Double.MAX_VALUE);
        areaContenuto.setStyle("-fx-background-color: #1a87db; -fx-background-radius: 6px");

        this.setLeft(this.creaSidebar());
        this.setCenter(areaContenuto);
    }

    private VBox creaSidebar(){
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(15));
        sidebar.setSpacing(15);

        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.setStyle("-fx-background-color: #000000;  -fx-background-radius: 6px");

        



        return sidebar;
    }
}
