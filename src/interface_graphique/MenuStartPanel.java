// MenuStartPanel.java
package interface_graphique;

import javax.swing.*;
import java.awt.*;

public class MenuStartPanel extends JPanel {

    public MenuStartPanel(MainFrame mainFrame) {
        // Configuration du layout
        setLayout(new GridLayout(2, 1, 10, 10));

        // Boutons pour le menu de démarrage
        JButton newGameButton = new JButton("Nouvelle Partie");
        JButton exitButton = new JButton("Quitter la Partie");

        // Ajout des boutons au panneau
        add(newGameButton);
        add(exitButton);

        // Action pour le bouton Nouvelle Partie
        newGameButton.addActionListener(e -> mainFrame.showPanel("Map"));

        // Action pour le bouton Quitter la Partie
        exitButton.addActionListener(e -> System.exit(0));

        // Ici, on pourrait ajouter une méthode pour créer une nouvelle partie
    }
}
