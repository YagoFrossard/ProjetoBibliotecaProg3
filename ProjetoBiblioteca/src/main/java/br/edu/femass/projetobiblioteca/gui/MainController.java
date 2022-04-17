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

    /*
    private Boolean autoresAberto = false;
    private Boolean generosAberto = false;
    private Boolean livrosAberto = false;
    private Boolean usuariosAberto = false;
    private Boolean emprestimosAberto = false;
    private Boolean copiasAberto = false;
     */

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
    private void BtnCopias_Click(ActionEvent evento) throws IOException {
        Stage janelaCopias = new Stage();
        FXMLLoader fxmlLoaderCopias = new FXMLLoader(HelloApplication.class.getResource("copia-view.fxml"));
        Scene copiaScene = new Scene(fxmlLoaderCopias.load(), 760, 406);
        janelaCopias.setTitle("Cópias");
        janelaCopias.setScene(copiaScene);
        janelaCopias.show();
    }

    @FXML
    private void BtnEmprestimos_Click(ActionEvent evento) throws IOException {
        Stage janelaEmprestimos = new Stage();
        FXMLLoader fxmlLoaderEmprestimos = new FXMLLoader(HelloApplication.class.getResource("emprestimo-view.fxml"));
        Scene emprestimoScene = new Scene(fxmlLoaderEmprestimos.load(), 760, 406);
        janelaEmprestimos.setTitle("Usuários");
        janelaEmprestimos.setScene(emprestimoScene);
        janelaEmprestimos.show();
    }

    @FXML
    private void BtnUsuarios_Click(ActionEvent evento) throws IOException {
        Stage janelaUsuarios = new Stage();
        FXMLLoader fxmlLoaderUsuarios = new FXMLLoader(HelloApplication.class.getResource("usuario-view.fxml"));
        Scene usuarioScene = new Scene(fxmlLoaderUsuarios.load(), 760, 406);
        janelaUsuarios.setTitle("Usuários");
        janelaUsuarios.setScene(usuarioScene);
        janelaUsuarios.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
