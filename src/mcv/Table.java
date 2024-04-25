package mcv;

import javax.swing.*;
import java.awt.*;

// my packages
import characters_classes.*;
import mcv.EventManager;


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


    public void setDot(int y, int x) {
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        //SVGIcon originalIcon = new SVGIcon("src/images/dot.svg");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        tiles[y][x].setIcon(new ImageIcon(scaledImageDot));
    }
    public void setSuperFood(int y, int x) {
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        //SVGIcon originalIcon = new SVGIcon("src/images/dot.svg");
        Image originalImage = originalIcon.getImage();
        Image scaledImageSFood = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        tiles[y][x].setIcon(new ImageIcon(scaledImageSFood));
    }
    public void clearPacman(int x, int y) {
        tiles[y][x].setIcon(null);
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
        tiles[17][13].setIcon(cyanGhost);
    }
    public void setPinkGhost(PinkGhost pinkGhost) {
        this.pinkGhost = pinkGhost;
        tiles[17][14].setIcon(pinkGhost);
    }
    public void setOrangeGhost(OrangeGhost orangeGhost) {
        this.orangeGhost = orangeGhost;
        tiles[17][15].setIcon(orangeGhost);
    }
    public void setScoreBar() {
        String score = "SCORE";
        int xTile = 9;
        for (int i = 0; i < score.length(); i++) {
            tiles[1][xTile].setForeground(Color.WHITE);
            tiles[1][xTile].setFont(new Font("Arial", Font.BOLD, 25));
            tiles[1][xTile].setText(score.charAt(i) + "");
            xTile++;
        }
        tiles[1][18].setForeground(Color.WHITE);
        tiles[1][18].setFont(new Font("Arial", Font.BOLD, 25));
        tiles[1][18].setText("0");
    }
    public void setLives(){
        ImageIcon pacman = new ImageIcon("src/images/pacman/right.png");
        Image originalImage = pacman.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        pacman.setImage(new ImageIcon(scaledImageDot).getImage());
        tiles[35][2].setIcon(pacman);
        //tiles[35][2].repaint();
        tiles[35][4].setIcon(pacman);
        //tiles[35][4].repaint();
        tiles[35][6].setIcon(pacman);
        //tiles[6][35].repaint();
    }
    public void  setFruit() {
        ImageIcon fruit = new ImageIcon("src/images/fruit.png");
        Image originalImage = fruit.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        fruit.setImage(new ImageIcon(scaledImageDot).getImage());
        tiles[35][25].setIcon(fruit);
        tiles[35][23].setIcon(fruit);
    }

    public void updatePosition() {
        tiles[character.getY()][character.getX()].setIcon(character);
        tiles[redGhost.getY()][redGhost.getX()].setIcon(redGhost);
    }
    public void clearGhost(int x, int y, boolean isDot, boolean isSuperFood) {
        if (!isDot && !isSuperFood) tiles[y][x].setIcon(null);
        else if (isSuperFood) setSuperFood(y, x);
        else setDot(y, x);
    }
    public void updateGhost(Ghost ghost) {
        tiles[ghost.getY()][ghost.getX()].setIcon(ghost);
    }
    public void updateScore(int score) {
        String scoreString = score+"";
        int xTile = 18;
        for (int i = scoreString.length()-1; i>=0; i--) {
            tiles[1][xTile].setForeground(Color.WHITE);
            tiles[1][xTile].setFont(new Font("Arial", Font.BOLD, 25));
            tiles[1][xTile].setText(scoreString.charAt(i)+"");
            xTile--;
        }
    }
}
