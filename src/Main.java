public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        System.out.println("Model created");
        EventManager eventManager = new EventManager();
        System.out.println("EventManager created");
        Table grafica = new Table();
        System.out.println("Table created");


        grafica.setEventManager(eventManager);
        System.out.println("EventManager set to Table");
        eventManager.setModel(model);
        System.out.println("Model set to game.EventManedager");
        eventManager.setTable(grafica);
        System.out.println("Table set to EventManager");


        /*
        grafica.setEventManager(eventManager);
        System.out.println("EventManager set to Table");
        eventManager.setModelandTable(model, grafica);
        System.out.println("Model and Table set to EventManager");
        */
    }
}
