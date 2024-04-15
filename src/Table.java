import javax.swing.*;
import java.awt.*;

// my packages
import characters_classes.*;
import myclasses.SVGIcon;


public class Table extends JFrame {

    protected JLabel[][] tiles;
    protected EventManager eventManager;
    protected Pacman character;
    protected Ghost redGhost, cyanGhost, pinkGhost, orangeGhost;


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
        //SVGIcon originalIcon = new SVGIcon("src/images/dot.svg");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        tiles[i][j].setIcon(new ImageIcon(scaledImageDot));
    }
    public void setSuperFood(int i, int j) {
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        //SVGIcon originalIcon = new SVGIcon("src/images/dot.svg");
        Image originalImage = originalIcon.getImage();
        Image scaledImageSFood = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        tiles[i][j].setIcon(new ImageIcon(scaledImageSFood));
    }
    public void clearPacman(int i, int j) {
        tiles[i][j].setIcon(null);
        tiles[i][j].repaint();
    }

    public void setCharacter(Pacman character) {
        this.character = character;
        tiles[26][13].setIcon(character);
    }
    public void setRedGhost(RedGhost redGhost) {
        this.redGhost = redGhost;
        tiles[17][12].setIcon(redGhost);
    }
    public void setCyanGhost(CyanGhost cyanGhost) {
        this.cyanGhost = cyanGhost;
        tiles[17][11].setIcon(cyanGhost);
    }
    public void setPinkGhost(PinkGhost pinkGhost) {
        this.pinkGhost = pinkGhost;
        tiles[17][14].setIcon(pinkGhost);
    }
    public void setOrangeGhost(OrangeGhost orangeGhost) {
        this.orangeGhost = orangeGhost;
        tiles[17][15].setIcon(orangeGhost);
    }

    public void updatePosition() {
        tiles[character.getX()][character.getY()].setIcon(character);
        tiles[redGhost.getX()][redGhost.getY()].setIcon(redGhost);
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
