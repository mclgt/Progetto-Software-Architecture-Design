package com.Controller;

import com.Model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * @brief Controller principale dell'applicazione, gestisce la schermata
 *        principale e si occupa della visualizzazione della tabella dei brani
 *        musicali e dell'interazione con l'utente.
 */

public class MainController {
    @FXML
    private Button btnAddTrack;
    @FXML
    private TableView<Track> trackTable;
    @FXML
    private TableColumn<Track, String> titleCol;
    @FXML
    private TableColumn<Track, String> authorCol;
    @FXML
    private TableColumn<Track, String> genreCol;

    private ObservableList<Track> trackList = FXCollections.observableArrayList();

    /***
     * @brief Inizializza i componenti dell'interfaccia grafica
     */
    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        trackTable.setItems(trackList);
    }

    /**
     * @brief Inserisce un nuovo brano nella tabella principale
     * @param track oggetto appena creato da aggiungere alla collezione
     */
    public void addTrackMainTable(Track track) {
        trackList.add(track);
    }

    /**
     * @brief Apre la finestra di dialogo per l'aggiunta del nuovo brano, l'evento
     *        è generato a partire dalla pressione sul pulsante "Aggiungi Brano"
     * @param ev evento generato dalla pressione del pulsante
     */
    @FXML
    public void openAddTrackWindow(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/View/AddTrackView.fxml"));
            Parent p = fxmlLoader.load();
            AddTrackController controller = fxmlLoader.getController();
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Aggiungi Nuovo Brano");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(p));
            stage.showAndWait();

        } catch (IOException ex) {
            System.err.print("Errore nel caricamento della finestra:" + ex.getMessage());
        }
    }

}
