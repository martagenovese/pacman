package supervisor;

import mcv.EventManager;
import mcv.Main;
import mcv.Model;
import myclasses.My2DSyncArray;

public class Supervisor implements Runnable{
    My2DSyncArray charactersPosition;
    int lives, ghostsEaten;
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
    private int nGhostsEaten(){
        int g = 1, n = 0;
        for (int i = 12; i < 16; i++) {
            if (charactersPosition.get(g, 1) == 17 && charactersPosition.get(g, 0) == i) {
                n++;
            }
            g++;
        }
        return n;
    }

    @Override
    public void run() {
        while (true) {
            int n = model.collision();
            if (n>=0) {
                System.out.println(charactersPosition);
                try {
                    boolean isPacmanAlive = model.collisionProcedure(n);
                    if (!isPacmanAlive) {
                        eventManager.setStartGhost(2);
                        lives--;
                        System.out.println("lives: " + lives);
                        if (lives == 0) {
                            System.out.println("Game Over");
                            eventManager.stopGame(false);
                        }
                        isPacmanAlive = true;
                    } else {
                        ghostsEaten = nGhostsEaten();
                        System.out.println(Math.pow(2, ghostsEaten));
                        // da numeri assurdi
                        if (ghostsEaten!=0) model.score += 100 * (int) Math.pow(2, ghostsEaten);
                        System.out.println("ghostsEaten: " + ghostsEaten);
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}