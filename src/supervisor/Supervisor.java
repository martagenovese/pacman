package supervisor;

import mcv.EventManager;
import mcv.Model;
import myclasses.My2DSyncArray;

public class Supervisor implements Runnable{
    My2DSyncArray charactersPosition;
    int lives;
    Model model;
    EventManager eventManager;

    public Supervisor(My2DSyncArray charactersPosition, Model model){
        this.charactersPosition = charactersPosition;
        lives = 3;
        this.model = model;
    }
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void run() {
        while (true) {
            int n = model.collision();
            if (n>=0) {
                //System.out.println(charactersPosition);
                try {
                    boolean isPacmanAlive = model.collisionProcedure(n);
                    if (!isPacmanAlive) {
                        lives--;
                        if (lives == 0) {
                            System.out.println("Game Over");
                            eventManager.stopGame(false);
                            System.exit(0);
                        }
                        isPacmanAlive = true;
                    } else {
                        System.out.println("ghostsEaten: " + model.ghostsEaten);
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}