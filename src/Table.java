import javax.swing.*;
import java.awt.*;

// my packages
import characters_classes.Pacman;


public class Table extends JFrame {

    protected JLabel[][] tiles;
    protected EventManager eventManager;
    protected Pacman character;


    public Table() {
        setTitle("pacman");
        tiles = new JLabel[36][28];
        setLayout(new GridLayout(36, 28));
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                tiles[i][j] = new JLabel();
                tiles[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                tiles[i][j].setVerticalAlignment(SwingConstants.CENTER);
                tiles[i][j].setOpaque(true);
                add(tiles[i][j]);
            }
        }

        setSize(224 * 3, 288 * 3);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
        addKeyListener(eventManager);
    }


    public void setDot(int i, int j) {
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        tiles[i][j].setIcon(new ImageIcon(scaledImageDot));
    }
    public void setSuperFood(int i, int j) {
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImageSFood = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        tiles[i][j].setIcon(new ImageIcon(scaledImageSFood));
    }
    public void clearPacman(int i, int j) {
        tiles[i][j].setIcon(null);
    }

    public void setCharacter(Pacman character) {
        this.character = character;
        tiles[26][13].setIcon(character);
    }

    public void updatePosition() {
        tiles[character.getX()][character.getY()].setIcon(character);
    }

    public void updateScore(int score) {
        String scoreString = score+"";
        int yTile = 18;
        for (int i = scoreString.length()-1; i>=0; i--) {
            tiles[1][yTile].setForeground(Color.WHITE);
            tiles[1][yTile].setFont(new Font("Arial", Font.BOLD, 25));
            tiles[1][yTile].setText(scoreString.charAt(i)+"");
            yTile--;
        }
    }
}
