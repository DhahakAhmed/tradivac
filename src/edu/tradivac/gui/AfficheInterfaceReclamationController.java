/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.Reclamation;
import edu.tradivac.services.ReclamationCrud;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Reclamation, Date> created_at;
    @FXML
    private TextField tfobjet;
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
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableaurec.setItems(reclamationInstance);
        
        
        // TODO
    }    

    @FXML
    private void AjouterReclamationAction(ActionEvent event) {
   
    }

    @FXML
    private void UpdateReclamationAction(ActionEvent event) {
    }

    @FXML
    private void DeleteReclamationAction(ActionEvent event) {
    }
    
}
