package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.*;
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

public class CopiaDao implements Dao<Copia> {

    private final XStream xstream = new XStream();
    private static List<Copia> copias = new ArrayList<>();

    public void updateXML(){
        String xml = xstream.toXML(copias);
        try {
            FileWriter fw = new FileWriter("copias.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Copia objeto) {
        copias.add(objeto);
        updateXML();
    }

    @Override
    public List<Copia> listar() {
        xstream.alias("Copia", Copia.class);
        xstream.alias("Autor", Autor.class);
        xstream.alias("Genero", Genero.class);
        xstream.alias("LivroOriginal", Livro.class);
        xstream.alias("Aluno", Aluno.class);
        xstream.alias("Professor", Professor.class);
        xstream.alias("Empréstimo", Emprestimo.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Livro.class, Genero.class, Autor.class, Copia.class, Usuario.class,
                Professor.class, Aluno.class, Emprestimo.class});
        copias = (List<Copia>) xstream.fromXML(new File("copias.xml"));
        return copias;
    }

    @Override
    public void excluir(Copia objeto) {
        copias.remove(objeto);
        updateXML();
    }
}
