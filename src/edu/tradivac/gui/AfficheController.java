/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.NotePaquet;
import edu.tradivac.services.NotePaquetCrud;
import java.net.URL;
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
 * @author User
 */
public class AfficheController implements Initializable {

    @FXML
    private TableView<NotePaquet> tableauxnote;
    @FXML
    private TableColumn<NotePaquet, Integer> nb_etoiles;
    @FXML
    private TableColumn<NotePaquet, String> commentaire;
    @FXML
    private TableColumn<NotePaquet, Integer> id_note_paquet;
    @FXML
    private TableColumn<NotePaquet, Integer> id_user;
    @FXML
    private TableColumn<NotePaquet, Integer> id_paquet;
    @FXML
    private TextField tfnbe;
    @FXML
    private TextField tfcom;
    
    ObservableList<NotePaquet> liste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        NotePaquetCrud rc = new NotePaquetCrud();
        liste = FXCollections.observableList(rc.displayEntities());
        id_note_paquet.setCellValueFactory(new PropertyValueFactory<>("id_note_paquet"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        id_paquet.setCellValueFactory(new PropertyValueFactory<>("id_paquet"));
        nb_etoiles.setCellValueFactory(new PropertyValueFactory<>("nb_etoiles"));
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        tableauxnote.setItems(liste);
        // TODO
    }    

    @FXML
    private void ajouterAction(ActionEvent event) {
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
    }

    @FXML
    private void updateAction(ActionEvent event) {
    }
    
}
