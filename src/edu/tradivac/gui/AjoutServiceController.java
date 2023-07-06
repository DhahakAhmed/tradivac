/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.entities.ServiceHebergement;
import edu.tradivac.entities.ServiceRestauration;
import edu.tradivac.services.ServiceExcursionCrud;
import edu.tradivac.services.ServiceHebergementCrud;
import edu.tradivac.services.ServiceRestaurationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private CheckBox CBmeublee;
    @FXML
    private ChoiceBox CBtypeHebergement;
    @FXML
    private TextField TFrestauration_type;
    @FXML
    private CheckBox CBrepas;
    @FXML
    private CheckBox CBdejeuner;
    @FXML
    private CheckBox CBdiner;
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
        idToUpdate = data.getId_service();
        currName = data.getNom_service();
        System.out.println(data.getId_service());
        updateStatus = updateB;
        TFType.setDisable(true);
        updateForm(data.getType());
        currType = data.getType();
        if( "excursion".equals(currType)){
            CBForet.setSelected(data.isExcursion_foret());
            CBPlage.setSelected(data.isExcursion_plage());
        }
        else if( "hebergement".equals(currType)){
            // GET SERVICE HEBERGEMENT 
            ServiceHebergementCrud SEC = new ServiceHebergementCrud();
            List<ServiceHebergement>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            int i = 0;
            ServiceHebergement convertedData = null;
            while( i < listSEC.size()){
                if(listSEC.get(i).getId_service() == data.getId_service()){
                    convertedData = listSEC.get(i);
                    break;
                }
                i++;
            }
            CBmeublee.setSelected(convertedData.isHebergement_meublee());
            CBtypeHebergement.setValue(convertedData.getHebergement_classification_taille());
        }
        else if( "restauration".equals(currType)){
            ServiceRestaurationCrud SEC = new ServiceRestaurationCrud();
            List<ServiceRestauration>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            int i = 0;
            ServiceRestauration convertedData = null;
            while( i < listSEC.size()){
                if(listSEC.get(i).getId_service() == data.getId_service()){
                    convertedData = listSEC.get(i);
                    break;
                }
                i++;
            }
            TFrestauration_type.setText(convertedData.getRestauration_type());
            CBrepas.setSelected(convertedData.isRestauration_repas_sur_demande());
            CBdejeuner.setSelected(convertedData.isRestauration_dejeuner());
            CBdiner.setSelected(convertedData.isRestauration_diner());
        }
    }
    private String currType = "";
    public void setServiceType(String type){
            TFType.setText(type);
            TFType.setDisable(true);
            updateForm(type);
            currType = type;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CBtypeHebergement.getItems().add("studio");
        CBtypeHebergement.getItems().add("T1");
        CBtypeHebergement.getItems().add("T1 bis");
        CBtypeHebergement.getItems().add("T2");
        CBtypeHebergement.getItems().add("T2 bis");
        CBtypeHebergement.getItems().add("T3");
        CBtypeHebergement.setValue("studio");
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
        String destination = TFDestination.getText();
        if( "excursion".equals(currType)){
            Boolean excursion_foret = CBForet.isSelected();
            Boolean excursion_plage = CBPlage.isSelected();
                
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
                    SEC.updateEntity(newService);
                }
                else if(updateStatus == false){
                    SEC.addEntity(newService);
                }
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
        else if ("hebergement".equals(currType)){
            String tpye_hebergement = (String) CBtypeHebergement.getValue();
            boolean hebergement_meublee = CBmeublee.isSelected();
            
            ServiceHebergementCrud SEC = new ServiceHebergementCrud();
            List<ServiceHebergement>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            boolean nomServiceUsed = false;
            int i = 0;
            while( i < listSEC.size()){
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
                ServiceHebergement newService = new ServiceHebergement(0, 0, nom_service,"Hebergement", description, prix, destination, date_debut, date_fin, tpye_hebergement, hebergement_meublee);
                if(updateStatus == true){
                    newService = new ServiceHebergement(idToUpdate, 0, nom_service,"Hebergement", description, prix, destination, date_debut, date_fin, tpye_hebergement, hebergement_meublee);
                    SEC.updateEntity(newService);
                }
                else if(updateStatus == false){
                    SEC.addEntity(newService);
                }
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
        else if("restauration".equals(currType)){
            String restauration_type = TFrestauration_type.getText();
            boolean restauration_repas = CBrepas.isSelected();
            boolean restauration_dejeuner = CBdejeuner.isSelected();
            boolean restauration_diner = CBdiner.isSelected();
            System.out.println(restauration_type);
            ServiceRestaurationCrud SEC = new ServiceRestaurationCrud();
            List<ServiceRestauration>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            boolean nomServiceUsed = false;
            int i = 0;
            while( i < listSEC.size()){
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
                ServiceRestauration newService = new ServiceRestauration(0, 0, nom_service,"Restauration", description, prix, destination, date_debut, date_fin, restauration_type, restauration_repas, restauration_dejeuner, restauration_diner);
                if(updateStatus == true){
                    newService = new ServiceRestauration(idToUpdate, 0, nom_service,"Restauration", description, prix, destination, date_debut, date_fin,  restauration_type, restauration_repas, restauration_dejeuner, restauration_diner);
                    SEC.updateEntity(newService);
                }
                else if(updateStatus == false){
                    SEC.addEntity(newService);
                }
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

    private void updateForm(String type) {
        CBForet.setVisible(false);
            CBPlage.setVisible(false);
            CBmeublee.setVisible(false);
            CBtypeHebergement.setVisible(false);
            TFrestauration_type.setVisible(false);
            CBrepas.setVisible(false);
            CBdejeuner.setVisible(false);
            CBdiner.setVisible(false);
            if ( "excursion".equals(type)){
                CBForet.setVisible(true);
                CBPlage.setVisible(true);
            }
            else if ( "hebergement".equals(type)){
                CBmeublee.setVisible(true);
                CBtypeHebergement.setVisible(true);
            }
            else if ( "restauration".equals(type)){
                TFrestauration_type.setVisible(true);
                CBrepas.setVisible(true);
                CBdejeuner.setVisible(true);
                CBdiner.setVisible(true);
            }
    }

    
}
