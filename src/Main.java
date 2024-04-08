
public class Main {
    public static void main(String[] args) {
        Table grafica = new Table();
        System.out.println("Table created");
        Model model = new Model();
        System.out.println("Model created");
        EventManager eventManager = new EventManager();
        System.out.println("EventManager created");

        grafica.setEventManager(eventManager);
        System.out.println("EventManager set to Table");
        eventManager.setTable(grafica);
        System.out.println("Table set to EventManager");

        eventManager.setModel(model);

        System.out.println("Pacman's initial position: " + model.getCharacter().getX() + ", " + model.getCharacter().getY());


    }
}
