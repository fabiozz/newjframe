package screens;

import classes.Biblioteca;
import classes.BibliotecaException;
import classes.Jogador;
import classes.Jogo;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaWindow extends JFrame {
    private JList<Jogo> jogoList;
    private DefaultListModel<Jogo> jogoListModel;
    private JButton addButton;
    private JButton removeButton;
    private Biblioteca biblioteca;

    public BibliotecaWindow(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        jogoListModel = new DefaultListModel<>();
        jogoList = new JList<>(jogoListModel);
        JScrollPane scrollPane = new JScrollPane(jogoList);

        addButton = new JButton("Adicionar Jogo");
        removeButton = new JButton("Remover Jogo");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarJogo();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerJogo();
            }
        });

        atualizarLista();
    }

    private void adicionarJogo() {
        // Lógica para adicionar um jogo à biblioteca
    }

    private void removerJogo() {
        Jogo jogo = jogoList.getSelectedValue();
        if (jogo != null) {
            try {
                biblioteca.removerJogo(jogo);
                atualizarLista();
                JOptionPane.showMessageDialog(this, "Jogo removido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (BibliotecaException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para remover", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarLista() {
        jogoListModel.clear();
        for (Jogo jogo : biblioteca.getJogos()) {
            jogoListModel.addElement(jogo);
        }
    }
}
