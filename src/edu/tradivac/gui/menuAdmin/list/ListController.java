package edu.tradivac.gui.menuAdmin.list;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import edu.tradivac.bda.ActivityDAO;
import edu.tradivac.bda.DetailsPackDAO;
import edu.tradivac.bda.PackDAO;
import edu.tradivac.bda.TypesDAO;
import edu.tradivac.entities.Activity;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Types;

public class ListController {

    @FXML
    private TextField nombre;
    @FXML
    private TextArea description;
    @FXML
    private TextField price;

    @FXML
    private TextField url;
    @FXML
    private Button imagenBoton;
    @FXML
    private Label rutaIMG;
    @FXML
    private ImageView imgView;

    private Activity activitySelecc;

    private Connection conexion;
    private Tools tools;

    private ActivityDAO activityDAO = new ActivityDAO();
    private DetailsPackDAO detallePackDAO = new DetailsPackDAO();
    private PackDAO packDAO = new PackDAO();

    private TypesDAO typeDAO = new TypesDAO();

    private int qualityEstrellas;
    ObservableList<Types> listatypes;
    ObservableList<Activity> olistactivities;
    private Types type = new Types();
    private Path img;

    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonModificar1;
    @FXML
    private ListView<Activity> lvactivities;
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

        try {
            //Cargar activities en listView

            actualizaractivities();
            lvactivities.getSelectionModel().selectFirst();
            //Cargar types de activity en comboBox
            typeDAO.setConex(conexion);
            listatypes = FXCollections.observableArrayList(typeDAO.objetotype());
            typeBox.setItems(listatypes);

        } catch (SQLException ex) {
            tools.showAlertErr("Error: " + ex.getMessage());
        }
    }

    public void actualizaractivities() {
        try {
            activityDAO.setConex(conexion);
            olistactivities = FXCollections.observableArrayList(activityDAO.obtenerTodasactivities());
            lvactivities.setItems(olistactivities);
            resetEstilo();

        } catch (SQLException e) {
            tools.showAlertErr("Error: " + e.getMessage());
        }
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
            error += "- Campos en Null \n";
        } else {
            if (nombreAct.length() < 5) {
                error += "- Nombre corto \n";
            }
            if (descriptionAct.length() < 15) {
                error += "- description corta \n";
            }
            if (!regExpURL(urlAct)) {
                error += "- Formato incorrecto de URL \n";
            }
            try {
                double priceAct = Double.parseDouble(priceAct_string);
            } catch (NumberFormatException e) {
                error += "- Formato de price incorrecto (EJ: Hay letras) \n";
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
            tools.showAlertErr("No has seleccionado imagen");
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
    private void modactivity(ActionEvent event) {
        if (checkErrores()) {
            try {
                int idactivity = activitySelecc.getIdActivity();
                Activity activityModif = new Activity(idactivity, nombre.getText(),
                        description.getText(), img.toString(), url.getText(),
                        qualityEstrellas, Double.parseDouble(price.getText()),
                        type.getId());
                if (activityDAO.editaractivity(activityModif)) {
                    tools.showAlertInfo("activity editada");
                    actualizaractivities();
                } else {
                    tools.showAlertErr("Error al editar la activity");
                }
            } catch (SQLException e) {
                tools.showAlertErr("Error: " + e.getMessage());
            }
        }
    }

    @FXML
    private void elimactivity(ActionEvent event) {
        try {
            if (activityDAO.borraractivity(activitySelecc)) {
                tools.showAlertInfo("activité supprimée");
                actualizaractivities();
                limpiarCampos();

            } else {
                tools.showAlertErr("Erreur lors de la suppression de l'activité");
            }
        } catch (SQLException e) {
            boolean eliminar = tools.showAlertConf("Impossible de supprimer l'activité, actuellement"
                    + " est inclus dans un pack \n"
                    + "¿Vous souhaitez également supprimer les packs associés?");
            if (eliminar == true) {
                detallePackDAO.setConex(conexion);
                detallePackDAO.deleteDetailPack(activitySelecc);
                elimactivity(event);
            }
        }
    }

    @FXML
    private void seleccactivity(MouseEvent event) {
        activitySelecc = lvactivities.getSelectionModel().getSelectedItem();
        //Setear datos de la activity seleccionada
        nombre.setText(activitySelecc.getNombre());
        url.setText(activitySelecc.getUrl());
        price.setText(String.valueOf(activitySelecc.getPrice()));
        qualityEstrellas = activitySelecc.getQuality();
        estiloEstrellas("cal" + activitySelecc.getQuality());

        //Setear type
        for (Types type : listatypes) {
            if (type.getId().equals(activitySelecc.gettype())) {
                typeBox.getSelectionModel().select(type);
            }
        }
        description.setText(activitySelecc.getDescription());

        img = Paths.get(activitySelecc.getImage());
        rutaIMG.setText(activitySelecc.getImage());
        imgView.setImage(new Image("/edu/tradivac/img/" + activitySelecc.getImage()));
    }

    @FXML
    private void selecquality(MouseEvent event) {

        String idquality = "chaux" + activitySelecc.getQuality();
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
                qualityEstrellas = 1;
                break;
            case "cal2":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                qualityEstrellas = 2;
                break;
            case "cal3":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                qualityEstrellas = 3;
                break;
            case "cal4":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                qualityEstrellas = 4;
                break;
            case "cal5":
                cal5.setStyle("-fx-fill: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                qualityEstrellas = 5;
                break;
        }
    }

    private void limpiarCampos() {

        //Setear datos de la activity seleccionada
        nombre.setText("");
        url.setText("");
        price.setText(String.valueOf(0));

        estiloEstrellas("cal5");

        description.setText("");

        img = Paths.get("");
        rutaIMG.setText("");
        imgView.setImage(null);
    }

    @FXML
    private void modificarElemento(KeyEvent event) {
        if (activitySelecc.getNombre().equals(nombre.getText()) == false) {
            nombre.setStyle(estiloResaltado());
        } else if (activitySelecc.getNombre().equals(nombre.getText()) == true) {
            nombre.setStyle(estiloNormal());
        }

        if (activitySelecc.getPrice() != Double.valueOf(price.getText())) {
            price.setStyle(estiloResaltado());
        } else if (activitySelecc.getPrice() == Double.valueOf(price.getText())) {
            price.setStyle(estiloNormal());
        }

        if (activitySelecc.getUrl().equals(url.getText()) == false) {
            url.setStyle(estiloResaltado());
        } else if (activitySelecc.getUrl().equals(url.getText()) == true) {
            url.setStyle(estiloNormal());
        }

        if (activitySelecc.getDescription().equals(description.getText()) == false) {
            description.setStyle("-fx-background-color: white;"
                    + " -fx-border-color: red;");
        } else if (activitySelecc.getDescription().equals(description.getText()) == true) {
            description.setStyle("-fx-background-color: white;"
                    + " -fx-border-color: #85CAE3;");
        }
    }

    private String estiloResaltado() {
        return "-fx-background-color: white;"
                + " -fx-border-color: red;"
                + " -fx-border-style:  hidden hidden solid hidden;"
                + " -fx-border-width:  3px;"
                + " -fx-font-size:  16";
    }

    private String estiloNormal() {
        return "-fx-background-color: white;"
                + " -fx-border-color: #85CAE3;"
                + " -fx-border-style:  hidden hidden solid hidden;"
                + " -fx-border-width:  3px;"
                + " -fx-font-size:  16";

    }
    
    private void resetEstilo() {
        nombre.setStyle(estiloNormal());
        price.setStyle(estiloNormal());
        url.setStyle(estiloNormal());
        description.setStyle("-fx-background-color: white;"
                    + " -fx-border-color: #85CAE3;");
    }
}
