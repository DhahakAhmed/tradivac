package edu.tradivac.gui.activities;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import edu.tradivac.bda.ActivityDAO;
import edu.tradivac.entities.Activity;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Pack;
import edu.tradivac.gui.cart.CartController;
import edu.tradivac.gui.details.DetailsController;

public class ActivitiesController {

    private ArrayList<Activity> listActivities;

    private int quantitySelecc = 5;
    private double priceMaxSelecc = 100;

    private ActivityDAO activityDAO = new ActivityDAO();
    private Pack pack;
    private Tools tools;
    private Connection conexion;
    private int type;

    @FXML
    private Pane bar;
    @FXML
    private Pane menu;
    @FXML
    private Button cartButton;
    @FXML
    private Pane menuFilters;
    @FXML
    private Button filters;
    @FXML
    private AnchorPane menuActivities;

    private Pane pane;
    private ImageView image;
    private Label labelPrice;
    private Label labelTitle;
    private Label labelDescription;
    private Label labelPerson;
    private Label labelScore;
    private SVGPath star;
    private Button addbutton;
    private Hyperlink hiperLink;
    private Pane breadPrice;
    private Button buttonUbi;
    private WebView webView;
    private Pane paneMap;

    Double positionXstar = 450.;
    Double positionYstar = 60.;
    @FXML
    private Slider sliPrice;
    @FXML
    private SVGPath cal1;
    @FXML
    private SVGPath cal2;
    @FXML
    private SVGPath cal3;
    @FXML
    private SVGPath cal4;
    @FXML
    private SVGPath cal5;
    @FXML
    private Label labprice;
    @FXML
    private Label labelCart;
    @FXML
    private Pane paneBackground;

    public void initialize() {

    }

    //Setear parametros
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void rellenarConPanes() {

        Double posicionX = 100.;
        Double posicionY = 60.;

        menuActivities.setMinWidth(1043);
        try {
            activityDAO.setConex(conexion);
            listActivities = activityDAO.obteneractivities(type);
  
            for (Activity activity : listActivities) {
                System.out.println("Activity : "+activity.toString());
                menuActivities.getChildren().add(createPane(activity, posicionX, posicionY));
                posicionY += 225;
            }

        } catch (SQLException ex) {
            System.out.println("exception : "+ex.getMessage());
            tools.showAlertErr("Impossible d'établir la connexion");
        }

        labelCart.setText(String.valueOf(pack.getListaactivities().size()));
    }

    @FXML
    private void showcart(ActionEvent event) {
        FXMLLoader loader;
        Parent root = null;
        try {

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/cart/cart.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            CartController cart = loader.getController();
            cart.setConexion(conexion);
            cart.setPack(pack);
            cart.setTools(tools);

            cart.rellenarcart();

            menu.getChildren().addAll(root);
        } catch (IOException ex) {
            tools.showAlertErr("Erreur d'accès au panier");
        }
    }

    @FXML
    private void toggleFilters(ActionEvent event) {
        if (menuFilters.isVisible()) {
            menuFilters.setVisible(false);
            paneBackground.setVisible(false);
        } else {
            menuFilters.setVisible(true);
            paneBackground.setVisible(true);
        }
    }

