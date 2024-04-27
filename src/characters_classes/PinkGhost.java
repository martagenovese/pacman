package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class PinkGhost extends Ghost {

    public PinkGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman, String colour){
        super(charactersPosition, tiles, pacman, colour);
        x=14;
        y=17;
        status=1;
        nGhost=3;
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
            xTarget=charactersPosition.get(0,0);
            yTarget=charactersPosition.get(0,1);
            System.out.println("Inizio: xtarget "+xTarget+", ytarget "+yTarget);
            System.out.println(charactersPosition.get(0,2));

            System.out.println(pacman.getDirection());
            switch (pacman.getDirection()){
                case "up": {
                    System.out.println("Up");
                    //up
                    for(int i=4; i>=0; i--)  {
                        if( yTarget-i>=0 && !(tiles[yTarget-i][xTarget] instanceof WallTile) ) {
                                yTarget = yTarget - i;
                                break;
                        }
                    }
                    break;
                }
                case "left": {
                    System.out.println("Left");
                    //left
                    for(int i=4; i>=0; i--)  {
                        if( xTarget-i>=0 && !(tiles[yTarget][xTarget-i] instanceof WallTile) ){
                            System.out.println("entra con i="+i);
                            xTarget=xTarget-i;
                            break;
                        }
                    }
                    break;
                }
                case "down": {
                    System.out.println("Down");
                    //down
                    for(int i=4; i>=0; i--)  {
                        if( yTarget+i<=35 && !(tiles[yTarget+i][xTarget] instanceof WallTile) ){
                            yTarget=yTarget+i;
                            break;
                        }
                    }
                    break;
                }
                case "right": {
                    System.out.println("Right");
                    //right
                    for(int i=4; i>=0; i--)  {
                        if( xTarget+i<=27 && !(tiles[yTarget][xTarget+i] instanceof WallTile) ){
                            xTarget=xTarget+i;
                            break;
                        }
                    }
                    break;
                }
            }
            System.out.println("Fine: xtarget "+xTarget+", ytarget "+yTarget);

        }
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void scatter() {
        if(status!=1) {
            turnAround();
            status = 1;
        }
        int xTarget=1;
        int yTarget=0;
        reachTarget(xTarget, yTarget);
    }
    @Override
    public void startGame() {
        try {
            Thread.sleep(1400);
        } catch (InterruptedException ignored) {}
        move("up");
        move("up");
        move("up");
        status=1;
    }
    protected void restorePosition(){
        x=14;
        y=17;
        charactersPosition.set(nGhost,0, x);
        charactersPosition.set(nGhost,1, y);
        eventManager.updateGhostPosition(this);
    }

}

