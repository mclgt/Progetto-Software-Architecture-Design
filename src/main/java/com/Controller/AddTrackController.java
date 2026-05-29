package com.Controller;

import com.Model.Track;
import com.Factory.TrackFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @brief Controller per la finestra di inserimento di un nuovo brano.
 *        Gestisce l'interazione dell'utente con il form per aggiungere una
 *        traccia. Raccoglie i dati dal form, ne verifica
 *        la correttezza e usa la Factory per creare il nuovo oggetto Track.
 */

public class AddTrackController {
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtAlbum;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSave;
    private MainController mainController;

    /**
     * @brief Imposta il riferimento al controller principale. Consente di far
     *        comunicare la schermata con la vista principale
     *        affinché il brano venga mostrato una volta creato.
     * @param mainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @brief gestisce l'annullamento dell'azione mentre questa è in corso.
     * 
     * @param e evento generata dalla pressione del pulsante
     */
    @FXML
    public void handleDelete(ActionEvent e) {
        closeWindow();
    }

    /**
     * @brief Gestisce la procedura del salvataggio del brano, preleva le stringhe,
     *        controlla se anno e durata sono
     *        inseriti correttamente e istanzia il brano se supera i controlli.
     *        Inoltre, intercetta eventuali eccezioni legate
     *        all'input dell'utente.
     * @param e evento generata dalla pressione del pulsante
     */
    @FXML
    public void handleSave(ActionEvent e) {
        try {
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String album = txtAlbum.getText();

            int duration = 0;
            if (txtDuration.getText() != null && !txtDuration.getText().isEmpty()) {
                duration = Integer.parseInt(txtDuration.getText());
            }
            int year = 0;
            if (txtYear.getText() != null && !txtYear.getText().isEmpty()) {
                year = Integer.parseInt(txtYear.getText());
            }
            Track newTrack = TrackFactory.createTrack(title, author, year, genre, duration, album);
            if (mainController != null) {
                mainController.addTrackMainTable(newTrack);
            }
            closeWindow();

        } catch (NumberFormatException ex) {
            viewError("Errore nell'inserimento dei dati numerici",
                    "Assicurarsi di aver inserito numeri nei campi 'Anno' e 'Durata'");
        } catch (IllegalArgumentException ex) {
            viewError("Dati non vallidi", ex.getMessage());
        }
    }

    /**
     * @brief Metodo per la chiusura della finestra corrente
     */
    private void closeWindow() {
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.close();
    }

    /**
     * @brief Mostra un alert di errore all'utente: è una finestra di dialogo che
     *        notifica all'utente l'errore avvenuto. Blocca l'interfaccia finché non
     *        si chiude la finestra.
     * @param title titlo da visualizzare nella barra in alto
     * @param msg   messaggio di errore
     */
    private void viewError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
