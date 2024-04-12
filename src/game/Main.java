package game;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        System.out.println("game.Model created");
        EventManager eventManager = new EventManager();
        System.out.println("game.EventManager created");
        Table grafica = new Table();
        System.out.println("game.Table created");


        grafica.setEventManager(eventManager);
        System.out.println("game.EventManager set to game.Table");
        eventManager.setModel(model);
        System.out.println("game.Model set to game.EventManager");
        eventManager.setTable(grafica);
        System.out.println("game.Table set to game.EventManager");


        /*
        grafica.setEventManager(eventManager);
        System.out.println("game.EventManager set to game.Table");
        eventManager.setModelandTable(model, grafica);
        System.out.println("game.Model and game.Table set to game.EventManager");
        */
    }
}
