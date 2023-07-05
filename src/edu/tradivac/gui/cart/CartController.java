package edu.tradivac.gui.cart;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edu.tradivac.bda.DetailsPackDAO;
import edu.tradivac.bda.PackDAO;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.card.CreateTicketPDF;
import edu.tradivac.entities.DetailsPack;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.Pack;

public class CartController {

    @FXML
    private TableView<DetailsPack> cart;
    @FXML
    private TableColumn<DetailsPack, Integer> id;
    @FXML
    private TableColumn<DetailsPack, String> nombre;
    @FXML
    private TableColumn<DetailsPack, Integer> numPers;
    @FXML
    private TableColumn<DetailsPack, LocalDate> startDate;
    @FXML
    private TableColumn<DetailsPack, LocalDate> fechaFin;
    @FXML
    private TableColumn<DetailsPack, Double> priceInd;
    @FXML
    private TableColumn<DetailsPack, Double> priceTotal;
    @FXML
    private TableColumn<DetailsPack, ImageView> borrar;
    @FXML
    private Button confirmar;
    @FXML
    private Label priceFinal;

    private CreateTicketPDF ticketCreator = new CreateTicketPDF();
    private Pack pack;
    ObservableList<DetailsPack> obsevableactivity = FXCollections.observableArrayList();

    DetailsPackDAO detallePackDAO = new DetailsPackDAO();
    PackDAO packDAO = new PackDAO();
    UserDAO ticketDAO = new UserDAO();
    Connection conexion;
    Tools tools;

    @FXML
    private TextArea description;

    public void initialize() {
    }

    //Setear parametros
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    //Rellenar cart de la compra/pack
    public void rellenarcart() {
        //Rellenamos la observable list
        for (int i = 0; i < pack.getListaactivities().size(); i++) {
            obsevableactivity.add(pack.getListaactivities().get(i));
            System.out.println(pack.getListaactivities().get(i).toString());
        }
            
        
        
        
        //Añadimos la observableList a la tabla
        cart.setItems(obsevableactivity);
        cart.getSelectionModel().setCellSelectionEnabled(true);
        //Asignamos las columnas
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombreDetailsPack"));
        numPers.setCellValueFactory(new PropertyValueFactory<>("numPlaces"));
        priceInd.setCellValueFactory(new PropertyValueFactory<>("priceBase"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDateES"));
        fechaFin.setCellValueFactory(new PropertyValueFactory<>("finalDateES"));
        priceTotal.setCellValueFactory(new PropertyValueFactory<>("price"));
        borrar.setCellValueFactory(new PropertyValueFactory<>("imgBorrar"));

        //Seteamos el parametro conexión al atributo de las clases que vamos a usar
        packDAO.setConex(conexion);
        detallePackDAO.setConex(conexion);

        //Calculamos el price del cart
        calcularpriceFinal();
    }

    //Caclular price final del pack
    public void calcularpriceFinal() {
        double priceFinal = 0;
        for (DetailsPack act : pack.getListaactivities()) {
            priceFinal += act.getPrice();
        }
        this.priceFinal.setText("Prix final: " + priceFinal + "€");
        pack.setprice(priceFinal);

    }

    private void insertarDetalle() throws SQLException {

        int idUltimoPack = packDAO.ultimopack();

        for (int i = 0; i < pack.getListaactivities().size(); i++) {
            //Recojo linea a linea del pack y le asigno como numLinea su posicion
            DetailsPack lineaInsertar = pack.getListaactivities().get(i);
            lineaInsertar.setNumLinea(i + 1);
            lineaInsertar.setIdPack(idUltimoPack);

            //Inserto la linea en la tabla detalle_pack
            detallePackDAO.insertarDetalle(lineaInsertar);
        }
    }

    @FXML
    private void confirmPack(ActionEvent event) {
        try {
            if (pack.getListaactivities().isEmpty()) {
                throw new SQLException("Le panier est vide !");
            }
            if (tools.showAlertConf("Êtes-vous sûr de passer la commande ?")) {
                //Insertar pack
                pack.setdescription(description.getText());
                pack.setIdPack(packDAO.ultimopack() + 1);
                packDAO.insertPack(pack);
                insertarDetalle();

                //Creacion del ticket PDF
                ticketDAO.setConex(conexion);
                ticketCreator.setTicketDAO(ticketDAO);
                ticketCreator.createTicketPDF(pack);
                tools.showAlertInfo("Votre commande de pack a été passée avec succès !");
                tools.showAlertInfo("Un ticket a été créé sur : " + ticketCreator.getFile().getAbsolutePath());
            } else {
                throw new SQLException("La commande n'a pas pu être passée.");
            }
        } catch (SQLException | DocumentException | FileNotFoundException e) {
            tools.showAlertErr(e.getMessage());
        }
    }

    @FXML
    private void borraractivity(MouseEvent event) {
        if (pack.getListaactivities().size() > 0) {
            try {
                TablePosition selectedCell = cart.getSelectionModel().getSelectedCells().get(0);
                int col = selectedCell.getColumn();
                int row = selectedCell.getRow();
                if (selectedCell.getColumn() == 6) {
                    DetailsPack act = cart.getSelectionModel().getSelectedItem();
                    if (tools.showAlertConf("Voulez-vous supprimer cette activité ?")) {
                        cart.getItems().clear();
                        pack.getListaactivities().remove(act);
                        rellenarcart();
                        calcularpriceFinal();
                    }
                }
            } catch (Exception e) {
                tools.showAlertErr("Error: " + e.getMessage());
            }
        }
    }
}
