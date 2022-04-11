package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Aluno;
import br.edu.femass.projetobiblioteca.model.Autor;
import br.edu.femass.projetobiblioteca.model.Professor;
import br.edu.femass.projetobiblioteca.model.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioDao implements Dao<Usuario> {

    private final XStream xstream = new XStream();
    private static List<Usuario> usuarios = new ArrayList<>();

    public void updateXML(){
        String xml = xstream.toXML(usuarios);
        try {
            FileWriter fw = new FileWriter("usuarios.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Usuario objeto) {
        usuarios.add(objeto);
        updateXML();
    }

    @Override
    public List<Usuario> listar() {
        xstream.alias("Aluno", Aluno.class);
        xstream.alias("Professor", Professor.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Aluno.class, Professor.class, Usuario.class});
        usuarios = (List<Usuario>) xstream.fromXML(new File("usuarios.xml"));
        return usuarios;
    }

    @Override
    public void excluir(Usuario objeto) {
        usuarios.remove(objeto);
        updateXML();
    }
}
