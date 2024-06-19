package classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import java.io.Serializable;
import java.util.Objects;

public class Jogo implements Serializable {
    private String titulo;
    private double preco;
    private Desenvolvedor desenvolvedor;

    public Jogo(String titulo, double preco, Desenvolvedor desenvolvedor) {
        this.titulo = titulo;
        this.preco = preco;
        this.desenvolvedor = desenvolvedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Double.compare(jogo.preco, preco) == 0 && Objects.equals(titulo, jogo.titulo) && Objects.equals(desenvolvedor, jogo.desenvolvedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, preco, desenvolvedor);
    }

    @Override
    public String toString() {
        return titulo + " - R$ " + preco + " - " + desenvolvedor.getNome();
    }
}