    private Pane createPane(Activity activity, double posicionX, double posicionY) {

        pane = new Pane();
        image = new ImageView(new Image("/edu/tradivac/img/" + activity.getImage()));
        labelPrice = new Label();
        labelTitle = new Label();
        labelDescription = new Label();
        addbutton = new Button();
        labelPerson = new Label();
        hiperLink = new Hyperlink();
        breadPrice = new Pane();
        buttonUbi = new Button();

        // coordenadas X e Y
        pane.setLayoutX(posicionX);
        pane.setLayoutY(posicionY);

        //tamaño de los botones: largo, alto
        pane.setMinSize(850, 200);

        image.setLayoutX(10);
        image.setLayoutY(10);
        image.setFitHeight(180);
        image.setFitWidth(225);

        labelTitle.setLayoutX(300);
        labelTitle.setLayoutY(15);
        labelTitle.setText(activity.getNombre());
        labelTitle.setStyle("-fx-font-size: 22; -fx-text-fill:white");

        labelPrice.setLayoutX(10);
        labelPrice.setLayoutY(15);
        labelPrice.setMinSize(170, 50);
        labelPrice.setText(String.format("%.0f€", activity.getPrice()));
        labelPrice.setStyle("-fx-font-size: 50; -fx-text-fill:white; -fx-alignment:center");

        labelDescription.setText(activity.getDescription());
        labelDescription.setLayoutX(250);
        labelDescription.setLayoutY(75);
        labelDescription.setMaxSize(350, 200);
        labelDescription.setWrapText(true);
        labelDescription.setStyle("-fx-text-fill:white");

        addbutton.setText("Ajouter au paniert");
        addbutton.setMinSize(50, 25);
        addbutton.setLayoutX(47);
        addbutton.setLayoutY(135);
        addbutton.setStyle("-fx-background-color: #8C8C8C; -fx-text-fill:white; -fx-background-radius: 10");
        addbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //Este code lo crea en el momento del click. 
                //No puede hacer referencias a variables de este code porque coge el ultimo valor
                cargarDetalles(activity);
            }

        });

        labelPerson.setText("par personne");
        labelPerson.setLayoutX(70);
        labelPerson.setLayoutY(75);
        labelPerson.setStyle("-fx-font-size: 12; -fx-text-fill:white ");

        breadPrice.setMinSize(190, 180);
        breadPrice.setLayoutX(650);
        breadPrice.setLayoutY(10);
        breadPrice.setStyle("-fx-background-color:#85CAE3; -fx-background-radius: 15 ");
        breadPrice.getChildren().add(labelPrice);
        breadPrice.getChildren().add(labelPerson);
        breadPrice.getChildren().add(addbutton);

        hiperLink.setText("visiter le site Web");
        hiperLink.setLayoutX(260);
        hiperLink.setLayoutY(50);
        hiperLink.setStyle("-fx-text-fill: #85CAE3");

        hiperLink.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(activity.getUrl()));
                    } catch (IOException | URISyntaxException e1) {
                        tools.showAlertErr("Échec du chargement de la page Web");
                    }
                }
            }

        });

        for (int i = 1; i <= activity.getQuality(); i++) {

            star = new SVGPath();

            star.setContent("M 0.000 4.000\n"
                    + "L 5.878 8.090\n"
                    + "L 3.804 1.236\n"
                    + "L 9.511 -3.090\n"
                    + "L 2.351 -3.236\n"
                    + "L 0.000 -10.000\n"
                    + "L -2.351 -3.236\n"
                    + "L -9.511 -3.090\n"
                    + "L -3.804 1.236\n"
                    + "L -5.878 8.090\n"
                    + "L 0.000 4.000");
            star.setStyle("-fx-fill: #6AB1CA");
            star.setLayoutX(positionXstar);
            star.setLayoutY(positionXstar);

            pane.getChildren().add(star);
            positionXstar += 25;

            if (i == activity.getQuality()) {
                positionXstar = 450.;
                positionXstar = 60.;
            }

        }

        buttonUbi.setStyle("-fx-graphic: url(/edu/tradivac/img/ubi.png); -fx-background-color: transparent");
        buttonUbi.setLayoutX(255);
        buttonUbi.setLayoutY(10);
        buttonUbi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                mostrarMapa(activity);
            }

        });
        buttonUbi.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                buttonUbi.setCursor(Cursor.HAND);
            }

        });

        pane.setStyle("-fx-background-color: #8C8C8C; -fx-background-radius: 15");
        pane.getChildren().add(image);
        pane.getChildren().add(labelTitle);
        pane.getChildren().add(labelDescription);
        pane.getChildren().add(hiperLink);
        pane.getChildren().add(breadPrice);
        pane.getChildren().add(buttonUbi);

        return pane;
    }

    @FXML
    private void variarprice(MouseEvent event) {
        this.priceMaxSelecc = sliPrice.getValue();
        labprice.setText(String.format("%.0f€", this.priceMaxSelecc));
        filtraractivities();
    }

    @FXML
    private void selecquality(MouseEvent event) {
        String idquality = "cal5";
        Node nodoQueHaGeneradoEvento = (Node) event.getSource();
        idquality = nodoQueHaGeneradoEvento.getId();
        estiloEstrellas(idquality);
        filtraractivities();
    }

    private void filtraractivities() {
        Double posicionX = 100.;
        Double posicionY = 50.;

        menuActivities.getChildren().clear();
        for (Activity activity : listActivities) {

            if (activity.getQuality() <= quantitySelecc && activity.getPrice() <= this.priceMaxSelecc) {
                menuActivities.getChildren().add(createPane(activity, posicionX, posicionY));
                posicionY += 225;
            }
        }
    }

    private void estiloEstrellas(String idquality) {
        switch (idquality) {
            case "cal1":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal2.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.quantitySelecc = 1;
                break;
            case "cal2":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.quantitySelecc = 2;
                break;
            case "cal3":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.quantitySelecc = 3;
                break;
            case "cal4":
                cal5.setStyle("-fx-fill: white; -fx-stroke: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.quantitySelecc = 4;
                break;
            case "cal5":
                cal5.setStyle("-fx-fill: #0073ff");
                cal4.setStyle("-fx-fill: #0073ff");
                cal3.setStyle("-fx-fill: #0073ff");
                cal2.setStyle("-fx-fill: #0073ff");
                cal1.setStyle("-fx-fill: #0073ff");
                this.quantitySelecc = 5;
                break;
        }
    }

    public void cargarDetalles(Activity activity) {

        FXMLLoader loader;
        Parent root = null;
        try {

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/details/details.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            DetailsController detalleController = loader.getController();
            detalleController.setPack(pack);
            detalleController.setTools(tools);
            detalleController.setInfo(activity);

            Stage ventana = new Stage();

            ventana.setTitle("détails de l'activité " + activity.getNombre());
            ventana.setScene(new Scene(root));

            ventana.showAndWait();

            labelCart.setText(String.valueOf(pack.getListaactivities().size()));

        } catch (IOException ex) {
            System.out.println("ex : "+ex.getMessage());
            tools.showAlertErr("Erreur lors du chargement des détails de l'activité");
        }

    }

    public void mostrarMapa(Activity activity) {
        paneMap = new Pane();
        webView = new WebView();
        paneMap.setMinSize(800, 600);
        
        
        Stage escenaMapa = new Stage();
        Image icono = new Image(this.getClass().getResource("/edu/tradivac/img/ubi.png").toString());
        WebEngine webEngine = webView.getEngine();

        webEngine.load(activity.getLocation());
        escenaMapa.getIcons().setAll(icono);
        escenaMapa.setScene(new Scene(paneMap));
        escenaMapa.setTitle("Emplacement " + activity.getNombre());
        paneMap.getChildren().setAll(webView);
        
        escenaMapa.showAndWait();

    }

    @FXML
    private void borrarFiltros(ActionEvent event) {

        sliPrice.setValue(100);
        labprice.setText(String.format("%.0f€", sliPrice.getValue()));
        cal5.setStyle("-fx-fill: #0073ff");
        cal4.setStyle("-fx-fill: #0073ff");
        cal3.setStyle("-fx-fill: #0073ff");
        cal2.setStyle("-fx-fill: #0073ff");
        cal1.setStyle("-fx-fill: #0073ff");
        rellenarConPanes();
    }

}
