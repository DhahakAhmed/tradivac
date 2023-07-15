/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.Reclamation;
import edu.tradivac.services.ReclamationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author octanet
 */
public class AfficheInterfaceReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableaurec;
    @FXML
    private TableColumn<Reclamation, Integer> id_reclamation;
    @FXML
    private TableColumn<Reclamation, Integer> id_touriste;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, Timestamp> date;

    @FXML
    private TextField tfdesc;

    private ObservableList<Reclamation> reclamationInstance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationCrud rc = new ReclamationCrud();
        reclamationInstance = FXCollections.observableList(rc.displayReclamation());
        id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        id_touriste.setCellValueFactory(new PropertyValueFactory<>("id_touriste"));
        objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableaurec.setItems(reclamationInstance);

        // TODO
    }


    

  @FXML
private void addReclamation(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamationFXML.fxml"));
    Parent root = loader.load();
    
    // Accéder au contrôleur du fichier FXML chargé
    AJouterReclamationFXMLController ajouterReclamationController = loader.getController();
    
    // Passer les données nécessaires au contrôleur AjouterReclamationController
    ajouterReclamationController.setData(reclamationInstance);
    
    // Afficher la scène contenant le formulaire d'ajout de réclamation
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
}

  
    
    
@FXML
private void deleteReclamation(ActionEvent event) {
    // Get the selected reclamation from the table
    Reclamation selectedReclamation = tableaurec.getSelectionModel().getSelectedItem();
    
    if (selectedReclamation != null) {
        // Call the delete method in your ReclamationCrud class or service
        ReclamationCrud rc = new ReclamationCrud();
        rc.deleteReclamation(selectedReclamation.getId_reclamation());
        
        // Remove the selected reclamation from the observable list
        reclamationInstance.remove(selectedReclamation);
         Alert a = new Alert(Alert.AlertType.INFORMATION, "Réclamation a été supprimée ", ButtonType.OK);
            a.showAndWait();
           
    }
    else {         Alert a = new Alert(Alert.AlertType.ERROR, "Merci de sélectionner l'objet !", ButtonType.OK);
            a.showAndWait();
            System.out.println("Merci de vérifier !! ");}
}







@FXML
    private void updateReclamation(ActionEvent event) throws IOException {
        Reclamation selectedReclamation = tableaurec.getSelectionModel().getSelectedItem();

        if (selectedReclamation != null) {
            showModifierReclamation(selectedReclamation);
        } else {
               Alert a = new Alert(Alert.AlertType.ERROR, "Merci de sélectionner l'objet !", ButtonType.OK);
            a.showAndWait();
            System.out.println("No row selected.");
        }
        
          
    }
    


    @FXML
    private void showModifierReclamation(Reclamation reclamation) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
        Parent root = loader.load();

        ModifierReclamationController modifierController = loader.getController();
        modifierController.initData(reclamation);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();

        tableaurec.refresh();
    }
}