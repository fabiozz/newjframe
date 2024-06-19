package classes;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Jogador implements Serializable {
    private String nome;
    private List<Biblioteca> bibliotecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.bibliotecas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarBiblioteca(Biblioteca biblioteca) {
        this.bibliotecas.add(biblioteca);
    }

    public List<Biblioteca> getBibliotecas() {
        return new ArrayList<>(bibliotecas);
    }
}