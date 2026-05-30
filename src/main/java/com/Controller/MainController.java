package com.Controller;

import java.io.IOException;
import java.util.Optional;

import com.Model.Track;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.awt.event.MouseEvent;

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
            Add_ModTrackController controller = fxmlLoader.getController();
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

    @FXML
    public void openModifyTrackView(ActionEvent ev) {
        Track selectedTrack = trackTable.getSelectionModel().getSelectedItem();
        if (selectedTrack == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna selezione");
            alert.setContentText("Seleziona una traccia dalla tabella da modificare.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/View/ModifyTrackView.fxml"));
            Parent p = fxmlLoader.load();
            Add_ModTrackController controller = fxmlLoader.getController();
            controller.setMainController(this);
            controller.setTrack(selectedTrack);
            Stage stage = new Stage();
            stage.setTitle("Modifica Brano -" + selectedTrack.getTitle());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(p));
            stage.showAndWait();
            trackTable.getSelectionModel().clearSelection();

        } catch (IOException ex) {
            System.err.print("Errore nel caricamento della finestra:" + ex.getMessage());
        }
    }

    @FXML
    public void handleBackgroundClick(MouseEvent event) {
        trackTable.getSelectionModel().clearSelection();
    }

}
