package com.Controller;

import java.io.IOException;
import java.util.Optional;

import com.Model.Track;
import com.Model.Library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private VBox detailPanel;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblAuthor;
    @FXML
    private Label lblAlbum;
    @FXML
    private Label lblGenre;
    @FXML
    private Label lblDuration;
    @FXML
    private Label lblYear;

    private Library trackList = new Library();

    /***
     * @brief Inizializza i componenti dell'interfaccia grafica. Effettua il binding
     *        tra le colonne della tabella e le StringProperty del modello Track,
     *        sfruttando il pattern Observer per consentire l'aggiornamento in tempo
     *        reale.
     */
    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        trackTable.setItems(trackList.getLibrary());
        detailPanel.setVisible(false);
        trackTable.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            updateDetailPanel(newVal);
        });
    }

    /**
     * @brief Inserisce un nuovo brano nella tabella principale
     * @param track oggetto appena creato da aggiungere alla collezione
     */
    public void addTrackMainTable(Track track) {
        trackList.addTrack(track);
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

    /**
     * @brief Apre la finestra di dialogo per la modifica di un brano esistente.
     *        Recupera il brano selezionato e lo passa al controller. Se nessun
     *        brano è selezionato, mostra un avviso all'utente.
     * @param ev evento generato dalla pressione del pulsante
     */
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

    /**
     * @brief Consente di deselezionare la riga della tabella se l'utente clicca su
     *        un'area vuota.
     * @param ev evento di pressione su una qualsiasi parte dello sfondo
     *           dell'interfaccia
     */
    @FXML
    public void handleBackgroundClick(MouseEvent ev) {
        trackTable.getSelectionModel().clearSelection();
    }

    /**
     * @brief Rimuove il brano selezionato dalla tabella principale, l'evento è
     *        generato a partire dalla pressione sul pulsante "Rimuovi Brano", viene
     *        mostrato a
     *        schermo un messaggio di conferma. Nel caso in cui non sia selezionato
     *        alcun brano,
     *        viene mostrato un messaggio di avviso.
     * @param event evento generato dalla pressione del pulsante
     */

    @FXML

    private void handleRemoveTrack(ActionEvent event) {
        Track selectedTrack = this.trackTable.getSelectionModel().getSelectedItem();
        if (selectedTrack != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma rimozione");
            alert.setHeaderText("Sei sicuro di voler rimuovere la traccia selezionata?");
            alert.setContentText(selectedTrack.getTitle());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                this.trackList.removeTrack(selectedTrack);
                this.trackTable.getSelectionModel().clearSelection();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText("Nessuna traccia selezionata");
            alert.setContentText("Seleziona una traccia da rimuovere.");
            alert.showAndWait();
        }
    }

    /**
     * @brief Aggiorna il pannello di dettaglio in base alla traccia selezionato. Se
     *        non viene selezionata alcuna traccia, il pannello scompare.
     * @param t traccia selezionata dalla tabella
     */
    private void updateDetailPanel(Track t) {
        if (t == null) {
            detailPanel.setVisible(false);
        } else {
            detailPanel.setVisible(true);
            lblTitle.setText(t.getTitle());
            lblAuthor.setText(t.getAuthor());
            lblAlbum.setText(t.getAlbum());
            lblGenre.setText(t.getGenre());
            lblYear.setText(t.getYear() == 0 ? "-" : String.valueOf(t.getYear()));
            int min = t.getDuration() / 60;
            int sec = t.getDuration() % 60;
            lblDuration.setText(String.format("%d:%d", min, sec));
        }
    }

}
