package interface_graphique;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Seigneur des Anneaux RPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation du CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Création des panneaux
        MenuStartPanel menuStartPanel = new MenuStartPanel(this);
        PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel();
        MapPanel mapPanel = new MapPanel();

        // Ajout des panneaux au CardLayout
        mainPanel.add(menuStartPanel, "MenuStart");
        mainPanel.add(mapPanel, "Map");

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);

        // Afficher le menu de démarrage au début
        cardLayout.show(mainPanel, "MenuStart");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
