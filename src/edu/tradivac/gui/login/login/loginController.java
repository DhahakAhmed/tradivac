package edu.tradivac.gui.login.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import edu.tradivac.bda.ConexionDAO;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.entities.Tools;
import edu.tradivac.gui.home.HomeController;

public class loginController {

    private Connection conexion;
    private ConexionDAO connect = new ConexionDAO();
    private Tools tools = new Tools();
    private UserDAO userDAO = new UserDAO();

    @FXML
    private TextField mail;
    @FXML
    private TextField pwd;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Pane menu;

    private String mailUser="wassou93@live.fr";
    private String password="123456";

    public void initialize() {
          try {

            if (connect.connect("root", "", "tradivac")) {
                conexion = connect.getConexion();
                userDAO.setConex(conexion);
                   
                if (userDAO.checkuserYaRegistrado(mailUser)) {
                    if(userDAO.checkPWD(password)){
                       goToHomePage();
                    }else{
                        System.out.println("User not found");
                    }
               
            }
            }

        } catch (SQLException ex) {
            System.out.println("exception : ");
            System.out.println(ex.getMessage());
            
        }
        
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public ConexionDAO getconnect() {
        return connect;
    }

    public void setconnect(ConexionDAO connect) {
        this.connect = connect;
    }

    public Tools getTools() {
        return tools;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }

    public UserDAO getuserDAO() {
        return userDAO;
    }

    public void setuserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private boolean checkCampos() {
        mailUser = this.mail.getText();
        password = this.pwd.getText();

        if (mailUser.length() == 0 || password.length() == 0) {
            tools.showAlertErr("¡Para iniciar sesión debes rellenar los campos!");
        } else {
            String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
            if(!mailUser.matches(pattern)){
                tools.showAlertErr("¡Formato de mail incorrecto!");
            }else{
                return true;
            }
        }
        return false;
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            if (checkCampos()) {
                if (userDAO.checkuserYaRegistrado(mailUser)) {
                    if(userDAO.checkPWD(password)){
                        goToHomePage();
                    }else{
                        tools.showAlertErr("password incorrecta, ¡inténtelo de nuevo!");
                    }
                }else{
                    tools.showAlertErr("¡user no registrado!");
                }
            }
        } catch (SQLException e) {
            tools.showAlertErr(e.getMessage());
        }
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        FXMLLoader loader;
        Parent root = null;
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/login/register/register.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            menu.getChildren().addAll(root);
        } catch (IOException ex) {
            tools.showAlertErr("Error " + ex.getMessage());
        }
    }

    private void goToHomePage() {
        FXMLLoader loader;
        Parent root = null;
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/home/home.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            HomeController homeController = loader.getController();
            homeController.setConexion(conexion);
            homeController.setTools(tools);
            homeController.setconnect(connect);
            homeController.setuserDAO(userDAO);
            homeController.setMailuser(mailUser);
            homeController.homeInitialize();

            menu.getChildren().addAll(root);
        } catch (IOException ex) {
            tools.showAlertErr("Error " + ex.getMessage());
            
        }
    }
}
