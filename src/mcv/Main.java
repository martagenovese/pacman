package mcv;

import mcv.EventManager;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        System.out.println("mcv.Model created");
        EventManager eventManager = new EventManager();
        System.out.println("mcv.EventManager created");
        Table grafica = new Table();
        System.out.println("mcv.Table created");

        grafica.setEventManager(eventManager);
        System.out.println("mcv.EventManager set to mcv.Table");
        eventManager.setModel(model);
        System.out.println("mcv.Model set to EventManedager");
        eventManager.setTable(grafica);
        System.out.println("mcv.Table set to mcv.EventManager");
    }
}
