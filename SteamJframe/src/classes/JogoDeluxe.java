package classes;

import java.io.Serializable;
import java.util.Objects;

public class JogoDeluxe extends Jogo implements Serializable {
    private String conteudoExtra;

    public JogoDeluxe(String titulo, double preco, Desenvolvedor desenvolvedor, String conteudoExtra) {
        super(titulo, preco, desenvolvedor);
        this.conteudoExtra = conteudoExtra;
    }

    public String getConteudoExtra() {
        return conteudoExtra;
    }

    public void setConteudoExtra(String conteudoExtra) {
        this.conteudoExtra = conteudoExtra;
    }

    @Override
    public String toString() {
        return super.toString() + " (Deluxe Edition: " + conteudoExtra + ")";
    }
}
