package org.example.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Graph-view.fxml"));
        stage.getIcons().add(new Image("file:C:\\Users\\aleja\\OneDrive\\Documentos\\3rd Semester\\OOP Java\\assignment1/icon.jpg"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Assignment1");
        stage.setScene(scene);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
//        String css = this.getClass().getResource("styles.css").toExternalForm();
//        scene.getStylesheets().add(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}