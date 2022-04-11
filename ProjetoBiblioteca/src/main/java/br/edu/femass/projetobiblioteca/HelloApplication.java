package br.edu.femass.projetobiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage janelaMain) throws IOException {
        FXMLLoader fxmlLoaderMain = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene sceneAutores = new Scene(fxmlLoaderMain.load(), 760, 406);
        janelaMain.setTitle("Autores");
        janelaMain.setScene(sceneAutores);
        janelaMain.show();
    }

    public static void main(String[] args) {
        launch();
    }
}