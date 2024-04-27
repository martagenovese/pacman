package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class RedGhost extends Ghost {


    public RedGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman, String colour){
        super(charactersPosition, tiles, pacman, colour);
        x=12;
        y=17;
        status=0;
        nGhost=1;
    }

    protected void startGame() {
        move("up");
        move("right");
        move("up");
        move("up");
        status=0;
    }

    @Override
    public void scatter(){
        if(status!=1) {
            turnAround();
            status = 1;
        }
        int xTarget=25;
        int yTarget=0;
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void chase() {
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
            targetReached=false;
            xTarget=charactersPosition.get(0,0);
            yTarget=charactersPosition.get(0,1);
        }

        reachTarget(xTarget, yTarget);
    }

    @Override
    public void eaten(){
        x=12;
        y=17;
        charactersPosition.set(nGhost,0, x);
        charactersPosition.set(nGhost,1, y);
        eventManager.updateGhostPosition(this);
        if(!pacman.isSuper()){
            startGame();
        }
    }




}