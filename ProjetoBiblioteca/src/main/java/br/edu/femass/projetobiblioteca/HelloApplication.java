package br.edu.femass.projetobiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage janelaAutores) throws IOException {
        //Primeira janela - Autores
        FXMLLoader fxmlLoaderAutores = new FXMLLoader(HelloApplication.class.getResource("autor-view.fxml"));
        Scene sceneAutores = new Scene(fxmlLoaderAutores.load(), 760, 406);
        janelaAutores.setTitle("Autores");
        janelaAutores.setScene(sceneAutores);
        janelaAutores.show();
        /*
        //Segunda janela - Generos
        Stage janelaGeneros = new Stage();
        FXMLLoader fxmlLoaderGeneros = new FXMLLoader(HelloApplication.class.getResource("genero-view.fxml"));
        Scene scene2 = new Scene(fxmlLoaderGeneros.load(), 760, 406);
        janelaGeneros.setTitle("Generos");
        janelaGeneros.setScene(scene2);
        janelaGeneros.show();
        //Terceira janela - Livros
        Stage janelaLivros = new Stage();
        FXMLLoader fxmlLoaderLivros = new FXMLLoader(HelloApplication.class.getResource("livro-view.fxml"));
        Scene sceneLivros = new Scene(fxmlLoaderLivros.load(), 760, 406);
        janelaLivros.setTitle("Livros");
        janelaLivros.setScene(sceneLivros);
        janelaLivros.show();

         */
    }

    public static void main(String[] args) {
        launch();
    }
}