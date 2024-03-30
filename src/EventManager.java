import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class EventManager implements KeyListener {
    Pacman character;
    My2DSyncArray charactersPosition;
    Tile[][] tiles;
    Table table;
    private Timer timer = new Timer();
    private TimerTask moveTask;
    private String lastDirection;
    public EventManager() {
        timer = new Timer();
    }
    public void setCharacter(Pacman character) {
        this.character = character;
        tiles[character.x][character.y].setIcon(character);
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
        if (key == KeyEvent.VK_LEFT) {
            lastDirection = "left";
        } else if (key == KeyEvent.VK_RIGHT) {
            lastDirection = "right";
        } else if (key == KeyEvent.VK_UP) {
            lastDirection = "up";
        } else if (key == KeyEvent.VK_DOWN) {
            lastDirection = "down";
        }

        if (moveTask != null) {
            moveTask.cancel();
        }

        moveTask = new TimerTask() {
            @Override
            public void run() {
                if (lastDirection.equals("left") && !(tiles[character.x][character.y - 1] instanceof WallTile)) {
                    tiles[character.x][character.y].setIcon(null);
                    character.move("left");
                } else if (lastDirection.equals("right") && !(tiles[character.x][character.y + 1] instanceof WallTile)) {
                    tiles[character.x][character.y].setIcon(null);
                    character.move("right");
                } else if (lastDirection.equals("up") && !(tiles[character.x - 1][character.y] instanceof WallTile)) {
                    tiles[character.x][character.y].setIcon(null);
                    character.move("up");
                } else if (lastDirection.equals("down") && !(tiles[character.x + 1][character.y] instanceof WallTile)) {
                    tiles[character.x][character.y].setIcon(null);
                    character.move("down");
                }
                updatePosition();
                table.repaint();
            }
        };

        timer.scheduleAtFixedRate(moveTask, 0, 200);
    }

    private void updatePosition() {
        charactersPosition.set(0, 0, character.x);
        charactersPosition.set(0, 1, character.y);
        tiles[character.x][character.y].setIcon(character);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
