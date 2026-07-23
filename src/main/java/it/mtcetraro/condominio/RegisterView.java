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
import javafx.stage.Stage;
import javafx.scene.Scene;

public class RegisterView extends VBox{
    private TextField username;
    private PasswordField password;
    private Button registerButton;
    private Button goBack;

    public RegisterView(Stage stage){
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

        Label indietro = new Label("Hai un account?:");
        indietro.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155;");
        Button goBack = new Button("Accedi");
        goBack.setStyle("-fx-background-color: #ffffff; -fx-font-weight: bold; -fx-text-fill: #000000");
        goBack.setPrefWidth(80);
        goBack.setDefaultButton(true);

        goBack.setOnMouseEntered(e -> goBack.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;"
        ));
        goBack.setOnMouseExited(e -> goBack.setStyle(
            "-fx-background-color: #ffffff; -fx-font-weight: bold; -fx-text-fill: #000000"
        ));

        goBack.setOnAction(e -> backToLogin(stage));

        grid.add(indietro, 0 , 2, 1, 1);
        grid.add(goBack, 1, 2, 1, 1);


        Label titleLabel = new Label("Gestionale Condominio");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e293b;");
        
        Label subtitleLabel = new Label("Registra il tuo account amministratore");
        subtitleLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        subtitleLabel.setStyle("-fx-text-fill: #64748b;");

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(subtitleLabel, 0, 1, 2, 1);

        Label userLabel = new Label("Username / Email:");
        userLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155;");
        username = new TextField();
        username.setPromptText("Inserisci lo username...");
        username.setPrefWidth(250);
        username.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(userLabel, 0, 3, 2, 1);
        grid.add(username, 0, 4, 2, 1);

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155;");
        password = new PasswordField();
        password.setPromptText("••••••••");
        password.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(passwordLabel, 0, 5, 2, 1);
        grid.add(password, 0, 6, 2, 1);

        registerButton = new Button("Registrati");
        registerButton.setDefaultButton(true);
        registerButton.setPrefWidth(250);
        registerButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");

        registerButton.setOnMouseEntered(e -> registerButton.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"        
        ));

        registerButton.setOnMouseExited(e -> registerButton.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        
        registerButton.setOnAction(e -> registraUtente());

        grid.add(registerButton, 0, 7, 2, 1);

        this.getChildren().add(grid);
    }

    private void backToLogin(Stage stage){
        LoginView loginView = new LoginView(stage);

        Scene scene = new Scene(loginView, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void registraUtente(){
        String user = username.getText().trim();
        String pass = password.getText();
        Home home = new Home();
        boolean registrazione_effettuata = home.Registrazione(user, pass);

        if(user.isEmpty() || pass.isEmpty()){
            mostraMessaggio(AlertType.ERROR, "Registrazione non riuscita", "Compila tutti i campi dati!");
        }
        if(registrazione_effettuata){
            mostraMessaggio(AlertType.CONFIRMATION, "Registrazione effettuata", "Registrazione effettuata: per accedere al tuo account puoi ora effettuare il login");
        }

    }

    private void mostraMessaggio(AlertType tipo, String titolo, String messaggio){
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}
