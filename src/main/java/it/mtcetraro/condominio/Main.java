package it.mtcetraro.condominio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    //il primarystage viene creato automaticamente da JFX ed è "IL PALCO" dove poi verranno mostrate le pagine, che sono messe come "SCENE"
    public void start(Stage primaryStage){
        System.out.println("Avvio di CondominioManager...");
        LoginView loginView = new LoginView();

        //Qui creiamo la SCENA e gli impostiamo la pagina che vogliamo
        Scene scene = new Scene(loginView, 450, 500);
        primaryStage.setTitle("Condominio-Manager Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        //Nelle app JFX il metodo launch(args) inizializza il motore grafico e chiama automaticamente il metodo start()
        launch(args);
    }
}