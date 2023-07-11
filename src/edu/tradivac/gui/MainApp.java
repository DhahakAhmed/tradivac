package edu.tradivac.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AJouterReclamationFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Interface de réclamations");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
