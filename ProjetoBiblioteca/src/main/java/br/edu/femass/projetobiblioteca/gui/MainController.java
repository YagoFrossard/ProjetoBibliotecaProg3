package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button BtnAutores;

    @FXML
    private Button BtnGeneros;

    @FXML
    private Button BtnUsuarios;

    @FXML
    private Button BtnLivros;

    @FXML
    private Button BtnCopias;

    @FXML
    private Button BtnEmprestimos;

    @FXML
    private void BtnAutores_Click(ActionEvent evento) throws IOException {
        Stage janelaAutores = new Stage();
        FXMLLoader fxmlLoaderAutores = new FXMLLoader(HelloApplication.class.getResource("autor-view.fxml"));
        Scene autorScene = new Scene(fxmlLoaderAutores.load(), 760, 406);
        janelaAutores.setTitle("Autores");
        janelaAutores.setScene(autorScene);
        janelaAutores.show();
    }

    @FXML
    private void BtnGeneros_Click(ActionEvent evento) throws IOException {
        Stage janelaGeneros = new Stage();
        FXMLLoader fxmlLoaderGeneros = new FXMLLoader(HelloApplication.class.getResource("genero-view.fxml"));
        Scene generoScene = new Scene(fxmlLoaderGeneros.load(), 760, 406);
        janelaGeneros.setTitle("Gêneros");
        janelaGeneros.setScene(generoScene);
        janelaGeneros.show();
    }

    @FXML
    private void BtnLivros_Click(ActionEvent evento) throws IOException {
        Stage janelaLivros = new Stage();
        FXMLLoader fxmlLoaderLivros = new FXMLLoader(HelloApplication.class.getResource("livro-view.fxml"));
        Scene livroScene = new Scene(fxmlLoaderLivros.load(), 760, 406);
        janelaLivros.setTitle("Livros");
        janelaLivros.setScene(livroScene);
        janelaLivros.show();
    }

    @FXML
    private void BtnCopias_Click(){}

    @FXML
    private void BtnEmprestimos_Click(){}

    @FXML
    private void BtnUsuarios_Click(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
