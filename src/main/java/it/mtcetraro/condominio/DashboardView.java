package it.mtcetraro.condominio;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

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
        costo.setOnMouseClicked(e -> mostraContenuto(contenutoSpesa(condominio)));

        Button tabella = new Button("Tabelle Millesimali");
        tabella.setStyle( "-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff");
        tabella.setPrefWidth(160);
        tabella.setPrefHeight(35);
        SVGPath tabellaIcona = new SVGPath();
        tabellaIcona.setContent("M3.375 19.5h17.25m-17.25 0a1.125 1.125 0 0 1-1.125-1.125M3.375 19.5h7.5c.621 0 1.125-.504 1.125-1.125m-9.75 0V5.625m0 12.75v-1.5c0-.621.504-1.125 1.125-1.125m18.375 2.625V5.625m0 12.75c0 .621-.504 1.125-1.125 1.125m1.125-1.125v-1.5c0-.621-.504-1.125-1.125-1.125m0 3.75h-7.5A1.125 1.125 0 0 1 12 18.375m9.75-12.75c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125m19.5 0v1.5c0 .621-.504 1.125-1.125 1.125M2.25 5.625v1.5c0 .621.504 1.125 1.125 1.125m0 0h17.25m-17.25 0h7.5c.621 0 1.125.504 1.125 1.125M3.375 8.25c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125m17.25-3.75h-7.5c-.621 0-1.125.504-1.125 1.125m8.625-1.125c.621 0 1.125.504 1.125 1.125v1.5c0 .621-.504 1.125-1.125 1.125m-17.25 0h7.5m-7.5 0c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125M12 10.875v-1.5m0 1.5c0 .621-.504 1.125-1.125 1.125M12 10.875c0 .621.504 1.125 1.125 1.125m-2.25 0c.621 0 1.125.504 1.125 1.125M13.125 12h7.5m-7.5 0c-.621 0-1.125.504-1.125 1.125M20.625 12c.621 0 1.125.504 1.125 1.125v1.5c0 .621-.504 1.125-1.125 1.125m-17.25 0h7.5M12 14.625v-1.5m0 1.5c0 .621-.504 1.125-1.125 1.125M12 14.625c0 .621.504 1.125 1.125 1.125m-2.25 0c.621 0 1.125.504 1.125 1.125m0 1.5v-1.5m0 0c0-.621.504-1.125 1.125-1.125m0 0h7.5");
        //iconaHome.setStyle("-fx-text-fill: #ffffff;");
        tabella.setGraphic(tabellaIcona);
        tabella.setGraphicTextGap(15);
        tabella.setAlignment(Pos.CENTER_LEFT);
        tabella.setOnMouseExited(e -> tabella.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #675c2a; -fx-text-fill: #ffffff"));
        tabella.setOnMouseEntered(e -> tabella.setStyle("-fx-font-size:13; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-color: #f4da68; -fx-text-fill: #ffffff"));
        tabella.setOnMouseClicked(e -> mostraContenuto(contenutoTabella(condominio)));

        menuBar.getChildren().addAll(home, view, costo, tabella);

        sidebar.getChildren().add(menuBar);

        return sidebar;
    }

    private void mostraContenuto(Node nuovoContenuto){
        areaContenuto.getChildren().clear();

        areaContenuto.getChildren().add(nuovoContenuto);
    }

    private Node contenutoTabella(Condominio condominio){
        VBox contenuto = new VBox();
        contenuto.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        contenuto.setPrefWidth(900);
        contenuto.setMinWidth(900);
        contenuto.setMaxWidth(900);
        contenuto.setPadding(new Insets(100, 60, 0, 100));

        GridPane grid = new GridPane();
        grid.setVgap(30);

        Label title = new Label("Tabelle Millesimali: -- " + condominio.getNome());
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #1e293b;");

        grid.add(title, 0, 0, 2, 1);

        //TODO: Implementazione della lista di Tabelle Millesimali
        ObservableList<Appartamento> observableListAppTab = FXCollections.observableArrayList();
        ListView<Appartamento> listViewAppTab = new ListView<>(observableListAppTab);
        listViewAppTab.setPrefSize(600, 500);
        listViewAppTab.setStyle("-fx-cell-size: 30px; -fx-font-size: 15px");



        grid.add(listViewAppTab, 0, 1, 3, 4);

        Button crea = new Button("Crea Tab");
        crea.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        //crea.setOnMouseClicked(e -> mostraContenuto(aggiungiSpesa(condominio)));
        grid.add(crea, 3, 0, 1, 1);
        grid.setHalignment(crea, HPos.RIGHT);

        //caricaAppartamentoTabella(condominio, observableListAppTab);
        ObservableList<TabellaMillesimale> observableListTab = FXCollections.observableArrayList();
        ComboBox<TabellaMillesimale> comboBoxTab = new ComboBox<>(observableListTab);
        comboBoxTab.setPromptText("Scegli Tabella:");
        caricaTable(observableListTab, condominio);
        grid.add(comboBoxTab, 3, 1,1 ,1);
        grid.setHalignment(comboBoxTab, HPos.RIGHT);
        Button aggiungi = new Button("Aggiungi");
        aggiungi.setStyle("-fx-background-color: #1dd8a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        aggiungi.setOnMouseClicked(e -> mostraContenuto(scegliAppartamento(condominio)));
        grid.add(aggiungi, 3, 2, 1, 1);
        grid.setHalignment(aggiungi, HPos.RIGHT);
        
        contenuto.getChildren().add(grid);
        return contenuto;
    }

    private Node scegliAppartamento(Condominio condominio){
        VBox contenuto = new VBox();
        contenuto.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        contenuto.setPrefWidth(900);
        contenuto.setMinWidth(900);
        contenuto.setMaxWidth(900);
        contenuto.setPadding(new Insets(100, 60, 0, 100));

        GridPane grid = new GridPane();
        grid.setVgap(30);

        Label title = new Label("Appartamenti del Condominio: -- " + condominio.getNome());
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
        title.setStyle("-fx-text-fill: #1e293b;");
        grid.add(title, 0, 0, 2, 1);

        Label subtitle = new Label("Seleziona l'appartamento che vuoi aggiungere alla Tabella e poi clicca su ADD");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        subtitle.setStyle("-fx-text-fill: #384a67;");
        grid.add(subtitle, 0, 1, 2, 1);

        Button add = new Button("ADD");
        add.setStyle("-fx-background-color: #d51dd8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        
        grid.add(add, 3, 2, 1, 1);

        ObservableList<Appartamento> lista_appartamenti_obs = FXCollections.observableArrayList();
        ListView<Appartamento> lista_appartamenti = new ListView<>(lista_appartamenti_obs);
        lista_appartamenti.setPrefSize(600, 500);
        lista_appartamenti.setMaxWidth(600);
        lista_appartamenti.setStyle("-fx-cell-size: 30px; -fx-font-size: 15px");
        
        caricaAppartamenti(condominio, lista_appartamenti_obs);

        add.setOnMouseClicked(e->{
            Appartamento appartamento_selezionato = lista_appartamenti.getSelectionModel().getSelectedItem();
            if(appartamento_selezionato==null){
                mostraMessaggio(AlertType.ERROR, "Errore nella selezione", "Non è stato selezionato nessun appartamento. Assicurarsi di selezionare un appartamento presente nella lista!");
            }else{
                mostraContenuto(formAppTab(appartamento_selezionato, condominio));
                /*Home home = new Home();
                boolean appartamento_aggiunto = home.aggiungiAppTab(appartamento_selezionato);
                if(appartamento_aggiunto){
                    mostraMessaggio(AlertType.CONFIRMATION, "Appartamento aggiunto", "L'appartamento selezionato è stato aggiunto alla Tabella scelta!");
                }*/
            }
        });

        

        grid.add(lista_appartamenti, 0, 2, 3, 4);

        contenuto.getChildren().add(grid);
        return contenuto;
    }

    private Node formAppTab(Appartamento appartamento, Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 100, 100, 100));

        GridPane grid = new GridPane();
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );
        grid.setPadding(new Insets(15, 30, 15, 30));
        grid.setVgap(30);
        grid.setHgap(20);

        Label Titolo = new Label("Inserimento in tabella");
        Titolo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        Titolo.setStyle("-fx-text-fill: #1e293b;");
        grid.add(Titolo, 0, 0, 3, 1);

        Label Tabella = new Label("Tabella:");
        Tabella.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField tab = new TextField();
        grid.add(Tabella, 0, 1, 1, 1);
        grid.add(tab, 2, 1, 1, 1);

        Label Millesimi = new Label("Millesimi:");
        Millesimi.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField mille = new TextField();
        mille.setPromptText("123, 89, 238 ...");
        grid.add(Millesimi, 0, 2, 1, 1);
        grid.add(mille, 2, 2, 1, 1);

        Label Tassa = new Label("Tassa:");
        Tassa.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField tass = new TextField();
        tass.setPromptText("Inserire 0");
        grid.add(Tassa, 0, 3, 1, 1);
        grid.add(tass, 2, 3, 1, 1);

        Button ritorna = new Button("Indietro");
        ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        ritorna.setOnMouseExited(e -> ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setOnMouseEntered(e -> ritorna.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setPrefSize(80, 40);
        ritorna.setOnMouseClicked(e -> mostraContenuto(scegliAppartamento(condominio)));
        grid.add(ritorna, 0, 4, 1, 1);
        grid.setHalignment(ritorna, HPos.LEFT);

        Button send = new Button("Invia");
        send.setPrefSize(80, 40);
        send.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        send.setOnMouseEntered(e -> send.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        send.setOnMouseExited(e -> send.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        //send.setOnMouseClicked(e->{});
        grid.add(send, 2, 4, 1, 1);
        grid.setHalignment(send, HPos.RIGHT);


        area.getChildren().add(grid);

        return area;
    }

    private void caricaTable(ObservableList<TabellaMillesimale> observableListTab, Condominio condominio){
        Home home = new Home();
        List<TabellaMillesimale> listaTab = home.getTab(condominio);
        observableListTab.clear();
        observableListTab.addAll(listaTab);
    }

    private Node contenutoSpesa(Condominio condominio){
        VBox contenuto = new VBox();
        contenuto.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        contenuto.setPrefWidth(900);
        contenuto.setMinWidth(900);
        contenuto.setMaxWidth(900);
        contenuto.setPadding(new Insets(100, 0, 0, 100));

        GridPane grid = new GridPane();
        grid.setVgap(30);

        Label title = new Label("Sezione spese: ---- " + condominio.getNome());
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #1e293b;");

        grid.add(title, 0, 0, 1, 1);

        Button aggiungi = new Button("Aggiungi");
        aggiungi.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        aggiungi.setOnMouseClicked(e -> mostraContenuto(aggiungiSpesa(condominio)));
        

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tipologia Spese");
        xAxis.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-fill: #bf1dd8;");
        xAxis.setTickLabelFont(Font.font("System", FontWeight.BOLD, 12));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Importo (€)"); 


        StackedBarChart<String, Number> stackedChart = new StackedBarChart<>(xAxis, yAxis);
        stackedChart.setTitle("Ripartizione delle spese per tipologia");
        stackedChart.setPrefSize(600, 500);
        stackedChart.setStyle("-fx-category-gap: 70px;");

        stackedChart.setAnimated(false);

        XYChart.Series<String, Number> serieOrdinarie = new XYChart.Series<>();
        serieOrdinarie.setName("Spese ordinarie");

        stackedChart.getData().add(serieOrdinarie);

        //IMPLEMENTAZIONE DELLA COMBO BOX PER FILTRI

        ComboBox<Integer> annBox = new ComboBox<>();
        ObservableList<Integer> anni = FXCollections.observableArrayList();

        int annoCorrente = LocalDate.now().getYear();
        for(int anno = annoCorrente; anno>=2020; anno--){
            anni.add(anno);
        }

        annBox.setItems(anni);
        annBox.setPromptText("Seleziona anno:");
        grid.add(annBox, 3, 1, 1, 1);
        
        annBox.setOnAction(e->{
            if(annBox.getValue() == null){
                return;
            }
            int anno_selezionato = annBox.getValue();
            
            Home home = new Home();
            Map<String, Double> map = home.estraiSpesa(condominio, anno_selezionato); 

            serieOrdinarie.getData().clear();

            map.forEach((i , y) -> {
                serieOrdinarie.getData().add(new XYChart.Data<>(i, y));
            });
        });



        grid.add(stackedChart, 0, 1, 2, 2);
        grid.add(aggiungi, 3, 0, 1, 1);

        Button cambia = new Button("Change");
        cambia.setStyle("-fx-background-color: #bf1dd8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        grid.add(cambia, 3, 3, 1, 1);
        cambia.setOnMouseClicked(e->{
            grid.getChildren().remove(stackedChart);
            
            PieChart torta = new PieChart();
            torta.setPrefSize(600, 500);
            torta.setAnimated(false);
            torta.setLegendSide(Side.LEFT);

            int old_year = annoCorrente;
            if(annBox.getValue() != null){
                old_year = annBox.getValue();
            }
            

            annBox.setValue(null);

            annBox.setOnAction(t->{
            if(annBox.getValue() == null){
                return;
            }
            int anno_selezionato = annBox.getValue();
            
            Home home = new Home();
            Map<String, Double> map = home.estraiSpesa(condominio, anno_selezionato); 

            torta.getData().clear();

            map.forEach((i , y) -> {
                torta.getData().add(new PieChart.Data(i, y));
            });
            });


            annBox.setValue(old_year);

            grid.add(torta, 0, 1, 2, 2);

            cambia.setOnMouseClicked(z->{
                grid.getChildren().remove(torta);

                ObservableList<Spesa> observableListSpesa = FXCollections.observableArrayList();
                ListView<Spesa> listViewSpesa = new ListView<>(observableListSpesa);
                listViewSpesa.setPrefSize(600, 500);
                listViewSpesa.setStyle("-fx-cell-size: 30px; -fx-font-size: 15px");

                int anno_viejo = annBox.getValue();
                annBox.setValue(null);
                annBox.setValue(anno_viejo);
                caricaSpesa(condominio, annBox.getValue(), observableListSpesa);

                annBox.setOnAction(t->{
                if(annBox.getValue() == null){
                    return;
                }
                caricaSpesa(condominio, annBox.getValue(), observableListSpesa);
                });

                cambia.setOnMouseClicked(t->{
                    mostraContenuto(contenutoSpesa(condominio));
                });

                grid.add(listViewSpesa, 0, 1, 2, 2);

                listViewSpesa.setOnMouseClicked(t->{
                    if(t.getClickCount() == 2 && t.getButton()==MouseButton.PRIMARY){
                        Spesa spesa_selezionata = listViewSpesa.getSelectionModel().getSelectedItem();
                        if(spesa_selezionata==null){
                            mostraMessaggio(AlertType.ERROR, "Elemento inesistente", "La spesa selezionata non risulta presente nell'elenco. Seleziona una spesa presente nella lista!");
                        }else{
                            mostraContenuto(showSpecificaSpesa(spesa_selezionata, condominio));
                        }
                    }
                });

                listViewSpesa.setOnKeyPressed(t->{
                    if(t.getCode() == KeyCode.D){
                        Spesa spesa_rimossa = listViewSpesa.getSelectionModel().getSelectedItem();
                        if(spesa_rimossa==null){
                            mostraMessaggio(AlertType.ERROR, "Elemento inesistente", "La spesa selezionata non risulta presente nell'elenco. Seleziona una spesa presente nella lista!");
                        }else{
                            Home home = new Home();
                            boolean eliminata = home.eliminaSpesa(spesa_rimossa);
                            if(eliminata){
                                mostraMessaggio(AlertType.CONFIRMATION, "Spesa rimossa", "La spesa da te selezionato è stata rimossa dalla lista delle spese del condominio");
                                mostraContenuto(contenutoSpesa(condominio));
                            }else{
                                mostraMessaggio(AlertType.ERROR, "Rimozione fallita", "E' stato impossibile rimuovere la spesa selezionata!");
                            }
                        }
                    }
                });

            });
        });

        contenuto.getChildren().add(grid);
        return contenuto;
    }

    private Node showSpecificaSpesa(Spesa spesa, Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 100, 100, 100));

        GridPane grid = new GridPane();
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );
        grid.setPadding(new Insets(15, 30, 15, 30));
        grid.setVgap(30);
        grid.setHgap(20);

        Label Titolo = new Label("Spesa selezionata: " + spesa.getFattura());
        Titolo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        Titolo.setStyle("-fx-text-fill: #1e293b;");
        grid.add(Titolo, 0, 0, 3, 1);

        Label Fattura = new Label("Numero fattura:");
        Fattura.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField fattur = new TextField();
        fattur.setText(spesa.getFattura());
        fattur.setEditable(false);
        grid.add(Fattura, 0, 1, 1, 1);
        grid.add(fattur, 2, 1, 1, 1);

        Label Tipologia = new Label("Tipologia:");
        Tipologia.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Tip = new TextField();
        Tip.setText(spesa.getTipologia());
        Tip.setEditable(false);
        grid.add(Tipologia, 0, 2, 1, 1);
        grid.add(Tip, 2, 2, 1, 1);

        Label Cifra = new Label("Cifra:");
        Cifra.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField cifr = new TextField();
        cifr.setText(String.valueOf(spesa.getCifra()));
        cifr.setEditable(false);
        grid.add(Cifra, 0, 3, 1, 1);
        grid.add(cifr, 2, 3, 1, 1);

        Label DataPagamento = new Label("Data pagamento:");
        DataPagamento.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField dat = new TextField();
        dat.setText(String.valueOf(spesa.getData()));
        dat.setEditable(false);
        grid.add(DataPagamento, 0, 4, 1, 1);
        grid.add(dat, 2, 4, 1, 1);

        Label Tabella = new Label("Tabella Millesimale:");
        Tabella.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField tab = new TextField();
        tab.setText(spesa.getTabella());
        tab.setEditable(false);
        grid.add(Tabella, 0, 5, 1, 1);
        grid.add(tab, 2, 5, 1, 1);


        Button modifica = new Button("Modifica");
        modifica.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        modifica.setOnMouseEntered(e -> modifica.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setOnMouseExited(e -> modifica.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setOnMouseClicked(e -> {
            modifica.setText("Salva");
            modifica.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
            modifica.setOnMouseExited(t -> modifica.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            modifica.setOnMouseEntered(t -> modifica.setStyle("-fx-background-color: #23ca09; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            modifica.setPrefSize(80, 40);
            fattur.setEditable(true);
            Tip.setEditable(true);
            cifr.setEditable(true);
            dat.setEditable(true);
            tab.setEditable(true);

            modifica.setOnMouseClicked(t ->{

                String fattura = fattur.getText();
                String tipologia = Tip.getText();
                Double cifra = Double.valueOf(cifr.getText());
                Date data = Date.valueOf(dat.getText());
                String tabella = tab.getText();


                Spesa spesa_modificata = new Spesa(fattura, spesa.getCondominio(), tipologia, cifra, data, tabella);
                Home home = new Home();
                Boolean modificato = home.modificaSpesa(spesa, spesa_modificata);
                if(modificato){
                    mostraMessaggio(AlertType.CONFIRMATION, "Modifica effettuata", "I dati da te inseriti sono stati sovrascritti!");
                    mostraContenuto(contenutoSpesa(condominio));
                }else{
                    mostraMessaggio(AlertType.ERROR, "Errore!", "I dati da te inseriti non sono stati salvati. Ricontrolla i dati inseriti!");
                }
            });
        });

        modifica.setPrefSize(80, 40);
        grid.add(modifica, 1, 6, 1, 1);
        grid.setHalignment(modifica, HPos.LEFT);

        Button ritorna = new Button("Indietro");
        ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        ritorna.setOnMouseExited(y -> ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setOnMouseEntered(y -> ritorna.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setPrefSize(80, 40);
        ritorna.setOnMouseClicked(y -> mostraContenuto(contenutoSpesa(condominio)));
        grid.add(ritorna, 2, 6, 1, 1);
        grid.setHalignment(ritorna, HPos.RIGHT);

        area.getChildren().add(grid);
        return area;
    }

    private void caricaSpesa(Condominio condominio, int anno, ObservableList<Spesa> observableListSpesa){
        Home home = new Home();
        List<Spesa> listone = home.getSpesa(condominio, anno);
        observableListSpesa.clear();
        observableListSpesa.addAll(listone);
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
        lista_appartamenti.setStyle("-fx-cell-size: 30px; -fx-font-size: 15px");
        
        caricaAppartamenti(condominio, lista_appartamenti_obs);

        lista_appartamenti.setOnMouseClicked(e ->{
            if(e.getClickCount()==2 && e.getButton()==MouseButton.PRIMARY){
                Appartamento appartamento_selezionato = lista_appartamenti.getSelectionModel().getSelectedItem();
                if(appartamento_selezionato == null){
                    mostraMessaggio(AlertType.ERROR, "Appartamento inesistente", "Seleziona un appartamento presente nella lista!");
                }else{
                    mostraContenuto(showSpecificaAppartamento(appartamento_selezionato, condominio));
                }
            }
        });

        lista_appartamenti.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.D){
                Appartamento appartamento_selezionato = lista_appartamenti.getSelectionModel().getSelectedItem();
                if(appartamento_selezionato == null){
                    mostraMessaggio(AlertType.ERROR, "Rimozione fallita", "Si è verificato un errore nella rimozione. Verifica di aver selezionato un appartamento disponibile!");
                }else{
                    boolean rimozione = rimozioneAppartamento(appartamento_selezionato);
                    if(rimozione){
                        mostraMessaggio(AlertType.CONFIRMATION, "Appartamento rimosso", "L'appartamento da te selezionato è stato rimosso dalla lista degli appartamenti del condominio");
                        mostraContenuto(contenutoView(condominio));
                    }else{
                        mostraMessaggio(AlertType.ERROR, "Rimozione fallita", "E' stato impossibile rimuovere l'appartamento selezionato!");
                    }
                }

            }
        });

        Button aggiungi = new Button("Aggiungi");
        aggiungi.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        aggiungi.setOnMouseClicked(e -> showInserimentoAppartamento(condominio));

        Button cambio = new Button("Proprietari");
        cambio.setPrefSize(90, 40);
        cambio.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        cambio.setOnMouseExited(e -> cambio.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        cambio.setOnMouseEntered(e -> cambio.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        cambio.setOnMouseClicked(e -> {
            ObservableList<Proprietario> lista_proprietari_obs = FXCollections.observableArrayList(); 
            ListView<Proprietario> lista_proprietari = new ListView<>(lista_proprietari_obs);
            lista_proprietari.setPrefSize(600, 500);
            lista_proprietari.setMaxWidth(600);
            lista_proprietari.setStyle("-fx-cell-size: 30px; -fx-font-size: 15px");
            caricaProprietari(condominio, lista_proprietari_obs);

            grid.getChildren().remove(lista_appartamenti);
            grid.add(lista_proprietari,  0, 1, 2, 1);
            title.setText("Proprietari nel condominio:");

            lista_proprietari.setOnMouseClicked(f ->{
                if(f.getClickCount() == 2 && f.getButton() == MouseButton.PRIMARY){
                    Proprietario proprietario_selezionato = lista_proprietari.getSelectionModel().getSelectedItem();
                    if(proprietario_selezionato != null){
                        mostraContenuto(showSpecificaProprietario(proprietario_selezionato, condominio));
                    }else{
                        mostraMessaggio(AlertType.ERROR, "Errore di selezione", "Il proprietario selezionato non è disponibile. Controlla di aver selezionato un proprietario presente!");
                    }
                }
            });



        });

        Button cambio_appartamenti = new Button("Appartamenti");
        cambio_appartamenti.setPrefSize(110, 40);
        cambio_appartamenti.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        cambio_appartamenti.setOnMouseExited(e -> cambio_appartamenti.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        cambio_appartamenti.setOnMouseEntered(e -> cambio_appartamenti.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        cambio_appartamenti.setOnMouseClicked(e -> mostraContenuto(contenutoView(condominio)));

        grid.add(cambio_appartamenti, 0, 2, 1, 1);
        grid.setHalignment(cambio_appartamenti, HPos.LEFT);

        grid.add(title, 0, 0, 1, 1);
        grid.add(aggiungi, 1, 0, 1, 1);
        grid.setHalignment(aggiungi, HPos.RIGHT);
        grid.add(lista_appartamenti, 0, 1, 2, 1);
        grid.add(cambio, 1, 2, 1, 1);
        grid.setHalignment(cambio, HPos.RIGHT);

        contenuto.getChildren().add(grid);


        return contenuto;
    }

    private Node aggiungiSpesa(Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 100, 100, 100));

        GridPane grid = new GridPane();
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );
        grid.setPadding(new Insets(15, 30, 15, 30));
        grid.setVgap(30);
        grid.setHgap(20);

        Label Titolo = new Label("Spesa da aggiungere:");
        Titolo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        Titolo.setStyle("-fx-text-fill: #1e293b;");
        grid.add(Titolo, 0, 0, 3, 1);

        Label fattura = new Label("Numero fattura:");
        fattura.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField fattur = new TextField();
        fattur.setPrefWidth(200);
        fattur.setPromptText("Numero fattura...");
        grid.add(fattura, 0, 1, 1, 1);
        grid.add(fattur, 2, 1, 1, 1);

        Label tipologia = new Label("Tipologia:");
        tipologia.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField tipo = new TextField();
        tipo.setPrefWidth(200);
        tipo.setPromptText("Giardino, luci. manutenzione ...");
        grid.add(tipologia, 0, 2, 1, 1);
        grid.add(tipo, 2, 2, 1, 1);

        Label cifra = new Label("Cifra:");
        cifra.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField cifr = new TextField();
        cifr.setPrefWidth(200);
        cifr.setPromptText("200.95 ...");;
        grid.add(cifra, 0, 3, 1, 1);
        grid.add(cifr, 2, 3, 1, 1);

        Label data = new Label("Data del pagamento:");
        data.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField dat = new TextField();
        dat.setPrefWidth(200);
        dat.setPromptText("YYYY-MM-DD");;
        grid.add(data, 0, 4, 1, 1);
        grid.add(dat, 2, 4, 1, 1);

        Label tabella = new Label("Tabella millesimale:");
        tabella.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField tab = new TextField();
        tab.setPrefWidth(200);
        tab.setPromptText("A, B ...");;
        grid.add(tabella, 0, 5, 1, 1);
        grid.add(tab, 2, 5, 1, 1);

        Button send = new Button("Invia");
        send.setPrefSize(80, 40);
        send.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        send.setOnMouseEntered(e -> send.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        send.setOnMouseExited(e -> send.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        send.setOnMouseClicked(e ->{
            String nome_condominio = condominio.getNome();
            String num_fattura = fattur.getText();
            String tip = tipo.getText();
            double price = Double.parseDouble(cifr.getText());
            String data_testo = dat.getText();
            Date data_pagamento = Date.valueOf(data_testo);
            String tabel = tab.getText();

            Spesa spesa = new Spesa(num_fattura, nome_condominio, tip, price, data_pagamento, tabel);
            Home home = new Home();
            boolean spesaInserita = home.inserisciSpesa(spesa);
            if(spesaInserita){
                mostraMessaggio(AlertType.CONFIRMATION, "Inserimento effettuato", "La spesa è stata inserita correttamente");
                mostraContenuto(contenutoSpesa(condominio));
            }else{
                mostraMessaggio(AlertType.ERROR, "Errore", "L'inserimento non è andato a buon fine. Controllare i dati inseriti!");
            }
        });
        grid.add(send, 2, 6, 1, 1);
        grid.setHalignment(send, HPos.RIGHT);

        Button ritorna = new Button("Indietro");
        ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        ritorna.setOnMouseExited(y -> ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setOnMouseEntered(y -> ritorna.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setPrefSize(80, 40);
        ritorna.setOnMouseClicked(y -> mostraContenuto(contenutoSpesa(condominio)));
        grid.add(ritorna, 0, 6, 1 ,1);
        grid.setHalignment(ritorna, HPos.LEFT);

        area.getChildren().add(grid);
        return area;
    }


    private void caricaProprietari(Condominio condominio, ObservableList<Proprietario> lista_propretari_obs){
        Home home = new Home();
        List<Proprietario> proprietari = home.showProprietari(condominio);
        lista_propretari_obs.clear();
        lista_propretari_obs.addAll(proprietari);
    }

    private void caricaAppartamenti(Condominio condominio, ObservableList<Appartamento> lista_appartamenti_obs){
        Home home = new Home();
        List<Appartamento> Appartamenti = home.showAppartamenti(condominio);
        lista_appartamenti_obs.clear();
        lista_appartamenti_obs.addAll(Appartamenti);
    }

    private Node showSpecificaProprietario(Proprietario proprietario, Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 100, 100, 100));

        GridPane grid = new GridPane();
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );
        grid.setPadding(new Insets(15, 30, 15, 30));
        grid.setVgap(30);
        grid.setHgap(20);

        Label Titolo = new Label("Proprietario: " + proprietario.getNome() + " " + proprietario.getNome());
        Titolo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        Titolo.setStyle("-fx-text-fill: #1e293b;");
        grid.add(Titolo, 0, 0, 3, 1);

        Label CF = new Label("Codice fiscale:");
        CF.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField codic = new TextField();
        codic.setText(proprietario.getCodice_fiscale());
        codic.setEditable(false);
        grid.add(CF, 0, 1, 1, 1);
        grid.add(codic, 2, 1, 1, 1);

        Label Nome = new Label("Nome:");
        Nome.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Nom = new TextField();
        Nom.setText(proprietario.getNome());
        Nom.setEditable(false);
        grid.add(Nome, 0, 2, 1, 1);
        grid.add(Nom, 2, 2, 1, 1);

        Label Cognome = new Label("Cognome:");
        Cognome.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Cog = new TextField();
        Cog.setText(proprietario.getCognome());
        Cog.setEditable(false);
        grid.add(Cognome, 0, 3, 1, 1);
        grid.add(Cog, 2, 3, 1, 1);

        Label Telefono = new Label("Telefono:");
        Telefono.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Tel = new TextField();
        Tel.setText(proprietario.getTelefono());
        Tel.setEditable(false);
        grid.add(Telefono, 0, 4, 1, 1);
        grid.add(Tel, 2, 4, 1, 1);

        Label Email = new Label("Email:");
        Email.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Mail = new TextField();
        Mail.setText(proprietario.getEmail());
        Mail.setEditable(false);
        grid.add(Email, 0, 5, 1, 1);
        grid.add(Mail, 2, 5, 1, 1);

        Label Residenza = new Label("Residenza:");
        Residenza.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Res = new TextField();
        Res.setText(proprietario.getResidenza());
        Res.setEditable(false);
        grid.add(Residenza, 0, 6, 1, 1);
        grid.add(Res, 2, 6, 1, 1);

        Button modifica = new Button("Modifica");
        modifica.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        modifica.setOnMouseEntered(e -> modifica.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setOnMouseExited(e -> modifica.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setOnMouseClicked(e -> {
            modifica.setText("Salva");
            modifica.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
            modifica.setOnMouseExited(t -> modifica.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            modifica.setOnMouseEntered(t -> modifica.setStyle("-fx-background-color: #23ca09; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            modifica.setPrefSize(80, 40);
            codic.setEditable(true);
            Nom.setEditable(true);
            Cog.setEditable(true);
            Tel.setEditable(true);
            Mail.setEditable(true);
            Res.setEditable(true);

            modifica.setOnMouseClicked(t ->{

                String cod = codic.getText();
                String nome = Nom.getText();
                String cognome = Cog.getText();
                String telefono = Tel.getText();
                String email = Mail.getText();
                String residenza = Res.getText();

                Proprietario proprietario_modificato = new Proprietario(cod, nome, cognome, telefono, email, residenza);
                Home home = new Home();
                Boolean modificato = home.modificaProprietario(proprietario, proprietario_modificato);
                if(modificato){
                    mostraMessaggio(AlertType.CONFIRMATION, "Modifica effettuata", "I dati da te inseriti sono stati sovrascritti!");
                    mostraContenuto(contenutoView(condominio));
                }else{
                    mostraMessaggio(AlertType.ERROR, "Errore!", "I dati da te inseriti non sono stati salvati. Ricontrolla i dati inseriti!");
                }
            });
        });

        modifica.setPrefSize(80, 40);
        grid.add(modifica, 1, 7, 1, 1);
        grid.setHalignment(modifica, HPos.LEFT);

        Button ritorna = new Button("Indietro");
        ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        ritorna.setOnMouseExited(y -> ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setOnMouseEntered(y -> ritorna.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setPrefSize(80, 40);
        ritorna.setOnMouseClicked(y -> mostraContenuto(contenutoView(condominio)));
        grid.add(ritorna, 2, 7, 1, 1);
        grid.setHalignment(ritorna, HPos.RIGHT);

        area.getChildren().add(grid);
        return area;
    }


    private Node showSpecificaAppartamento(Appartamento appartamento, Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 100, 100, 100));

        GridPane grid = new GridPane();
        grid.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );
        grid.setPadding(new Insets(15, 30, 15, 30));
        grid.setVgap(30);
        grid.setHgap(20);

        Label Titolo = new Label("Appartamento interno: " + appartamento.getInterno() + " - " + "Condominio: " + appartamento.getCondominio());
        Titolo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        Titolo.setStyle("-fx-text-fill: #1e293b;");
        grid.add(Titolo, 0, 0, 3, 1);

        Label Interno = new Label("Interno:");
        Interno.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Intern = new TextField();
        Intern.setText(appartamento.getInterno());
        Intern.setEditable(false);
        grid.add(Interno, 0, 1, 1, 1);
        grid.add(Intern, 2, 1, 1, 1);

        Label Subalterno = new Label("Subalterno:");
        Subalterno.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Subalt = new TextField();
        Subalt.setText(appartamento.getSubalterno());
        Subalt.setEditable(false);
        grid.add(Subalterno, 0, 2, 1, 1);
        grid.add(Subalt, 2, 2, 1, 1);

        Label Foglio = new Label("Foglio:");
        Foglio.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Fog = new TextField();
        Fog.setText(appartamento.getFoglio());
        Fog.setEditable(false);
        grid.add(Foglio, 0, 3, 1, 1);
        grid.add(Fog, 2, 3, 1, 1);

        Label Particella = new Label("Particella:");
        Particella.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Part = new TextField();
        Part.setText(appartamento.getParticella());
        Part.setEditable(false);
        grid.add(Particella, 0, 4, 1, 1);
        grid.add(Part, 2, 4, 1, 1);

        Label SpesaPersonale = new Label("Spesa personale:");
        SpesaPersonale.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Spes = new TextField();
        Spes.setText(String.valueOf(appartamento.getSpesapersonale()));
        Spes.setEditable(false);
        grid.add(SpesaPersonale, 0, 5, 1, 1);
        grid.add(Spes, 2, 5, 1, 1);

        Label Proprietario = new Label("Proprietario:");
        Proprietario.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155; -fx-font-size: 13px");
        TextField Prop = new TextField();
        Prop.setText(appartamento.getProprietario());
        Prop.setEditable(false);
        grid.add(Proprietario, 0, 6, 1, 1);
        grid.add(Prop, 2, 6, 1, 1);

        Button modifica = new Button("Modifica");
        modifica.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;");
        modifica.setOnMouseEntered(e -> modifica.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setOnMouseExited(e -> modifica.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 5px; -fx-cursor: hand;"
        ));
        modifica.setPrefSize(80, 40);

        Button ritorna = new Button("Indietro");
        ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
        ritorna.setOnMouseExited(y -> ritorna.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setOnMouseEntered(y -> ritorna.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
        ritorna.setPrefSize(80, 40);
        ritorna.setOnMouseClicked(y -> mostraContenuto(contenutoView(condominio)));
        grid.add(ritorna, 2, 7, 1, 1);
        grid.setHalignment(ritorna, HPos.RIGHT);

        modifica.setOnMouseClicked(e -> {
            Intern.setEditable(true);
            Subalt.setEditable(true);
            Fog.setEditable(true);
            Part.setEditable(true);
            Spes.setEditable(true);
            Prop.setEditable(true);

            grid.getChildren().remove(modifica);
            Button Salva = new Button("Salva");
            Salva.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
            Salva.setOnMouseExited(t -> Salva.setStyle("-fx-background-color: #0f5405; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            Salva.setOnMouseEntered(t -> Salva.setStyle("-fx-background-color: #23ca09; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            Salva.setPrefSize(80, 40);
            Salva.setOnMouseClicked(t ->{
                String inter = Intern.getText();
                String sub = Subalt.getText();
                String fog = Fog.getText();
                String part = Part.getText();
                String spesPers = Spes.getText();
                int numeroSpesa = Integer.parseInt(spesPers);
                String pro = Prop.getText();
                String condom = condominio.getNome();

                Appartamento appartamento_modificato = new Appartamento(condom, inter, sub, fog, part, numeroSpesa, pro);
                Home home = new Home();
                boolean modifica_riuscita = home.appartamentoModificato(appartamento, appartamento_modificato);
                if(modifica_riuscita){
                    mostraMessaggio(AlertType.CONFIRMATION, "Appartamento modificato", "L'appartamento è stato modificato con le modifiche suggerite");
                    mostraContenuto(contenutoView(condominio));
                }else{
                    mostraMessaggio(AlertType.ERROR, "Errore nella modifica", "Le modifiche inserite non sono state salvate. Controllare di aver salvato il proprietario prima di aggiungergli un appartamento");
                }

                
            });
            grid.add(Salva, 2, 7, 1, 1);
            grid.setHalignment(Salva, HPos.CENTER);

            Button Back = new Button("Indietro");
            Back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px");
            Back.setOnMouseExited(z -> Back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            Back.setOnMouseEntered(z -> Back.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 13px"));
            Back.setPrefSize(80, 40);
            Back.setOnMouseClicked(z -> mostraContenuto(showSpecificaAppartamento(appartamento, condominio)));
            grid.add(Back, 2, 7, 1, 1);
            grid.setHalignment(Back, HPos.RIGHT);
        });

        grid.add(modifica, 2, 7, 1, 1);
        grid.setHalignment(modifica, HPos.CENTER);

        area.getChildren().add(grid);
        return area;
    }

    private void showInserimentoAppartamento(Condominio condominio){
        VBox area = new VBox();
        area.setStyle("-fx-background-color: #bdbcbc; -fx-background-radius: 6px");
        area.setPrefWidth(900);
        area.setMinWidth(900);
        area.setMaxWidth(900);
        area.setPadding(new Insets(100, 0, 0, 100));

        GridPane grid = new GridPane();
        grid.setVgap(15);
        
        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints colspazio = new ColumnConstraints();
        colspazio.setPrefWidth(50);
        grid.getColumnConstraints().addAll(col0, col1, colspazio);
        
        Label proprietario = new Label("Codice Fiscale del proprietario:");
        proprietario.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField prop = new TextField();
        prop.setPromptText("CF...");
        prop.setPrefWidth(250);
        prop.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(proprietario, 3, 0, 1, 1);
        grid.add(prop, 3, 1, 2, 1);

        Label telefono = new Label("Numero telefonico:");
        telefono.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField telefon = new TextField();
        telefon.setPromptText("Telefono...");
        telefon.setPrefWidth(250);
        telefon.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(telefono, 3, 2, 1, 1);
        grid.add(telefon, 3, 3, 2, 1);

        Label email = new Label("Email:");
        email.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField mail = new TextField();
        mail.setPromptText("Email...");
        mail.setPrefWidth(250);
        mail.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(email, 3, 4, 1, 1);
        grid.add(mail, 3, 5, 2, 1);

        Label residenza = new Label("Residenza:");
        residenza.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField residenz = new TextField();
        residenz.setPromptText("Residenza...");
        residenz.setPrefWidth(250);
        residenz.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(residenza, 3, 6, 1, 1);
        grid.add(residenz, 3, 7, 2, 1);

        Label nome = new Label("Nome:");
        nome.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField nom = new TextField();
        nom.setPromptText("Nome...");
        nom.setPrefWidth(250);
        nom.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(nome, 3, 8, 1, 1);
        grid.add(nom, 3, 9, 2, 1);

        Label cognome = new Label("Cognome:");
        cognome.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField cognom = new TextField();
        cognom.setPromptText("Cognome...");
        cognom.setPrefWidth(250);
        cognom.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(cognome, 3, 10, 1, 1);
        grid.add(cognom, 3, 11, 2, 1);

        //PARTE A SINISTRA DEL FORM 


        Label interno = new Label("Interno:");
        interno.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField intern = new TextField();
        intern.setPromptText("Interno...");
        intern.setPrefWidth(250);
        intern.setStyle("\"-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;\"");

        grid.add(interno, 0, 0, 1, 1);
        grid.add(intern, 0, 1, 2, 1);

        Label subalterno = new Label("Subalterno:");
        subalterno.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField subalt = new TextField();
        subalt.setPromptText("Subalterno...");
        subalt.setPrefWidth(250);
        subalt.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(subalterno, 0, 2, 1, 1);
        grid.add(subalt, 0, 3, 2, 1);

        Label foglio = new Label("Foglio:");
        foglio.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField fogl = new TextField();
        fogl.setPromptText("Foglio...");
        fogl.setPrefWidth(250);
        fogl.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(foglio, 0, 4, 1, 1);
        grid.add(fogl, 0, 5, 2, 1);

        Label particella = new Label("Particella:");
        particella.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField particel = new TextField();
        particel.setPromptText("Particella...");
        particel.setPrefWidth(250);
        particel.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(particella, 0, 6, 1, 1);
        grid.add(particel, 0, 7, 2, 1);

        Label spesapersonale = new Label("Spesa personale:");
        spesapersonale.setStyle("-fx-font-weight: bold; -fx-text-fill: #334155");
        TextField spesa = new TextField();
        spesa.setPromptText("Se nulla inserisci 0...");
        spesa.setPrefWidth(250);
        spesa.setStyle("-fx-padding: 8px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px; -fx-background-radius: 4px;");

        grid.add(spesapersonale, 0, 8, 1, 1);
        grid.add(spesa, 0, 9, 2, 1);



        Button back = new Button("Indietro");
        back.setPrefWidth(90);
        back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        back.setOnMouseExited(e -> back.setStyle("-fx-background-color: #6c1010; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));
        back.setOnMouseEntered(e -> back.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));
        back.setOnMouseClicked(e -> mostraContenuto(contenutoView(condominio)));

        Button inviaAppartamento = new Button("Invia");
        inviaAppartamento.setPrefWidth(90);
        inviaAppartamento.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        inviaAppartamento.setOnMouseExited(e -> inviaAppartamento.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: #ffffff; -fx-font-weight: bold;"));
        inviaAppartamento.setOnMouseEntered(e -> inviaAppartamento.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: bold;"));
        inviaAppartamento.setOnMouseClicked(e -> {
            String inter = intern.getText();
            String sub = subalt.getText();
            String fog = fogl.getText();
            String part = particel.getText();
            String spesPers = spesa.getText();
            int numeroSpesa = Integer.parseInt(spesPers);
            String pro = prop.getText();
            String condom = condominio.getNome();
            String name = nom.getText();
            String lastname = cognom.getText();
            String phone = telefon.getText();
            String posta = mail.getText();
            String residence = residenz.getText();

            Proprietario owner = new Proprietario(pro, name, lastname, phone, posta, residence);
            Appartamento appartamento = new Appartamento(condom, inter, sub, fog, part, numeroSpesa, pro);

            Home home = new Home();
            boolean inserimento_proprietario_effettuato = home.inserimentoProp(owner);
            if(inserimento_proprietario_effettuato){
                boolean inserimento_effettuato = home.appartamentoInserito(appartamento);
                if(inserimento_effettuato){
                    mostraMessaggio(AlertType.CONFIRMATION, "Inserimento Effettuato", "L'appartamento è stato inserito con successo!");
                    mostraContenuto(contenutoView(condominio));
                }else{
                mostraMessaggio(AlertType.ERROR, "Inserimento Fallito", "L'inserimento dell'appartamento è fallito. Ricontrolla i dati inseriti!");
                }
            }else{
                mostraMessaggio(AlertType.ERROR, "Errore Proprietario", "Si è verificato un errore con l'inserimento del proprietario!");
            }

        });

        grid.add(back, 0, 12, 1, 1);
        grid.add(inviaAppartamento, 1, 12, 1, 1);
        grid.setHalignment(inviaAppartamento, HPos.RIGHT);

        area.getChildren().add(grid);

        mostraContenuto(area);
    }

    private boolean rimozioneAppartamento(Appartamento appartamento){
        Home home = new Home();
        return home.deleteAppartamento(appartamento);
    }

    private void mostraMessaggio(AlertType type, String titolo, String messaggio){
        Alert alert = new Alert(type);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

}
