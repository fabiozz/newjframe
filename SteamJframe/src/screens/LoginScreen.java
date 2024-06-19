package screens;

import classes.Biblioteca;
import classes.CarrinhoDeCompras;
import classes.Jogador;
import classes.Usuario;
import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private Map<String, String> usuarios = new HashMap<>();
    private CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();

    public LoginScreen() {
        usuarios.put("teste@teste.com", "senha123");

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Registrar");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    private void login() {
        String email = emailField.getText();
        char[] senha = passwordField.getPassword();
        String senhaStr = new String(senha);

        if (usuarios.containsKey(email) && usuarios.get(email).equals(senhaStr)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            MainScreen mainScreen = new MainScreen(carrinhoDeCompras);
            mainScreen.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        RegistrationScreen registrationScreen = new RegistrationScreen(usuarios);
        registrationScreen.setVisible(true);
    }
}

