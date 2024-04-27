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
            status=0;
            nGhost=2;
        }

    @Override
    public void chase() {
        if(status!=0) {
            //quando cambia status si gira
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

            switch (charactersPosition.get(0,2)){
                case 0: {
                    //up
                    for(int i=2; i>=0; i--)  {
                        if( yTarget-i>=0){
                            yTarget=yTarget-i;
                            break;
                        }
                    }
                }
                case 1: {
                    //left
                    for(int i=2; i>=0; i--)  {
                        if( xTarget-i>=0){
                            xTarget=xTarget-i;
                            break;
                        }
                    }
                }
                case 2: {
                    //down
                    for(int i=2; i>=0; i--)  {
                        if( yTarget+i<=35){
                            yTarget=yTarget+i;
                            break;
                        }
                    }
                }
                case 3: {
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
            for(int i=xTarget-charactersPosition.get(1,0);i>=0;i--){
                if( (xTarget+(xTarget-charactersPosition.get(1,0))>=0) && (xTarget+(xTarget-charactersPosition.get(1,0))<=27) ){
                    xTarget=xTarget+(xTarget-charactersPosition.get(1,0));
                    break;
                }
            }
            for(int i=yTarget-charactersPosition.get(1,1);i>=0;i--){
                if( (yTarget+(yTarget-charactersPosition.get(1,1))>=0) && (yTarget+(yTarget-charactersPosition.get(1,1))<=35) ){
                    yTarget=yTarget+(yTarget-charactersPosition.get(1,1));
                    break;
                }
            }

            for (int i = 0; i <  xTarget-charactersPosition.get(0,0); i--) {
                for (int j = 0; j < yTarget-charactersPosition.get(0,1); j--) {
                    if(!(tiles[yTarget-i][xTarget-j] instanceof WallTile)){
                        xTarget=xTarget-i;
                        yTarget=yTarget-j;
                        break;
                        //USCIRE DA TUTTI E DUE I CICLI
                    }
                }
                
            }
            
            xTarget=xTarget+(xTarget-charactersPosition.get(1,0));
            yTarget=yTarget+(yTarget-charactersPosition.get(1,1));
            
            
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
        status=0;
        move("up");
        move("up");
        move("up");
    }

    @Override
    public void run() {
//        try {
//            Thread.currentThread().sleep(585);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        startGame();
//        while(true){
//            chase();
//        }
    }

}
