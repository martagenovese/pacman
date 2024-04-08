import characters_classes.Pacman;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class EventManager implements KeyListener {
    My2DSyncArray charactersPosition;
    Tile[][] tiles;
    Tile leftTile, rightTile, upTile, downTile, myTile, nextTile;
    Table table;
    private String lastDirection;
    private Model model;
    public EventManager() {}
    public void setModel(Model model) {
        this.model = model;
    }
    public void setCharactersPosition(My2DSyncArray charactersPosition) {
        this.charactersPosition = charactersPosition;
    }
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        leftTile = model.getLeftTile();
        rightTile = model.getRightTile();
        upTile = model.getUpTile();
        downTile = model.getDownTile();
        myTile = model.getMyTile();

        if (key == KeyEvent.VK_LEFT) {
            lastDirection = "left";
            nextTile = leftTile;
        } else if (key == KeyEvent.VK_RIGHT) {
            lastDirection = "right";
            nextTile = rightTile;
        } else if (key == KeyEvent.VK_UP) {
            lastDirection = "up";
            nextTile = upTile;
        } else if (key == KeyEvent.VK_DOWN) {
            lastDirection = "down";
            nextTile = downTile;
        }

        movePacman(lastDirection, nextTile, myTile);
        model.updatePosition();
        table.repaint();

    }

    private void movePacman(String direction, Tile tile, Tile myTile) {
        if (!(tile instanceof WallTile) && tile!=null) {
            if (tile.isSuperFood()) {
                ((CrossableTile) tile).setSuperFood(false);
            } else if (tile.isDot()) ((CrossableTile) myTile).setDot(false);
            myTile.setIcon(null);
            model.setCharacterPosition(direction);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
