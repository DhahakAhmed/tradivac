package edu.tradivac.gui.menuAdmin;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import edu.tradivac.entities.Tools;
import edu.tradivac.gui.menuAdmin.createActivity.CreateActivityController;
import edu.tradivac.gui.menuAdmin.list.ListController;

public class menuAdminController {

    private Connection conex;
    private Tools tools = new Tools();
    @FXML
    private Button botoncreate;
    @FXML
    private Button botonListar;

    public void initialize() {

    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public Tools getTools() {
        return tools;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    @FXML
    public void createactivity(ActionEvent event) {
        FXMLLoader loader;
        Parent root = null;
        try {

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/menuAdmin/createactivity/createactivity.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            CreateActivityController createAct = loader.getController();
            createAct.setConexion(conex);
            createAct.setTools(tools);
            createAct.setDefault();

            Pane parentBoton = (Pane) botoncreate.getParent();

            parentBoton.getChildren().setAll(root);

        } catch (IOException ex) {
            tools.showAlertErr("Error " + ex.getMessage());
        }
    }

    @FXML
    private void listActivities(ActionEvent event) {
        FXMLLoader loader;
        Parent root = null;
        try {

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/menuAdmin/list/list.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            ListController listAct = loader.getController();
            listAct.setConexion(conex);
            listAct.setTools(tools);
            listAct.setDefault();

            Pane parentBoton = (Pane) botoncreate.getParent();

            parentBoton.getChildren().setAll(root);

        } catch (IOException ex) {
            tools.showAlertErr("Error " + ex.getMessage());
        }
    }
}
