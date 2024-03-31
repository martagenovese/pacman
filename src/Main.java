import characters_classes.Pacman;
import tiles_classes.Tile;

public class Main {
    public static void main(String[] args) {
        My2DSyncArray charactersPosition = new My2DSyncArray(5, 10);
        Tile[][] tiles = new Tile[36][28];


        Table grafica = new Table(tiles);

        EventManager pacmanmnrg = new EventManager();
        Pacman pacman = new Pacman();
        pacmanmnrg.setTiles(tiles);
        pacmanmnrg.setCharacter(pacman);
        pacmanmnrg.setCharactersPosition(charactersPosition);
        pacmanmnrg.setTable(grafica);

        grafica.addKeyListener(pacmanmnrg);


    }
}
