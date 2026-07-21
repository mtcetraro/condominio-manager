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

public class LoginView extends VBox {

    private TextField userField;
    private PasswordField passField;
    private Button loginButton;

    public LoginView() {
        // 1. Configurazione del contenitore esterno (VBox) per centrare il form
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setStyle("-fx-background-color: #f4f9f8;"); // Sfondo grigio chiaro pulito

        // 2. Creazione della griglia per il modulo di login
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

        // 3. Titolo Principale
        Label titleLabel = new Label("Gestionale Condominio");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e293b;");
        
        Label subtitleLabel = new Label("Accedi al tuo account amministratore");
        subtitleLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        subtitleLabel.setStyle("-fx-text-fill: #64748b;");

        // Aggiungiamo i titoli al GridPane (Colonna 0, Riga 0 e 1, occupano 2 colonne)
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(subtitleLabel, 0, 1, 2, 1);

        // 4. Campo Username / Email
        Label userLabel = new Label("Username / Email:");
        userLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155;");
        userField = new TextField();
        userField.setPromptText("inserisci la tua email");
        userField.setPrefWidth(250);
        userField.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(userLabel, 0, 2, 2, 1);
        grid.add(userField, 0, 3, 2, 1);

        // 5. Campo Password
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155;");
        passField = new PasswordField();
        passField.setPromptText("••••••••");
        passField.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(passLabel, 0, 4, 2, 1);
        grid.add(passField, 0, 5, 2, 1);

        // 6. Pulsante Login
        loginButton = new Button("Accedi");
        loginButton.setDefaultButton(true); // Si attiva premendo Invio sulla tastiera
        loginButton.setPrefWidth(250);
        
        // Stile del pulsante (Blu moderno con testo bianco)
        loginButton.setStyle(
            "-fx-background-color: #2563eb; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10px; " +
            "-fx-background-radius: 5px; " +
            "-fx-cursor: hand;"
        );

        // Effetto Hover sul pulsante (cambia colore al passaggio del mouse)
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));

        grid.add(loginButton, 0, 6, 2, 1);

        // 7. Gestione Evento del Click sul Pulsante
        loginButton.setOnAction(e -> gestisciLogin());

        // Inseriamo la griglia all'interno del VBox
        this.getChildren().add(grid);
    }

    private void gestisciLogin() {
        String username = userField.getText().trim();
        String password = passField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            mostraMessaggio(AlertType.WARNING, "Attenzione", "Compila tutti i campi prima di proseguire!");
            return;
        }

        // =========================================================
        // RICHIAMA QUI IL TUO METODO DEL CORE JAVA PER IL LOGIN!
        // Esempio:
        // boolean autenticato = authService.login(username, password);
        // =========================================================

        System.out.println("Tentativo di accesso con username: " + username);

        // Simula login riuscito
        mostraMessaggio(AlertType.INFORMATION, "Login Effettuato", "Benvenuto nel gestionale, " + username + "!");
    }

    private void mostraMessaggio(AlertType tipo, String titolo, String messaggio) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}