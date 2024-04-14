import characters_classes.Pacman;
import myclasses.My2DSyncArray;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Model {
    protected Tile[][] tiles;
    protected Pacman pacman;
    protected int score, lives, dotsCounter, fruit;

    // 0 - pacman, 1 - red ghost, 2 - pink ghost, 3 - blue ghost, 4 - orange ghost
    protected My2DSyncArray charactersPosition;
    protected Tile leftTile, rightTile, upTile, downTile, myTile;

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
                tiles[i][j].setIntersection(true);
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
                if (i == 6 && j == 1 || i == 6 && j == 26 || i == 26 && j == 1 || i == 26 && j == 26) {
                    tiles[i][j] = new CrossableTile(i, j);
                    ((CrossableTile) tiles[i][j]).setSuperFood(true);
                } else ((CrossableTile) tiles[i][j]).setDot(true);
                //tiles[i][j].setBackground(Color.PINK);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Model() {
        pacman = new Pacman();
        charactersPosition = new My2DSyncArray(5, 2);
        tiles = new Tile[36][28];

        //tutte crossable all'inizio
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                tiles[i][j] = new CrossableTile(i, j);
            }
        }
        arrageWalls();
        arrangeIntersections();
        arrangeDots();
        tiles[26][13].setPacman(true);

        // aggiungere punteggio, vite e frutti
        score = 0;
        lives = 3;
        dotsCounter = 0;
        fruit = 2;
    }

    public Pacman getPacman() {
        return pacman;
    }

    protected void updatePosition() {
        charactersPosition.set(0, 0, pacman.getX());
        charactersPosition.set(0, 1, pacman.getY());
    }
    public Tile getLeftTile() {
        try {
            leftTile = tiles[pacman.getX()][pacman.getY() - 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            leftTile = tiles[pacman.getX()][27];
        }
        return leftTile;
    }
    public Tile getRightTile() {
        try {
            rightTile = tiles[pacman.getX()][pacman.getY() + 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            rightTile = tiles[pacman.getX()][0];
        }
        return rightTile;
    }
    public Tile getUpTile() {
        upTile = tiles[pacman.getX() - 1][pacman.getY()];
        return upTile;
    }
    public Tile getDownTile() {
        downTile = tiles[pacman.getX() + 1][pacman.getY()];
        return downTile;
    }
    public Tile getMyTile() {
        myTile = tiles[pacman.getX()][pacman.getY()];
        return myTile;
    }

    public void movePacman(String direction, Tile tile, Tile myTile) {
        if (tile==null) { return; }
        if (!(tile instanceof WallTile)) {
            if (tile.isSuperFood()) {
                ((CrossableTile) tile).setSuperFood(false);
                score += 50;
                dotsCounter++;
                if (dotsCounter == 70 || dotsCounter == 240) {
                    fruit--;
                }
            } else if (tile.isDot()) {
                ((CrossableTile) myTile).setDot(false);
                score += 10;
            }
            myTile.setPacman(false);
            pacman.move(direction);
            tile.setPacman(false);
        }
    }

    public int getScore() {
        return score;
    }
    public int getLives() {
        return lives;
    }
    public int getFruit() {
        return fruit;
    }
    public boolean getDotsCounter() {
        return (dotsCounter==70 || dotsCounter==240);
    }
    public boolean isGameOver() {
        return lives == 0;
    }
}
