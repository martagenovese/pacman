package supervisor;

import mcv.Model;
import myclasses.My2DSyncArray;

public class Supervisor implements Runnable{
    My2DSyncArray charactersPosition;
    int lives;
    Model model;

    public Supervisor(My2DSyncArray charactersPosition, Model model){
        this.charactersPosition = charactersPosition;
        lives = 3;
        this.model = model;
    }

    @Override
    public void run() {
        while (true) {
            int n = model.collision();
            if (n>=0) {
                System.out.println(charactersPosition);
                try {
                    model.collisionProcedure(n);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}