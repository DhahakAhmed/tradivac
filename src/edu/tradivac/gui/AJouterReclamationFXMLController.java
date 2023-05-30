/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.tradivac.gui;

import edu.tradivac.services.ReclamationCrud;
import edu.tradivac.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class AJouterReclamationFXMLController implements Initializable {

    @FXML
    private TextField tfobjet;
    @FXML
    private TextArea tfdescription;
private int nbReclamations = 0;

public static void main(String[] args) {
        launch(args);
    }

    /*@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interface de réclamations");
       
        Button ajouterReclamationButton = new Button("Ajouter une réclamation");
        ajouterReclamationButton.setOnAction(event -> ajouterReclamation());
       
        VBox vbox = new VBox(10);
        vbox.getChildren().add(ajouterReclamationButton);
       
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
   


    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void  addEntity(ActionEvent event) throws IOException {
        if (tfdescription.getText().isEmpty() || tfobjet.getText().isEmpty() && nbReclamations < 3) {
            Alert a = new Alert(Alert.AlertType.ERROR, "OBJET ou DESCRIPTION invalide(s)", ButtonType.OK);
            a.showAndWait();

nbReclamations++;
            System.out.println("Réclamation ajoutée ! Nombre total de réclamations : " + nbReclamations);
        } else {
Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Limite de réclamations atteinte");
            alert.setContentText("Vous avez atteint le nombre maximum de réclamations.");
            alert.showAndWait();
            ReclamationCrud sp = new ReclamationCrud();
            Reclamation r = new Reclamation(tfdescription.getText(), tfobjet.getText());
            sp.addEntity(r);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "RECLAMATION added !", ButtonType.OK);
            a.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AJouter Reclamation FXML"));
            Parent root = loader.load();
            tfdescription.getScene().setRoot(root);
            AJouterReclamationFXMLController apc = loader.getController();
//           apc.setObject(tfobjet.getText());
//         apc.setDescription(tfdescription.getText());
        }
    }
   
}

