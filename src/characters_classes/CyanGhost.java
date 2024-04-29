package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import javax.swing.*;
import java.awt.*;

public class CyanGhost extends Ghost {

        private String imagePath;

        public CyanGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman, String colour){
            super(charactersPosition, tiles, pacman, colour);
            x=13;
            y=17;
            status=1;
            nGhost=2;
            //charactersPosition.set(nGhost,0,x);
            charactersPosition.setX(nGhost, x);
            //charactersPosition.set(nGhost,1,y);
            charactersPosition.setY(nGhost, y);
        }

    @Override
    public void chase() {
        if(status!=0) {
            //quando cambia status si gira
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
            //xTarget=charactersPosition.get(0,0);
            //yTarget=charactersPosition.get(0,1);
            xTarget=charactersPosition.getX(0);
            yTarget=charactersPosition.getY(0);

            switch (pacman.getDirection()){
                case "up": {
                    //up
                    for(int i=2; i>=0; i--)  {
                        if( yTarget-i>=0){
                            yTarget=yTarget-i;
                            break;
                        }
                    }
                }
                case "left": {
                    //left
                    for(int i=2; i>=0; i--)  {
                        if( xTarget-i>=0){
                            xTarget=xTarget-i;
                            break;
                        }
                    }
                }
                case "down": {
                    //down
                    for(int i=2; i>=0; i--)  {
                        if( yTarget+i<=35){
                            yTarget=yTarget+i;
                            break;
                        }
                    }
                }
                case "right": {
                    //right
                    for(int i=2; i>=0; i--)  {
                        if( xTarget+i<=27 ){
                            xTarget=xTarget+i;
                            break;
                        }
                    }
                }
            }
            //Conntrollo che le coordinate non escano dai margini dell'array
            for(int i=xTarget-charactersPosition.getX(1);i>=0;i--){
                if( (xTarget+(xTarget-charactersPosition.getX(1))>=0) && (xTarget+(xTarget-charactersPosition.getX(1))<=27) ){
                    xTarget=xTarget+(xTarget-charactersPosition.getX(1));
                    break;
                }
            }
            for(int i=yTarget-charactersPosition.getY(1);i>=0;i--){
                if( (yTarget+(yTarget-charactersPosition.getY(1))>=0) && (yTarget+(yTarget-charactersPosition.getY(1))<=35) ){
                    yTarget=yTarget+(yTarget-charactersPosition.getY(1));
                    break;
                }
            }
            OuterLoop:
            for (int i = 0; i <  yTarget-charactersPosition.getY(0); i++) {
                for (int j = 0; j < xTarget-charactersPosition.getX(0); j++) {
                    if(!(tiles[yTarget-i][xTarget-j] instanceof WallTile)){
                        xTarget=xTarget-i;
                        yTarget=yTarget-j;
                        break OuterLoop;
                    }
                }
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
        int xTarget=0;
        int yTarget=35;
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void startGame() {
        try {
            Thread.sleep(waitingTime*4);
        } catch (InterruptedException ignored) {}
        move("up");
        move("up");
        move("up");
        status=1;
    }
    public void restorePosition(){
        if(x!=13 || y!=17) {
            eventManager.clearGhostPosition(this);
        }
        x=13;
        y=17;
        //charactersPosition.set(nGhost,0, x);
        //charactersPosition.set(nGhost,1, y);
        charactersPosition.setX(nGhost, x);
        charactersPosition.setY(nGhost, y);
        eventManager.updateGhostPosition(this);
    }



}
