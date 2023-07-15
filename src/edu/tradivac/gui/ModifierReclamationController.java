/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.Reclamation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author octanet
 */
public class ModifierReclamationController {

    @FXML
    private TextField tfobjet;
    @FXML
    private TextArea tfdescription;

    private Reclamation reclamation;

    public void initData(Reclamation reclamation) {
        this.reclamation = reclamation;

        tfobjet.setText(reclamation.getObjet());
        tfdescription.setText(reclamation.getDescription());
    }

    @FXML
    private void updateReclamation() {
        String objet = tfobjet.getText();
        String description = tfdescription.getText();

        // Mettre à jour les données de la réclamation
        reclamation.setObjet(objet);
        reclamation.setDescription(description);

        // Afficher une alerte pour indiquer que la réclamation a été modifiée
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Réclamation a été modifiée", ButtonType.OK);
        alert.showAndWait();

        // Fermer la fenêtre
        Stage stage = (Stage) tfobjet.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        // Fermer la fenêtre sans effectuer de modifications
        Stage stage = (Stage) tfobjet.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void displayReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheInterfaceReclamation.fxml"));
        Parent root = loader.load();
        tfdescription.getScene().setRoot(root);
    }

}
