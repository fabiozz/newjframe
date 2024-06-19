package screens;

import classes.*;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarrinhoDeComprasWindow extends JFrame {
    private JList<Jogo> jogoList;
    private DefaultListModel<Jogo> jogoListModel;
    private JButton removeButton;
    private JButton addToLibraryButton;
    private JLabel totalLabel;
    private CarrinhoDeCompras carrinhoDeCompras;
    private Biblioteca biblioteca;

    public CarrinhoDeComprasWindow(CarrinhoDeCompras carrinhoDeCompras, Biblioteca biblioteca) {
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.biblioteca = biblioteca;

        setTitle("Carrinho de Compras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        jogoListModel = new DefaultListModel<>();
        jogoList = new JList<>(jogoListModel);
        JScrollPane scrollPane = new JScrollPane(jogoList);

        removeButton = new JButton("Remover Jogo");
        addToLibraryButton = new JButton("Adicionar à Biblioteca");
        totalLabel = new JLabel("Total: R$ 0.00");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(removeButton);
        buttonPanel.add(addToLibraryButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(totalLabel, BorderLayout.NORTH);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerJogo();
            }
        });

        addToLibraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarBiblioteca();
            }
        });

        atualizarLista();
    }

    private void removerJogo() {
        Jogo jogo = jogoList.getSelectedValue();
        if (jogo != null) {
            try {
                carrinhoDeCompras.removerJogo(jogo);
                atualizarLista();
                JOptionPane.showMessageDialog(this, "Jogo removido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (CarrinhoDeComprasException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para remover", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarBiblioteca() {
        Jogo jogo = jogoList.getSelectedValue();
        if (jogo != null) {
            try {
                biblioteca.adicionarJogo(jogo);
                carrinhoDeCompras.removerJogo(jogo);
                atualizarLista();
                JOptionPane.showMessageDialog(this, "Jogo adicionado à biblioteca", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (BibliotecaException | CarrinhoDeComprasException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para adicionar à biblioteca", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarLista() {
        jogoListModel.clear();
        for (Jogo jogo : carrinhoDeCompras.getJogos()) {
            jogoListModel.addElement(jogo);
        }
        totalLabel.setText("Total: R$ " + carrinhoDeCompras.calcularTotal());
    }
}
