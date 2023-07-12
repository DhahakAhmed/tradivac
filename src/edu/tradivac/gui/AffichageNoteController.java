/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;


import edu.tradivac.api.EmailSender;
import edu.tradivac.entities.NotePaquet;
import edu.tradivac.services.NotePaquetCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichageNoteController implements Initializable {

    @FXML
    private TableView<NotePaquet> TableNote;
    @FXML
    private TableColumn<NotePaquet, Integer> id_note_paquet;
    @FXML
    private TableColumn<NotePaquet, Integer> id_user;
    @FXML
    private TableColumn<NotePaquet, Integer> id_paquet;
    @FXML
    private TableColumn<NotePaquet, String> nb_etoiles;
    @FXML
    private TableColumn<NotePaquet, String> commentaire;
    @FXML
    private TextField tfnbetoile;
    @FXML
    private TextField tfcom;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        NotePaquetCrud rc = new NotePaquetCrud();
        ObservableList<NotePaquet> liste = FXCollections.observableList(rc.displayEntities());
        id_note_paquet.setCellValueFactory(new PropertyValueFactory<>("id_note_paquet"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        id_paquet.setCellValueFactory(new PropertyValueFactory<>("id_paquet"));
        nb_etoiles.setCellValueFactory(new PropertyValueFactory<>("nb_etoiles"));
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        TableNote.setItems(liste);
        System.out.println(liste);
    }

    @SuppressWarnings("empty-statement")
    private void add(ActionEvent event) {
        String textetoile = tfnbetoile.toString();
        String Commentaire = tfcom.toString();

        if (textetoile.isEmpty() || Commentaire.isEmpty()) {
            // Display an alert with a button for empty input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Input cannot be empty");

            // Create a custom OK button
            ButtonType okButton = new ButtonType("OK");

            // Set the button type for the alert
            alert.getButtonTypes().setAll(okButton);

            // Show the alert and wait for button click
            alert.showAndWait();

            return; // Exit the method
        }

        // Check if the input is a number between 0 and 5
        if (!textetoile.matches("[0-5]")) {
            // Display an alert with a button for invalid input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a number between 0 and 5");

            // Create a custom OK button
            ButtonType okButton = new ButtonType("OK");

            // Set the button type for the alert
            alert.getButtonTypes().setAll(okButton);

            // Show the alert and wait for button click
            alert.showAndWait();

            return; // Exit the method
        }

        // Create a regular expression to match numbers between 0 and 5
        String regex = "[0-5]";

        // Create a TextFormatter that only allows input matching the regular expression
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(regex)) {
                return change;
            }
            return null;
        });

        // Apply the TextFormatter to the TextField
        tfnbetoile.setTextFormatter(textFormatter);

        int nbetoiles = Integer.parseInt(textetoile);

        NotePaquetCrud npc = new NotePaquetCrud();
        NotePaquet np = new NotePaquet(1, 1, nbetoiles, Commentaire);
        npc.addEntity(np);

        EmailSender.send("rafik.bouchaoua@esprit.tn", "rafik_boch@hotmail.com", "bouchaoua.rafik@esprit.tn", "221SMT8188", "nb"); 
    }
    //TraductionNote translate = Translator.getInstance();
//String text = translate.translate("Hello!", Language.ENGLISH, Language.ROMANIAN);
//System.out.println(text); // "Bună ziua!" //

    @FXML
    private void update(ActionEvent event) {
        
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {

        String textetoile = tfnbetoile.getText().trim();
        String Commentaire = tfcom.getText().trim();
        System.out.println(textetoile);
        if (textetoile.isEmpty() || Commentaire.isEmpty()) {
            // Display an alert with a button for empty input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Input cannot be empty");

            // Create a custom OK button
            ButtonType okButton = new ButtonType("OK");

            // Set the button type for the alert
            alert.getButtonTypes().setAll(okButton);

            // Show the alert and wait for button click
            alert.showAndWait();

            return; // Exit the method
        }

        // Check if the input is a number between 0 and 5
        // Check if the input is a number between 0 and 5
        int nbetoiles;
        try {
            nbetoiles = Integer.parseInt(textetoile);
            if (nbetoiles < 0 || nbetoiles > 5) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            // Display an alert with a button for invalid input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number between 0 and 5");

            // Create a custom OK button
            ButtonType okButton = new ButtonType("OK");

            // Set the button type for the alert
            alert.getButtonTypes().setAll(okButton);

            // Show the alert and wait for button click
            alert.showAndWait();

            return; // Exit the method
        } catch (IllegalArgumentException e) {
            // Display an alert with a button for invalid input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a number between 0 and 5");

            // Create a custom OK button
            ButtonType okButton = new ButtonType("OK");

            // Set the button type for the alert
            alert.getButtonTypes().setAll(okButton);

            // Show the alert and wait for button click
            alert.showAndWait();

            return; // Exit the method
        }

        NotePaquetCrud npc = new NotePaquetCrud();
        NotePaquet np = new NotePaquet(1, 1, nbetoiles, Commentaire);
        npc.addEntity(np);

        //EmailSender.sendEmail("recipientEmail", "senderEmail", "senderPassword", np);
    }

}
