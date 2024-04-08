import characters_classes.Pacman;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

public class Model {
    Tile[][] tiles;
    Pacman character;
    My2DSyncArray charactersPosition;
    Tile leftTile, rightTile, upTile, downTile, myTile, nextTile;

    public Model() {
        character = new Pacman();
    }

    public Pacman getCharacter() {
        return character;
    }

    public My2DSyncArray getCharactersPosition() {
        return charactersPosition;
    }


    public void setCharacterPosition(String direction) {
        character.move(direction);
    }
    protected void setCharactersPosition(My2DSyncArray charactersPosition) {
        this.charactersPosition = charactersPosition;
    }
    protected void updatePosition() {
        charactersPosition.set(0, 0, character.getX());
        charactersPosition.set(0, 1, character.getY());
        tiles[character.getX()][character.getY()].setIcon(character);
    }
    public Tile getLeftTile() {
        try {
            leftTile = tiles[character.getX()][character.getY() - 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            leftTile = tiles[character.getX()][27];
        }
        return leftTile;
    }
    public Tile getRightTile() {
        try {
            rightTile = tiles[character.getX()][character.getY() + 1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            rightTile = tiles[character.getX()][0];
        }
        return rightTile;
    }
    public Tile getUpTile() {
        upTile = tiles[character.getX() - 1][character.getY()];
        return upTile;
    }
    public Tile getDownTile() {
        downTile = tiles[character.getX() + 1][character.getY()];
        return downTile;
    }
    public Tile getMyTile() {
        myTile = tiles[character.getX()][character.getY()];
        return myTile;
    }
    public Tile getNextTile() {
        return nextTile;
    }
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    protected void movePacman(String direction, Tile tile, Tile myTile) {
        if (!(tile instanceof WallTile) && tile!=null) {
            if (tile.isSuperFood()) {
                ((CrossableTile) tile).setSuperFood(false);
            } else if (tile.isDot()) ((CrossableTile) myTile).setDot(false);
            myTile.setIcon(null);
            setCharacterPosition(direction);
        }
    }

}
