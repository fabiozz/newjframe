package classes;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Desenvolvedor implements Serializable {
    private String nome;

    public Desenvolvedor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

