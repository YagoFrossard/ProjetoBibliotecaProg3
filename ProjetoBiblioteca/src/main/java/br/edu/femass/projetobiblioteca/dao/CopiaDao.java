package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Autor;
import br.edu.femass.projetobiblioteca.model.Copia;
import br.edu.femass.projetobiblioteca.model.Genero;
import br.edu.femass.projetobiblioteca.model.Livro;
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
            FileWriter fw = new FileWriter("autores.xml");
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
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Livro.class, Genero.class, Autor.class, Copia.class});
        copias = (List<Copia>) xstream.fromXML(new File("copias.xml"));
        return copias;
    }

    @Override
    public void excluir(Copia objeto) {
        copias.remove(objeto);
        updateXML();
    }
}
