import characters_classes.Pacman;
import tiles_classes.Tile;

import java.util.Timer;
import java.util.TimerTask;

public class Model {
    Tile[][] tiles;
    Pacman character;
    My2DSyncArray charactersPosition;
    Tile leftTile, rightTile, upTile, downTile, myTile, nextTile;
    Table table;
    private Timer timer;
    private TimerTask moveTask;
    private String lastDirection, nextDirection;

    public void setCharacter(Pacman character) {
        this.character = character;
        tiles[character.x][character.y].setIcon(character);
    }
    public void setCharacterPosition(String direction) {
        character.move(direction);
    }
    protected void setCharactersPosition(My2DSyncArray charactersPosition) {
        this.charactersPosition = charactersPosition;
    }
    protected void updatePosition() {
        charactersPosition.set(0, 0, character.x);
        charactersPosition.set(0, 1, character.y);
        tiles[character.x][character.y].setIcon(character);
    }

    public Tile getLeftTile() {
        try {
            leftTile = tiles[character.x][character.y - 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            leftTile = tiles[character.x][27];
        }
        return leftTile;
    }
    public Tile getRightTile() {
        try {
            rightTile = tiles[character.x][character.y + 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            rightTile = tiles[character.x][0];
        }
        return rightTile;
    }
    public Tile getUpTile() {
        upTile = tiles[character.x - 1][character.y];
        return upTile;
    }
    public Tile getDownTile() {
        downTile = tiles[character.x + 1][character.y];
        return downTile;
    }
    public Tile getMyTile() {
        myTile = tiles[character.x][character.y];
        return myTile;
    }



}
