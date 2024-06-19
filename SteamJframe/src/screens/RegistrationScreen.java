package screens;

import classes.*;
import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class RegistrationScreen extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private Map<String, String> usuarios;

    public RegistrationScreen(Map<String, String> usuarios) {
        this.usuarios = usuarios;

        setTitle("Registro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Nome:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        registerButton = new JButton("Registrar");

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    private void registrar() {
        String nome = nameField.getText();
        String email = emailField.getText();
        char[] senha = passwordField.getPassword();
        String senhaStr = new String(senha);

        if (nome.isEmpty() || email.isEmpty() || senhaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuarios.containsKey(email)) {
            JOptionPane.showMessageDialog(this, "E-mail já cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Usuario usuario = new Usuario(nome, email);
            usuarios.put(email, senhaStr);
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
