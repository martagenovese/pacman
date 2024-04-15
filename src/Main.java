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
        System.out.println("Model set to EventManedager");
        eventManager.setTable(grafica);
        System.out.println("Table set to EventManager");
    }
}
