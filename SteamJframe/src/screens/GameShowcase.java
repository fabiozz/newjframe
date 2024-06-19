package screens;

import classes.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;

public class GameShowcase extends JPanel {
    private JLabel titleLabel;
    private JLabel priceLabel;
    private JLabel developerLabel;
    private Jogo jogo;

    public GameShowcase(Jogo jogo) {
        this.jogo = jogo;
        setLayout(new GridLayout(3, 1));

        titleLabel = new JLabel("Título: " + jogo.getTitulo());
        priceLabel = new JLabel("Preço: R$ " + jogo.getPreco());
        developerLabel = new JLabel("Desenvolvedor: " + jogo.getDesenvolvedor().getNome());

        add(titleLabel);
        add(priceLabel);
        add(developerLabel);
    }
}
