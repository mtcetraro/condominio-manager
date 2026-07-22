package it.mtcetraro.condominio;

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

public class RegisterView extends VBox{
    private TextField username;
    private PasswordField password;
    private Button registerButton;

    public RegisterView(){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setStyle("-fx-background-color: #f4f9f8");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(30));
        grid.setHgap(12);
        grid.setVgap(15);

        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        Label titleLabel = new Label("Gestionale Condominio");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e293b;");
        
        Label subtitleLabel = new Label("Accedi al tuo account amministratore");
        subtitleLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        subtitleLabel.setStyle("-fx-text-fill: #64748b;");

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(subtitleLabel, 0, 1, 2, 1);

        this.getChildren().add(grid);
    }
}
