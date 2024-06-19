package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarrinhoDeCompras implements Serializable {
    private static final Logger logger = Logger.getLogger(CarrinhoDeCompras.class.getName());
    private List<Jogo> jogos;

    public CarrinhoDeCompras() {
        this.jogos = new ArrayList<>();
    }

    public synchronized void adicionarJogo(Jogo jogo) throws CarrinhoDeComprasException {
        if (jogo == null) {
            logger.log(Level.SEVERE, "Tentativa de adicionar um jogo nulo.");
            throw new CarrinhoDeComprasException("O jogo não pode ser nulo.");
        }
        if (jogo.getPreco() < 0) {
            logger.log(Level.SEVERE, "Tentativa de adicionar um jogo com preço negativo.");
            throw new CarrinhoDeComprasException("O preço do jogo não pode ser negativo.");
        }
        this.jogos.add(jogo);
        logger.log(Level.INFO, "Jogo adicionado ao carrinho: " + jogo.getTitulo());
    }

    public synchronized void removerJogo(Jogo jogo) throws CarrinhoDeComprasException {
        if (jogo == null) {
            logger.log(Level.SEVERE, "Tentativa de remover um jogo nulo.");
            throw new CarrinhoDeComprasException("O jogo não pode ser nulo.");
        }
        if (!this.jogos.contains(jogo)) {
            logger.log(Level.WARNING, "Tentativa de remover um jogo que não está no carrinho.");
            throw new CarrinhoDeComprasException("O jogo não está no carrinho.");
        }
        this.jogos.remove(jogo);
        logger.log(Level.INFO, "Jogo removido do carrinho: " + jogo.getTitulo());
    }

    public synchronized List<Jogo> getJogos() {
        return new ArrayList<>(jogos);
    }

    public synchronized double calcularTotal() {
        double total = 0;
        for (Jogo jogo : jogos) {
            total += jogo.getPreco();
        }
        return total;
    }
}

