/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.services.ServiceExcursionCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.xml.transform.Source;
import sun.management.snmp.util.JvmContextFactory;

/**
 * FXML Controller class
 *
 * @author devhk
 */
public class AjoutServiceController implements Initializable {

    @FXML
    private TextField TFNom_service;
    @FXML
    private TextField TFType;
    @FXML
    private TextField TFDestination;
    @FXML
    private TextArea TADescription;
    @FXML
    private DatePicker DPDate_debut;
    @FXML
    private DatePicker DPDate_fin;
    @FXML
    private TextField TFPrix;
    @FXML
    private CheckBox CBForet;
    @FXML
    private CheckBox CBPlage;
    @FXML
    private Button btnValiderExcursion;
    @FXML
    private Text returnBtn;
    @FXML
    private Text errorMessage;
    int idToUpdate = -1;
    String currName = "";
    boolean updateStatus = false;
    public void setData(ServiceExcursion data, boolean updateB) {
        TFNom_service.setText(data.getNom_service());
        TFType.setText(data.getType());
        TFDestination.setText(data.getDestination());
        TADescription.setText(data.getDescription());
        TFPrix.setText(data.getPrix()+"");
        DPDate_debut.setValue(data.getDate_debut().toLocalDate());
        DPDate_fin.setValue(data.getDate_fin().toLocalDate());
        CBForet.setSelected(data.isExcursion_foret());
        CBPlage.setSelected(data.isExcursion_plage());
        idToUpdate = data.getId_service();
        currName = data.getNom_service();
        System.out.println(data.getId_service());
        updateStatus = updateB;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }    

    static Window getWindow2(Event event) {
        Node source = (Node) event.getSource();
        return source.getScene().getWindow();
      }
    
    @FXML
    private void validerExcursion(ActionEvent event) {
        String nom_service = TFNom_service.getText();
        double prix = Double.parseDouble(TFPrix.getText());
        String excursion_type = TFType.getText();
        String description = TADescription.getText();
        Date date_debut = java.sql.Date.valueOf( DPDate_debut.getValue() );
        Date date_fin = java.sql.Date.valueOf( DPDate_fin.getValue() );
        Boolean excursion_foret = CBForet.isSelected();
        Boolean excursion_plage = CBPlage.isSelected();
        String destination = TFDestination.getText();
                
            ServiceExcursionCrud SEC = new ServiceExcursionCrud();
            List<ServiceExcursion>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            boolean nomServiceUsed = false;
            int i = 0;
            while( i < listSEC.size()){
                System.out.println(listSEC.get(i).getNom_service());
                if( listSEC.get(i).getNom_service().equals(nom_service) && listSEC.get(i).getNom_service().equals(currName) == false){
                    nomServiceUsed = true;
                }
                i++;
            }
            if( date_debut.compareTo(date_fin) > 0){
                 errorMessage.setText("Date de fin doit etre superieure a la date de debut.");
            }
            else if(prix <= 0){
                errorMessage.setText("Le prix doit etre superieur a 0.");
            }
            else if ( nomServiceUsed == true ){
                errorMessage.setText("Nom service utilisé, essayez un autre.");
            }
            else{
                ServiceExcursion newService = new ServiceExcursion(0, 0, nom_service,"excursion", description, prix, destination, date_debut, date_fin, excursion_plage, excursion_foret, excursion_type);
                if(updateStatus == true){
                    newService = new ServiceExcursion(idToUpdate, 0, nom_service,"excursion", description, prix, destination, date_debut, date_fin, excursion_plage, excursion_foret, excursion_type);
                }

                if(updateStatus == false){
                    SEC.addEntity(newService);


                }
                else{SEC.updateEntity(newService);}
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("listeService.fxml"));
                    Stage stage = (Stage) btnValiderExcursion.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    }catch (IOException io){
                        System.out.println(io.getMessage());
                }
            }
    }

    @FXML
    private void returnHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeService.fxml"));
            Stage stage = (Stage) btnValiderExcursion.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            }catch (IOException io){
                System.out.println(io.getMessage());
        }
    }

    
}
