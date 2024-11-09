package interface_graphique;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Configuration de la fenêtre principale
        setTitle("Seigneur des Anneaux RPG");
        setSize(800, 600); // Taille de base de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // Ici, on pourra ajouter les panels comme MenuStartPanel, PlayerInfoPanel, etc.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
