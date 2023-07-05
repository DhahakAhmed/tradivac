package edu.tradivac;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import edu.tradivac.bda.ConexionDAO;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.entities.Tools;
import edu.tradivac.gui.home.HomeController;
import static javafx.application.Application.launch;

public class Main extends Application {
   private Tools tools = new Tools();
     private UserDAO userDAO = new UserDAO();
    
    private ConexionDAO connect = new ConexionDAO();
    private Connection conexion;
    
     
    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/edu/tradivac/gui//login/login/login.fxml"));
           primaryStage.setTitle("Expérience de voyage");
        Image icono = new Image(this.getClass().getResource("img/iconoAPP.png").toString());
        primaryStage.getIcons().add(icono);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }
  
    //@Override
    public void startOLD(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/edu/tradivac/gui/login/register/register.fxml"));
         FXMLLoader loader=new FXMLLoader();
          //Inicio la conexión
        try {

            if (connect.connect("root", "", "packs touristiques")) {
                conexion = connect.getConexion();
                userDAO.setConex(conexion);
                Parent root = null;
               loader.setLocation(getClass().getResource("/edu/tradivac/gui/home/home.fxml"));
            root = loader.load(); //Se ejecuta el Initialize
            userDAO.setConex(conexion);

            HomeController homeController = loader.getController();
            homeController.setConexion(conexion);
            homeController.setTools(tools);
            homeController.setconnect(connect);
            homeController.setuserDAO(userDAO);
           homeController.setMailuser("admin@gmail.com");
            homeController.homeInitialize();
//menu.getChildren().addAll(root);
        primaryStage.setTitle("Expérience de voyage ");
        Image icono = new Image(this.getClass().getResource("img/iconoAPP.png").toString());
        primaryStage.getIcons().add(icono);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
            }

        } catch (SQLException ex) {
            System.out.println("exception : ");
            System.out.println(ex.getMessage());
            
            tools.showAlertErr("Connexion à la base de données non établie");
        }
         
    }



    public static void main(String[] args) {
        launch(args);
    }
}