// java packages
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

// my packages
import tiles_classes.*;



public class Table extends JFrame {
    Icon pacman, redghost, pinkghost, blueghost, orangeghost;

    // 0 - pacman, 1 - red ghost, 2 - pink ghost, 3 - blue ghost, 4 - orange ghost
    int[][] charactersPosition = new int[5][10];   // così accediamo alle loro posizioni subito

    Tile[][] tiles = new Tile[36][28];

    private void arrageWalls() {
        InputStream f;
        Scanner s;
        try {
            f = new FileInputStream("src/construction/walls.csv");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] coordinates = s.nextLine().split(";");
                int i = Integer.parseInt(coordinates[0]);
                int j = Integer.parseInt(coordinates[1]);
                tiles[i][j] = new WallTile();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void arrangeIntersections() {
        InputStream f;
        Scanner s;
        try {
            f = new FileInputStream("src/construction/intersections.csv");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] coordinates = s.nextLine().split(";");
                int i = Integer.parseInt(coordinates[0]);
                int j = Integer.parseInt(coordinates[1]);
                tiles[i][j] = new Intersection(i, j);
                //tiles[i][j].setBackground(Color.PINK);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void arrangeDots() {
        InputStream f;
        Scanner s;
        try {
            f = new FileInputStream("src/construction/dots.csv");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] coordinates = s.nextLine().split(";");
                int i = Integer.parseInt(coordinates[0]);
                int j = Integer.parseInt(coordinates[1]);
                if (i==6 && j==1 || i==6 && j==26 || i==29 && j==1 || i==29 && j==26) {
                    tiles[i][j] = new CrossableTile(i, j);
                    ((CrossableTile) tiles[i][j]).setSuperFood(true);
                } else ((CrossableTile) tiles[i][j]).setDot(true);
                //tiles[i][j].setBackground(Color.PINK);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void arrangeTable() {
        // creiamo inizialmente tutte come crossable
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                tiles[i][j] = new CrossableTile(i, j);
            }
        }
        arrageWalls();
        arrangeIntersections();
        arrangeDots();

        // aggiungiamo tutti i tiles al frame
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                add(tiles[i][j]);
            }
        }
    }


    public Table() {
        setTitle("Table");
        setLayout(new GridLayout(36, 28));
        arrangeTable();

        setSize(224*3, 288*3);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Table();
    }
}
