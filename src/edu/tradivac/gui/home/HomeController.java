package edu.tradivac.gui.home;

import java.io.File;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Callback;
import edu.tradivac.bda.ConexionDAO;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Pack;
import edu.tradivac.gui.activities.ActivitiesController;
import edu.tradivac.gui.menuAdmin.menuAdminController;

public class HomeController {

    private Connection conexion;
    private ConexionDAO connect;
    private Pack pack = new Pack(); //Por ahora
    private Tools tools;
    private UserDAO userDAO;

    @FXML
    private Pane menu;
    @FXML
    private Button botonHosteleria;
    @FXML
    private Button botonOcio;
    @FXML
    private Button botonHoteles;
    @FXML
    private Button botoncreateactivity;
    @FXML
    private DatePicker startDatePack;
    @FXML
    private DatePicker fechaFinPack;
    @FXML
    private TextField textNombrePack;
    @FXML
    private Button buttonAceptarNombre;
    @FXML
    private Label labelNombrePack;
    @FXML
    private Pane mainPane;
    @FXML
    private MediaView mediaView;

    MediaPlayer mediaPlayer;
    Button botonPausa;

    private String mailuser;
    private int iduser;

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setconnect(ConexionDAO connect) {
        this.connect = connect;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    public void setuserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    public void initialize() {
    }

    public void homeInitialize() {
        try {

            System.out.println("mailuser : " + mailuser);
            iduser = userDAO.getIduser(mailuser);
            System.out.println("iduser : " + iduser);
            pack.setIduser(iduser);

            setDefault();
            pack.setFechaCreacion(LocalDate.now());
            pack.setTiempoCreacion(LocalTime.now());
        } catch (SQLException e) {
            tools.showAlertErr("Error " + e.getMessage());
        }
    }

    public void setDefault() {
        startDatePack.setShowWeekNumbers(false);
        fechaFinPack.setShowWeekNumbers(false);
        fechaFinPack.setDisable(true);
        addVideo();
        Callback<DatePicker, DateCell> dayCellFactory = tools.getDayCellFactoryInicio(LocalDate.now());
        startDatePack.setDayCellFactory(dayCellFactory);
    }

    //VENTANAS Y ESCENAS
    @FXML
    private void mostraractivities(ActionEvent event) {

        if (labelNombrePack.getText().length() != 0 && startDatePack.getValue() != null && fechaFinPack.getValue() != null) {
            if (checkFechas()) {
                mediaPlayer.stop();
                Object botonPulsado = event.getSource();
                int type;
                if (botonPulsado == botonHoteles) {
                    type = 1;
                } else if (botonPulsado == botonOcio) {
                    type = 2;
                } else {
                    type = 3;
                }

                FXMLLoader loader;
                Parent root = null;
                try {
                    loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/edu/tradivac/gui/activities/activities.fxml"));
                    root = loader.load(); //Se ejecuta el Initialize

                    ActivitiesController activitiesController = loader.getController();
                    activitiesController.setConexion(conexion);
                    activitiesController.setPack(pack);
                    activitiesController.setTools(tools);
                    activitiesController.setType(type);
                    activitiesController.rellenarConPanes();

                    menu.getChildren().addAll(root);
                } catch (IOException ex) {
                    tools.showAlertErr(ex.getMessage());
                }

            } else {
                tools.showAlertErr("dates incorrectes");
            }

        } else {
            tools.showAlertErr("Configurez d'abord votre pack");
        }
    }

    @FXML
    private void createActivity(ActionEvent event) {
        try {

            System.out.println("pack() : " + pack.getDescription());

            System.out.println("pack.getIduser() : " + pack.getIduser());
            System.out.println("userDAO.getRol(pack.getIduser()) : " + userDAO.getRol(pack.getIduser()));

            if (userDAO.getRol(pack.getIduser()).equalsIgnoreCase("admin")) {
                mediaPlayer.stop();
                FXMLLoader loader;
                Parent root = null;
                try {

                    loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/edu/tradivac/gui/menuAdmin/menuAdmin.fxml"));
                    root = loader.load(); //Se ejecuta el Initialize

                    menuAdminController menuAdmin = loader.getController();
                    menuAdmin.setConex(conexion);
                    menuAdmin.setTools(tools);

                    menu.getChildren().addAll(root);
                } catch (IOException ex) {
                    tools.showAlertErr("Error " + ex.getMessage());
                }
            } else {
                tools.showAlertErr("Vous n'êtes pas administrateur !");
            }
        } catch (SQLException e) {
            tools.showAlertErr("Error " + e.getMessage());
        }

    }

    //SETEAR CAMPOS
    private boolean changeFechas() {
        if (pack.getListaactivities().isEmpty()) {
            return true;
        }
        startDatePack.setValue(pack.getstartDate());
        fechaFinPack.setValue(pack.getfinalDate());
        tools.showAlertErr("Désolé, il n'est pas possible de modifier les dates.\\Veuillez vider le panier avant d'effectuer la modification.");
        return false;
    }

    @FXML
    private void setstartDatePack(ActionEvent event) {
        if (changeFechas()) {
            if (startDatePack != null) {
                pack.setstartDate(startDatePack.getValue());

                //Habilitar y setear el campo de fecha fin del pack en el Controller Inicio
                Callback<DatePicker, DateCell> camposHabilitados = tools.getDayCellFactoryInicio(startDatePack.getValue());
                fechaFinPack.setDayCellFactory(camposHabilitados);
                fechaFinPack.setDisable(false);
                pack.setfinalDate(startDatePack.getValue().plusDays(1));
                fechaFinPack.setValue(startDatePack.getValue().plusDays(1));
            }

        }
    }

    @FXML
    private void setFechaFinPack(ActionEvent event) {
        if (changeFechas()) {
            pack.setfinalDate(fechaFinPack.getValue());
        }
    }

    @FXML
    private void createNombrePack(ActionEvent event) {
        if (textNombrePack.getText().length() != 0) {
            pack.setNombre(textNombrePack.getText());
            labelNombrePack.setText("PACK: " + textNombrePack.getText().toUpperCase());
        } else {
            tools.showAlertErr("Le nom ne peut pas être vide");
        }
    }

    private boolean checkFechas() {
        return startDatePack.getValue().isBefore(fechaFinPack.getValue()) || startDatePack.getValue().isEqual(fechaFinPack.getValue());
    }

    public void addVideo() {
        botonPausa = new Button();

        File file = new File("src/edu/tradivac//vid/malaga.mp4");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        //  mediaPlayer.play();
        botonPausa.setVisible(false);

        MediaView mediaView = new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setFitHeight(400);
        mediaView.setFitWidth(600);
        mediaView.setLayoutX(215);
        mediaView.setLayoutY(60);

        botonPausa.setLayoutX(479);
        botonPausa.setLayoutY(195);
        botonPausa.setOpacity(0.65);
        botonPausa.setMaxSize(150, 150);
        botonPausa.setStyle("-fx-graphic: url(/edu/tradivac/img/play.png); -fx-background-color: transparent");

        botonPausa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                    mediaPlayer.play();
                    botonPausa.setVisible(false);
                }

            }

        });

        botonPausa.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                botonPausa.setCursor(Cursor.HAND);
            }

        });

        mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.pause();
                    botonPausa.setVisible(true);
                }

            }

        });
        mediaView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaView.setCursor(Cursor.HAND);
                } else {
                    mediaView.setCursor(Cursor.DEFAULT);
                }

            }

        });

        menu.getChildren().add(mediaView);
        menu.getChildren().add(botonPausa);
    }
}
