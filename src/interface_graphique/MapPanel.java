package interface_graphique;


import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private int clos = 10;
    private int rows = 10;
    private JLabel[][] grid;

    public MapPanel(){
        setLayout(new GridLayout(rows,clos));
        grid = new JLabel[rows][clos];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < clos; j++){
                grid[i][j] = new JLabel(".", SwingConstants.CENTER);
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                add(grid[i][j]);
            }
        }
    }

}
