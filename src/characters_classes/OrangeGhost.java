package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;

import javax.swing.*;
import java.awt.*;

public class OrangeGhost extends Ghost {

        private String imagePath;

        public OrangeGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman, String colour){
            super(charactersPosition, tiles, pacman, colour);
            x=15;
            y=17;
            status=0;
            nGhost=4;
        }

    @Override
    public void chase() {
        double distanceFromPacman;
        if(status!=0) {
            targetReached=true;
            turnAround();
            status = 0;
        }
        //controlla se il target è stato raqggiunto
        if(x==xTarget&&y==yTarget) {
            targetReached=true;
        }
        //se è stato raggiunto acquisice un nuovo target
        if(targetReached){
            targetReached = false;
            distanceFromPacman =  Math.sqrt(Math.pow(y - charactersPosition.get(0,1), 2) + Math.pow(x - charactersPosition.get(0,0), 2));

            if (distanceFromPacman<8) {
                xTarget = charactersPosition.get(0, 0);
                yTarget = charactersPosition.get(0, 1);
            }else{
                xTarget=27;
                yTarget=35;
            }
        }
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void scatter() {
        if(status!=1) {
            turnAround();
            status = 1;
        }
        xTarget=27;
        yTarget=35;
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void startGame() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        move("up");
        move("left");
        move("up");
        move("up");
        status=0;
    }

    @Override
    public void eaten(){
        x=15;
        y=17;
        charactersPosition.set(nGhost,0, x);
        charactersPosition.set(nGhost,1, y);
        eventManager.updateGhostPosition(this);
        if(!pacman.isSuper()){
            startGame();
        }
    }




}
