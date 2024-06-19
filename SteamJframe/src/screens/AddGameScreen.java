package screens;

import classes.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGameScreen extends JFrame {
    private JTextField titleField;
    private JTextField priceField;
    private JTextField developerField;
    private JButton addButton;
    private CarrinhoDeCompras carrinhoDeCompras;

    public AddGameScreen(CarrinhoDeCompras carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;

        setTitle("Adicionar Jogo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Título:");
        titleField = new JTextField();
        JLabel priceLabel = new JLabel("Preço:");
        priceField = new JTextField();
        JLabel developerLabel = new JLabel("Desenvolvedor:");
        developerField = new JTextField();
        addButton = new JButton("Adicionar");

        add(titleLabel);
        add(titleField);
        add(priceLabel);
        add(priceField);
        add(developerLabel);
        add(developerField);
        add(new JLabel());
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarJogo();
            }
        });
    }

    private void adicionarJogo() {
        String titulo = titleField.getText();
        String precoStr = priceField.getText();
        String desenvolvedorNome = developerField.getText();

        if (titulo.isEmpty() || precoStr.isEmpty() || desenvolvedorNome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Desenvolvedor desenvolvedor = new Desenvolvedor(desenvolvedorNome);
        Jogo jogo = new Jogo(titulo, preco, desenvolvedor);

        try {
            carrinhoDeCompras.adicionarJogo(jogo);
            JOptionPane.showMessageDialog(this, "Jogo adicionado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (CarrinhoDeComprasException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
