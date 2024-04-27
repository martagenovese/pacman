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

    }

    @Override
    public void scatter() {
        if(status!=1) {
            turnAround();
            status = 1;
        }
        int xTarget=27;
        int yTarget=35;
        reachTarget(xTarget, yTarget);
    }
    @Override
    public void startGame() {
        status=0;
        move("up");
        move("left");
        move("up");
        move("up");
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1755);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startGame();
        while(true){
            scatter();
        }

    }



}
