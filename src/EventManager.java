import characters_classes.Pacman;
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
    Tile leftTile, rightTile, upTile, downTile, myTile;
    Table table;
    private Timer timer;
    private TimerTask moveTask;
    private String lastDirection, nextDirection;
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
            if (leftTile instanceof WallTile) nextDirection = "left";
            else lastDirection = "left";
        } else if (key == KeyEvent.VK_RIGHT) {
            if (rightTile instanceof WallTile) nextDirection = "right";
            else lastDirection = "right";
        } else if (key == KeyEvent.VK_UP) {
            if (upTile instanceof WallTile) nextDirection = "up";
            else lastDirection = "up";
        } else if (key == KeyEvent.VK_DOWN) {
            if (downTile instanceof WallTile) nextDirection = "down";
            else lastDirection = "down";
        }

        if (moveTask != null) {
            moveTask.cancel();
        }

        moveTask = new TimerTask() {
            boolean flag;
            @Override
            public void run() {
                flag = false;
                try {
                    leftTile = tiles[character.x][character.y - 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    leftTile = tiles[character.x][27];
                }
                try {
                    rightTile = tiles[character.x][character.y + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    rightTile = tiles[character.x][0];
                }
                upTile = tiles[character.x - 1][character.y];
                downTile = tiles[character.x + 1][character.y];
                myTile = tiles[character.x][character.y];
                //System.out.println("left: " + leftTile);

                /*
                // aspetta che la direzione sia valida
                if (nextDirection != null) {
                    if (nextDirection.equals("left") && !(leftTile instanceof WallTile)) {
                        flag = true;
                    } else if (nextDirection.equals("right") && !(rightTile instanceof WallTile)) {
                        flag = true;
                    } else if (nextDirection.equals("up") && !(upTile instanceof WallTile)) {
                        flag = true;
                    } else if (nextDirection.equals("down") && !(downTile instanceof WallTile)) {
                        flag = true;
                    } else flag = false;

                    if (flag) lastDirection = nextDirection;
                }
                 */

                // continua ad andare nella stessa direzione dell'ultimo tasto premuto
                if (lastDirection.equals("left") && !(leftTile instanceof WallTile)) {
                    if (leftTile.isSuperFood()) {
                        ((CrossableTile) leftTile).setSuperFood(false);
                    } else ((CrossableTile) myTile).setDot(false);
                    myTile.setIcon(null);
                    character.move("left");
                } else if (lastDirection.equals("right") && !(rightTile instanceof WallTile)) {
                    if (rightTile.isSuperFood()) {
                        ((CrossableTile) rightTile).setSuperFood(false);
                    } else ((CrossableTile) myTile).setDot(false);
                    myTile.setIcon(null);
                    character.move("right");
                } else if (lastDirection.equals("up") && !(upTile instanceof WallTile)) {
                    if (upTile.isSuperFood()) {
                        ((CrossableTile) upTile).setSuperFood(false);
                    } else ((CrossableTile) myTile).setDot(false);
                    myTile.setIcon(null);
                    character.move("up");
                } else if (lastDirection.equals("down") && !(downTile instanceof WallTile)) {
                    if (downTile.isSuperFood()) {
                        ((CrossableTile) downTile).setSuperFood(false);
                    } else ((CrossableTile) myTile).setDot(false);
                    myTile.setIcon(null);
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
