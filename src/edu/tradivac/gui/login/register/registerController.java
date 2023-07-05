package edu.tradivac.gui.login.register;

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
import org.apache.commons.codec.digest.DigestUtils;
import edu.tradivac.bda.ConexionDAO;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.entities.Tools;
import edu.tradivac.entities.User;
import edu.tradivac.gui.login.login.loginController;

public class registerController {

    private Connection conexion;
    private ConexionDAO connect = new ConexionDAO();
    private Tools tools = new Tools();
    private User user;
    private UserDAO userDAO = new UserDAO();

    @FXML
    private TextField nombre;
    @FXML
    private TextField mail;
    @FXML
    private TextField mobile;
    @FXML
    private TextField card;
    @FXML
    private TextField pwd;
    @FXML
    private TextField pwd2;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Pane menu;

    String nombreUser;
    String mailUser;
    String tlfUser;
    String cardUser;
    String password1;
    String password2;
    int tlf_numero;
    int card_numero;

    public void initialize() {

        //Inicio la conexión
        try {

            if (connect.connect("root", "", "touristpacks")) {
                conexion = connect.getConexion();
                userDAO.setConex(conexion);
            }

        } catch (SQLException ex) {
            System.out.println("exception : ");
            System.out.println(ex.getMessage());
            
            tools.showAlertErr("No se ha establecido conexión con la base de datos");
        }

    }

    private boolean checkCampos() {
        nombreUser = this.nombre.getText();
        mailUser = this.mail.getText();
        tlfUser = this.mobile.getText();
        cardUser = this.card.getText();
        password1 = this.pwd.getText();
        password2 = this.pwd2.getText();
        System.out.println(cardUser);
        int errores = 0;

        try {

            if (nombreUser.length() == 0 || mailUser.length() == 0 || tlfUser.length() == 0 || cardUser.length() == 0
                    || password1.length() == 0 || password2.length() == 0) {
                tools.showAlertErr("¡Para registrarte debes rellenar los campos!");
                errores++;
            } else {
                String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                if (!mailUser.matches(pattern)) {
                    tools.showAlertErr("¡El mail está mal escrito!");
                    errores++;
                }
                if (tlfUser.length() != 9) {
                    tools.showAlertErr("¡El teléfono está mal escrito!");
                    errores++;
                }
                if (cardUser.length() != 10) {
                    tools.showAlertErr("¡La card está mal escrita!");
                    errores++;
                }
                if (!password1.equalsIgnoreCase(password2)) {
                    tools.showAlertErr("¡Las passwords NO coinciden!");
                    errores++;
                }
            }
        } catch (NumberFormatException e) {
            tools.showAlertErr("¡Teléfono y card deben ser valores numéricos!");
            errores++;
        }
        if (errores == 0) {
            tlf_numero = Integer.parseInt(tlfUser);
            card_numero = Integer.parseInt(cardUser);
            return true;
        }
        return false;
    }

    @FXML
    private void register(ActionEvent event) {
        try {
            if (checkCampos()) {
                user = new User(nombreUser, mailUser, tlf_numero, card_numero, password1);
                //INSERT DEL OBJETO
                if (!userDAO.checkuserYaRegistrado(user.getMail())) {
                    //Encriptar contrasena
                    String pwdMD5=DigestUtils.md5Hex(password1);
                    user.setPwd(pwdMD5);
                    userDAO.registraruser(user);
                    tools.showAlertInfo("¡user registrado!");
                } else {
                    tools.showAlertErr("¡El user con dicho correo ya está registrado!");
                }
            }
        } catch (SQLException e) {
             System.out.println("exception : ");
            System.out.println(e.getMessage());
            tools.showAlertErr(e.getMessage());
            System.out.println("ERROR: " + e.getErrorCode());
        }
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        FXMLLoader loader;
        Parent root = null;
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/tradivac/gui/login/login/login.fxml"));
            root = loader.load(); //Se ejecuta el Initialize

            loginController loginController = loader.getController();
            loginController.setConexion(conexion);
            loginController.setTools(tools);
            loginController.setuserDAO(userDAO);
            loginController.setconnect(connect);

            menu.getChildren().addAll(root);
        } catch (IOException ex) {
            tools.showAlertErr(ex.getMessage());
        }
    }
}
