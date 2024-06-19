package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Biblioteca implements Serializable {
    private static final Logger logger = Logger.getLogger(Biblioteca.class.getName());
    private List<Jogo> jogos;

    public Biblioteca() {
        this.jogos = new ArrayList<>();
    }

    public synchronized void adicionarJogo(Jogo jogo) throws BibliotecaException {
        if (jogo == null) {
            logger.log(Level.SEVERE, "Tentativa de adicionar um jogo nulo.");
            throw new BibliotecaException("O jogo não pode ser nulo.");
        }
        if (jogo.getPreco() < 0) {
            logger.log(Level.SEVERE, "Tentativa de adicionar um jogo com preço negativo.");
            throw new BibliotecaException("O preço do jogo não pode ser negativo.");
        }
        this.jogos.add(jogo);
        logger.log(Level.INFO, "Jogo adicionado à biblioteca: " + jogo.getTitulo());
    }

    public synchronized void removerJogo(Jogo jogo) throws BibliotecaException {
        if (jogo == null) {
            logger.log(Level.SEVERE, "Tentativa de remover um jogo nulo.");
            throw new BibliotecaException("O jogo não pode ser nulo.");
        }
        if (!this.jogos.contains(jogo)) {
            logger.log(Level.WARNING, "Tentativa de remover um jogo que não está na biblioteca.");
            throw new BibliotecaException("O jogo não está na biblioteca.");
        }
        this.jogos.remove(jogo);
        logger.log(Level.INFO, "Jogo removido da biblioteca: " + jogo.getTitulo());
    }

    public synchronized List<Jogo> getJogos() {
        return new ArrayList<>(jogos);
    }
}
