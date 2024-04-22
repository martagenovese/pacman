package mcv;

import characters_classes.*;
import myclasses.My2DSyncArray;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Model {
    protected Tile[][] tiles;
    protected Pacman pacman;
    protected int score, lives, dotsCounter, fruit;

    // 0 - pacman, 1 - red ghost, 2 - pink ghost, 3 - cyan ghost, 4 - orange ghost
    protected My2DSyncArray charactersPosition;
    protected Tile leftTile, rightTile, upTile, downTile, myTile;
    protected String lastDirection, nextDirection;
    protected Thread rThread, pThread, cThread, oThread;
    protected Ghost r, p, c, o;

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
        tiles[15][13] = new WallTile();
        tiles[15][14] = new WallTile();
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
        charactersPosition = new My2DSyncArray(5, 3);
        pacman = new Pacman(charactersPosition);
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
        r = new RedGhost(charactersPosition, tiles, pacman);
        p = new PinkGhost(charactersPosition, tiles, pacman);
        c = new CyanGhost(charactersPosition, tiles, pacman);
        o = new OrangeGhost(charactersPosition, tiles, pacman);

        rThread = new Thread(r);
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
            leftTile = tiles[pacman.getY() - 1][pacman.getX()];
        } catch (ArrayIndexOutOfBoundsException ex) {
            leftTile = tiles[27][pacman.getX()];
        }
        return leftTile;
    }
    public Tile getRightTile() {
        try {
            rightTile = tiles[pacman.getY() + 1][pacman.getX()];
        } catch (ArrayIndexOutOfBoundsException ex) {
            rightTile = tiles[0][pacman.getX()];
        }
        return rightTile;
    }
    public Tile getUpTile() {
        upTile = tiles[pacman.getY()][pacman.getX() - 1];
        return upTile;
    }
    public Tile getDownTile() {
        downTile = tiles[pacman.getY()][pacman.getX() + 1];
        return downTile;
    }
    public Tile getMyTile() {
        myTile = tiles[pacman.getY()][pacman.getX()];
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
            tile.setPacman(true);
        }
    }
    public void keepDirection(String direction) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //non funziona questo secondo me
        Method method = this.getClass().getMethod("get"+direction+"Tile");
        Tile nextTile = (Tile) method.invoke(this);
        if (lastDirection == null) lastDirection = direction.toLowerCase();
        nextDirection = direction.toLowerCase();
        if (nextTile instanceof WallTile) {
            method = this.getClass().getMethod("get"+(lastDirection.charAt(0)+"").toUpperCase()+lastDirection.substring(1)+"Tile");
        } else {
            lastDirection = nextDirection;
        }
        movePacman(lastDirection, (Tile) method.invoke(this), getMyTile());
    }

    public RedGhost getRedGhost() {
        return (RedGhost) r;
    }
    public PinkGhost getPinkGhost() {
        return (PinkGhost) p;
    }
    public CyanGhost getCyanGhost() {
        return (CyanGhost) c;
    }
    public OrangeGhost getOrangeGhost() {
        return (OrangeGhost) o;
    }
    public Thread getRedGhostThread() {
        return rThread;
    }
    public Thread getPinkGhostThread() {
        return pThread;
    }
    public Thread getCyanGhostThread() {
        return cThread;
    }
    public Thread getOrangeGhostThread() {
        return oThread;
    }
    public void startRedGhost(){rThread.start();}

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