package edu.tradivac.gui.details;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import edu.tradivac.entities.Activity;
import edu.tradivac.entities.DetailsPack;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Pack;

public class DetailsController {

    ObservableList<String> listaHoras = FXCollections.observableArrayList("12:00", "14:00", "17:00", "19:00", "21:00");

    private Activity activity;
    private DetailsPack detallePack = null;
    private Pack pack;
    private Tools tools;

    private int idPack;
    private int idactivity;
    private String nombreDetailsPack;
    private int numPlaces = 1;
    private double priceTotal = 0;
    private LocalDate startDate;
    private LocalDate fechaFin;
    private LocalTime horaLocalTime;

    @FXML
    private ImageView ivImagen;
    @FXML
    private TextArea tadescription;
    @FXML
    private DatePicker dpstartDate;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private Spinner<Integer> spiPlazas;
    @FXML
    private Button bAgregarcart;
    @FXML
    private Hyperlink labURLactivity;
    @FXML
    private Label labNombreactivity;
    @FXML
    private ComboBox<String> cbHoraInicio;
    @FXML
    private ComboBox<String> cbHoraFin;
    @FXML
    private Label labHoraFin;
    @FXML
    private Label labFechaFin;
    @FXML
    private Label labNumPersonas;
    @FXML
    private Label labelprice;
    @FXML
    private Label labelpricePersona;
    @FXML
    private Label labelDias;

    public void initialize() {
    }

    //Setear parametros
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    //Setear datos
    public void setInfo(Activity activity) {
        labNombreactivity.setText(activity.getNombre());
        labURLactivity.setText(activity.getUrl());
        ivImagen.setImage(new Image("/edu/tradivac/img/" + activity.getImage()));
        tadescription.setText(activity.getDescription());
        labelprice.setText(activity.getPrice() + "€");
        labelpricePersona.setText(activity.getPrice() + "€");

        this.activity = activity;
        switch (activity.gettype()) {
            case "1":
                cargarHotel();
                break;
            case "2":
                cargarOcio();
                break;
            case "3":
                cargarHosteleria();
                break;
        }

        setElementos();
    }
    
    public void cargarHotel() {         
        labelDias.setVisible(true);         
        labNumPersonas.setText("ChambresH");            
    }

    public void cargarOcio() {
        labFechaFin.setVisible(false);
        dpFechaFin.setVisible(false);
        labHoraFin.setVisible(false);
        cbHoraFin.setVisible(false);
        labNumPersonas.setText("Personnes");
    }

    public void cargarHosteleria() {
        labFechaFin.setVisible(false);
        dpFechaFin.setVisible(false);
        labHoraFin.setVisible(false);
        cbHoraFin.setVisible(false);
        labNumPersonas.setText("diners");
    }

    public void setElementos() {
        cbHoraInicio.setItems(listaHoras);
        cbHoraInicio.getSelectionModel().selectFirst();
        String horaString = cbHoraInicio.getValue();
        this.horaLocalTime = LocalTime.parse(horaString);

        cbHoraFin.setItems(listaHoras);
        cbHoraFin.getSelectionModel().selectFirst();
            
        System.out.println("pack.getstartDate()"+pack.getstartDate()+" // pack.getfinalDate() " +pack.getfinalDate());
        dpstartDate.setDayCellFactory(tools.getDayCellFactory(pack.getstartDate(), pack.getfinalDate()));
        spiPlazas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));

    }

    public DetailsPack getDetallePack() {
        return this.detallePack;
    }

    //Actualizar valores/Eventos
    @FXML
    private void updatestartDate(ActionEvent event) {
        this.startDate = dpstartDate.getValue();
        //Habilitar y setear fechaFin
        dpFechaFin.setDayCellFactory(tools.getDayCellFactory(startDate, pack.getfinalDate()));
        dpFechaFin.setDisable(false);
        this.fechaFin = dpstartDate.getValue().plusDays(1);
        dpFechaFin.setValue(dpstartDate.getValue().plusDays(1));
        calcDays();
    }

    @FXML
    private void updateEndDate(ActionEvent event) {
        this.fechaFin = dpFechaFin.getValue();
        try {
            labelprice.setText(calculerPrix() + "€");
        } catch (Exception e) {
            tools.showAlertErr("Le prix n'a pas pu être calculé");
        }
        calcDays();
    }

    //Revisar
    @FXML
    private void calcTimeStart(ActionEvent event) {
        String horaString = cbHoraInicio.getValue();
        this.horaLocalTime = LocalTime.parse(horaString);
        cbHoraFin.setDisable(false);
    }

    @FXML
    private void updatePlaces(MouseEvent event) {
        this.numPlaces = spiPlazas.getValue();
        labelprice.setText(calculerPrix() + "€");
    }

    //Calcular el price
    public double calculerPrix() {
        double price = activity.getPrice();
        price = price * numPlaces;
        if (startDate != null && fechaFin != null) {
            int numDias = Period.between(startDate, fechaFin).getDays();
            price = price * numDias;
        }
        return price;
    }

    //Checkear y setear el objeto DetallePack
    private boolean checkDetailPack() {
        return startDate != null && fechaFin != null && cbHoraFin != null && cbHoraInicio.getValue() != null;
    }

    private boolean setDetaillePack() {
        this.idPack = activity.getIdActivity();
        this.idactivity = activity.getIdActivity();
        this.nombreDetailsPack = activity.getNombre();

        if (checkDetailPack()) {
            this.priceTotal = calculerPrix();
            detallePack = new DetailsPack(idPack, idactivity, nombreDetailsPack, numPlaces, priceTotal, startDate, fechaFin, horaLocalTime);
            detallePack.setPriceBase(activity.getPrice());

            return true;
        } else {
            tools.showAlertErr("Pour ajouter l'activité, vous devez d'abord la configurer");
        }
        return false;
    }

    //Agregar detallePack al objeto Pack
    @FXML
    private void addDetallePack(ActionEvent event) {
        if (setDetaillePack() && tools.showAlertConf("Voulez-vous vraiment ajouter l'activité ?")) {
            pack.getListaactivities().add(detallePack);

            //Cerrar ventana
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void visitURL(ActionEvent event) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(labURLactivity.getText()));
            } catch (IOException | URISyntaxException e1) {
                tools.showAlertErr("Failed to load the web page");
            }
        }
    }
    
    private void calcDays() {         
        Period periodoDias = Period.between(this.startDate, this.fechaFin);         
        labelDias.setText("pour " + String.valueOf(periodoDias.getDays()) + " noches");     
    }


}
