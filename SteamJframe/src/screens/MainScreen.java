package screens;

import classes.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JList<Jogo> jogoList;
    private DefaultListModel<Jogo> jogoListModel;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JButton viewLibraryButton;
    private CarrinhoDeCompras carrinhoDeCompras;
    private Biblioteca biblioteca;
    private List<Jogo> jogosDisponiveis;

    public MainScreen(CarrinhoDeCompras carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.biblioteca = new Biblioteca();
        this.jogosDisponiveis = new ArrayList<>();
        carregarJogosDisponiveis();

        setTitle("Tela Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jogoListModel = new DefaultListModel<>();
        jogoList = new JList<>(jogoListModel);
        JScrollPane scrollPane = new JScrollPane(jogoList);

        addToCartButton = new JButton("Adicionar ao Carrinho");
        viewCartButton = new JButton("Ver Carrinho");
        viewLibraryButton = new JButton("Ver Biblioteca");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(viewLibraryButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho();
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCarrinho();
            }
        });

        viewLibraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verBiblioteca();
            }
        });

        atualizarLista();
    }

    private void carregarJogosDisponiveis() {
        jogosDisponiveis.add(new Jogo("Jogo 1", 59.99, new Desenvolvedor("Dev 1")));
        jogosDisponiveis.add(new Jogo("Jogo 2", 39.99, new Desenvolvedor("Dev 2")));
        jogosDisponiveis.add(new JogoDeluxe("Jogo Deluxe 1", 99.99, new Desenvolvedor("Dev 3"), "Conteúdo Extra 1"));
        jogosDisponiveis.add(new JogoDeluxe("Jogo Deluxe 2", 119.99, new Desenvolvedor("Dev 4"), "Conteúdo Extra 2"));
    }

    private void atualizarLista() {
        jogoListModel.clear();
        for (Jogo jogo : jogosDisponiveis) {
            jogoListModel.addElement(jogo);
        }
    }

    private void adicionarAoCarrinho() {
        Jogo jogo = jogoList.getSelectedValue();
        if (jogo != null) {
            try {
                carrinhoDeCompras.adicionarJogo(jogo);
                JOptionPane.showMessageDialog(this, "Jogo adicionado ao carrinho", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (CarrinhoDeComprasException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para adicionar ao carrinho", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verCarrinho() {
        CarrinhoDeComprasWindow carrinhoDeComprasWindow = new CarrinhoDeComprasWindow(carrinhoDeCompras, biblioteca);
        carrinhoDeComprasWindow.setVisible(true);
    }

    private void verBiblioteca() {
        BibliotecaWindow bibliotecaWindow = new BibliotecaWindow(biblioteca);
        bibliotecaWindow.setVisible(true);
    }
}

