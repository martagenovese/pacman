import characters_classes.Pacman;
import tiles_classes.Tile;

public class Main {
    public static void main(String[] args) {
        Table grafica = new Table();
        Model model = new Model();
        EventManager eventManager = new EventManager();

        grafica.setEventManager(eventManager);
        eventManager.setTable(grafica);
        eventManager.setModel(model);



    }
}
