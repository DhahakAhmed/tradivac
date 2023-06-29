/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;


import com.sun.prism.impl.Disposer.Record;
import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.entities.ServiceList;
import edu.tradivac.services.ServiceExcursionCrud;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author devhk
 */
public class ListeServiceController implements Initializable {

    @FXML
    private ChoiceBox typeServiceChoice;
    @FXML
    private Button ajouterServiceBtn;
    @FXML
    private TableView tableViewService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // ADD OPTIONS TO CHOCIEBOX
        typeServiceChoice.getItems().add("Excursion");
        typeServiceChoice.getItems().add("Hebergement");
        typeServiceChoice.getItems().add("Restauration");
        typeServiceChoice.setValue("Excursion");
        
        // CREATE COLUMNS TO TABLEVIEW
        TableColumn<ServiceList, String> column1 = new TableColumn<>("Nom Service");
        column1.setCellValueFactory(new PropertyValueFactory<>("nom_service"));
        TableColumn<ServiceList, String> column2 = new TableColumn<>("Type");
        column2.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<ServiceList, String> column3 = new TableColumn<>("Destination");
        column3.setCellValueFactory(new PropertyValueFactory<>("destination"));
        TableColumn<ServiceList, String> column4 = new TableColumn<>("Prix");
        column4.setCellValueFactory(new PropertyValueFactory<>("prix"));
         TableColumn<ServiceList, ServiceExcursion> column5 = new TableColumn<>("Modifier");
         column5.setCellValueFactory(new PropertyValueFactory<>("SE"));
         TableColumn<ServiceList, ServiceExcursion> column6 = new TableColumn<>("Supprimer");
         column6.setCellValueFactory(new PropertyValueFactory<>("SE"));
                
        column5.setCellFactory(col -> {
            Button editButton = new Button("Modifier");
            TableCell<ServiceList, ServiceExcursion> cell = new TableCell<ServiceList, ServiceExcursion>() {
                @Override
                public void updateItem(ServiceExcursion ServiceExcursion, boolean empty) {
                    super.updateItem(ServiceExcursion, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
          
                }
               
                
            };
            editButton.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    ServiceExcursionCrud SEC = new ServiceExcursionCrud();
                     try {                       
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutService.fxml"));
                        Stage stage = (Stage) ajouterServiceBtn.getScene().getWindow();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        AjoutServiceController controller = loader.<AjoutServiceController>getController();
                        controller.setData(cell.getItem(),true);

                        
                    }catch (IOException io){
                         System.out.println(io.getMessage());
                    }
                }
            });
            
            return cell ;
        });    
                 
         column6.setCellFactory(col -> {
            Button editButton = new Button("Supprimer");
            TableCell<ServiceList, ServiceExcursion> cell = new TableCell<ServiceList, ServiceExcursion>() {
                @Override
                public void updateItem(ServiceExcursion ServiceExcursion, boolean empty) {
                    super.updateItem(ServiceExcursion, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
          
                }
               
                
            };
            editButton.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    ServiceExcursionCrud SEC = new ServiceExcursionCrud();
                    SEC.deleteEntity(cell.getItem());
                     try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeService.fxml"));
                        Stage stage = (Stage) ajouterServiceBtn.getScene().getWindow();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                    }catch (IOException io){
                         System.out.println(io.getMessage());
                    }
                }
            });
            
            return cell ;
        });

tableViewService.getColumns().addAll(column1,column2,column3,column4,column5,column6);


        
    
        // GET ENTITIES
        ServiceExcursionCrud SEC = new ServiceExcursionCrud();
        List<ServiceExcursion>  listSEC = new ArrayList<>();
        listSEC = SEC.displayEntities();
        int i = 0;
         ObservableList data = FXCollections.observableArrayList();
        while( i < listSEC.size()){
             ServiceList serviceListToAdd = new ServiceList();
            serviceListToAdd.setNom_service(listSEC.get(i).getNom_service());
            serviceListToAdd.setType(listSEC.get(i).getType());
            serviceListToAdd.setDestination(listSEC.get(i).getDestination());
            serviceListToAdd.setPrix(listSEC.get(i).getPrix());
            serviceListToAdd.setSE(listSEC.get(i));
            data.add(serviceListToAdd);
            i++;
        }
        tableViewService.setItems(data);
    }    

    @FXML
    private void ouvrirAjoutService(ActionEvent event) {
        String typeServiceToAdd = (String) typeServiceChoice.getValue();
        switch (typeServiceToAdd) {
            case "Excursion":
                System.out.println("Ouvrir excursion");
                 try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutService.fxml"));
                    Stage stage = (Stage) ajouterServiceBtn.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                }catch (IOException io){
                     System.out.println(io.getMessage());
                }
                break;
            case "Hebergement":
                System.out.println("Pas encore developpe");
                break;
            case "Restauration":
                System.out.println("Pas encore developpe");
                break;
            default:
                break;
        }
    }
    ServiceExcursion objToUpdate = null;

    

}
