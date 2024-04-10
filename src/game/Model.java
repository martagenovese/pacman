package game;

import characters_classes.Pacman;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

public class Model {
    Tile[][] tiles;
    Pacman pacman;
    Thread pacmanThread;
    String nextDirection, lastDirection;


    // 0 - pacman, 1 - red ghost, 2 - pink ghost, 3 - blue ghost, 4 - orange ghost
    My2DSyncArray charactersPosition;
    Tile leftTile, rightTile, upTile, downTile, myTile;

    public Model() {
        pacman = new Pacman();
        //pacmanThread = new Thread(pacman);
        //pacmanThread.start();
        charactersPosition = new My2DSyncArray(5, 2);
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void movePacman(String direction) {
        pacman.move(direction);
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
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    protected String movePacman(String direction, Tile tile, Tile myTile) {
        if (tile==null) return null;
        if (!(tile instanceof WallTile)) {
            if (tile.isSuperFood()) {
                ((CrossableTile) tile).setSuperFood(false);
            } else if (tile.isDot()) ((CrossableTile) myTile).setDot(false);
            myTile.setIcon(null);
            movePacman(direction);
        } else nextDirection = direction;
        return nextDirection;
    }

}
