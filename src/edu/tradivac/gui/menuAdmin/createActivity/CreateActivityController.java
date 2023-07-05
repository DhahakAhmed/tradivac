package edu.tradivac.gui.menuAdmin.createActivity;

import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;


import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import edu.tradivac.bda.ActivityDAO;
import edu.tradivac.bda.TypesDAO;
import edu.tradivac.entities.Activity;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Types;

public class CreateActivityController {

    @FXML
    private TextField nombre;
    @FXML
    private TextArea description;
    @FXML
    private TextField price;
    
    
    @FXML
    private Button botoncreate;
    @FXML
    private TextField url;
    @FXML
    private Button imagenBoton;
    @FXML
    private Label rutaIMG;
    @FXML
    private ImageView imgView;

    private Connection conexion;
    private Tools tools;
    private ActivityDAO activityDAO = new ActivityDAO();
    private TypesDAO typeDAO = new TypesDAO();

    private int qualityEstrellas = 5;
    ObservableList<Types> listatypes;
    private Types type = new Types();
    private Path img;
    @FXML
    private SVGPath cal5;
    @FXML
    private SVGPath cal4;
    @FXML
    private SVGPath cal3;
    @FXML
    private SVGPath cal2;
    @FXML
    private SVGPath cal1;
    @FXML
    private JFXComboBox<Types> typeBox;

    public void initialize() {

    }

    //Parametros
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    //Default
    public void setDefault() {

        activityDAO.setConex(conexion);
        typeDAO.setConex(conexion);
        try {
            listatypes = FXCollections.observableArrayList(typeDAO.objetotype());
        } catch (SQLException ex) {
            tools.showAlertErr("Error " + ex.getMessage());

        }
        typeBox.setItems(listatypes);
        typeBox.getSelectionModel().selectFirst();
        

    }

    //Checkeo de errores al insertar la activity
    public boolean checkErrores() {
        String nombreAct = nombre.getText();
        String descriptionAct = description.getText();
        String priceAct_string = price.getText();
        String urlAct = url.getText();
        type = typeBox.getValue();
        String rutaImagen = rutaIMG.getText();

        String error = "";

        if (nombreAct.length() == 0 || descriptionAct.length() == 0 || priceAct_string.length() == 0
                || urlAct.length() == 0 || rutaImagen.length() == 3) {
            error += "- Champs dans Null \n";
        } else {
            if (nombreAct.length() < 5) {
                error += "- Nom court \n";
            }
            if (descriptionAct.length() < 15) {
                error += "- brève description\n";
            }
            if (!regExpURL(urlAct)) {
                error += "- Format d'URL incorrect \n";
            }
            try {
                double priceAct = Double.parseDouble(priceAct_string);
            } catch (NumberFormatException e) {
                error += "- Mauvais format de prix (EX : Il y a des lettres) \n";
            }
        }
        if (!error.isEmpty()) {
            tools.showAlertErr(error);
            return false;
        }
        return true;
    }

    public boolean regExpURL(String URL) {
        String regExp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return url.getText().matches(regExp);
    }

    //Seleccion de imagen con FileChooser
    @FXML
    private void elegirImagen(ActionEvent event) {
        try {
            img = loadImagen();
            rutaIMG.setText(img.toString());
            imgView.setImage(new Image("/edu/tradivac/img/" + img.toString()));
        } catch (Exception e) {
            tools.showAlertErr("Vous n'avez pas sélectionné d'image");
        }
    }

    public Path loadImagen() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/edu/tradivac//img/"));
        File file = fileChooser.showOpenDialog(null);

        Path menu = Paths.get(file.getName());
        return menu;
    }

    @FXML
    private void createactivity(ActionEvent event) {
        if (checkErrores()) {
            try {
                int idactivity = activityDAO.ultimoIdactivity();
                Activity newAct = new Activity(++idactivity, nombre.getText(), description.getText(), img.toString(), url.getText(), qualityEstrellas, Double.parseDouble(price.getText()), type.getId());
                uploadactivity(newAct);
            } catch (SQLException ex) {
                tools.showAlertErr("Erreur lors de la sélection de la dernière activité");
            }
        }
    }

    public void uploadactivity(Activity act) {

        try {
            if (activityDAO.insertaractivities(act)) {
                tools.showAlertInfo("Nouvelle activité créée");
            }
        } catch (SQLException e) {
            tools.showAlertErr("Erreur lors de l'ajout de l'activité");
        }
    }

    @FXML
    private void selecquality(MouseEvent event) {
        String idquality = "cal5";
        Node nodoQueHaGeneradoEvento = (Node) event.getSource();
        idquality = nodoQueHaGeneradoEvento.getId();
        estiloEstrellas(idquality);
        
    }

    private void estiloEstrellas(String idquality) {
        switch (idquality) {
            case "cal1":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal2.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                qualityEstrellas= 1;
                break;
            case "cal2":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.qualityEstrellas = 2;
                break;
            case "cal3":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.qualityEstrellas = 3;
                break;
            case "cal4":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.qualityEstrellas = 4;
                break;
            case "cal5":
                cal5.setStyle("-fx-fill: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.qualityEstrellas = 5;
                break;
        }
    }
}