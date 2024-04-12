package characters_classes;

import tiles_classes.*;
import javax.swing.*;

public class Ghost extends ImageIcon {

    protected int x, y;

    public Ghost(){
    }

    public void frightened(Tile[][] tiles){
        int xTarget, yTarget;
        do{
            xTarget=(int)(Math.random()*28+1);
            yTarget=(int)(Math.random()*36+1);
        }while(tiles[xTarget][yTarget] instanceof WallTile);

        getToTheTarget(tiles, xTarget, yTarget);
    }

    public void move(String direction) {
        switch (direction) {
            case "left" -> {
                if (y == 0) y = 27;
                else y--;
            }
            case "right" -> {
                if (y == 27) y = 0;
                else y++;
            }
            case "up" -> x--;
            case "down" -> x++;
        }
    }

    public void getToTheTarget(Tile[][] tiles, int xTarget, int yTarget){

        while(x!=xTarget && y!=yTarget){

            //{ Up, Right, Down, Left }
            int[][] bo = { {y+1,x} , {y,x+1} , {y-1,x} , {y,x-1} };
            double distanceMin = Math.sqrt( Math.pow(yTarget-bo[0][0] , 2) + Math.pow(xTarget-bo[0][1] , 2) );
            double distance;
            int direction=0;

            //parte da 1 perchè assumo che la prima distanceMin sia quella di 0
            for (int i = 1; i < bo.length; i++) {
                if(!(tiles[bo[i][0]][bo[i][1]] instanceof WallTile)){
                    distance = Math.sqrt( Math.pow(yTarget-bo[i][0] , 2) + Math.pow(xTarget-bo[i][1] , 2) );
                    if(distance < distanceMin){
                        distanceMin = distance;
                        direction  = i;
                    }
                }
            }

            switch (direction) {
                case 0:
                    move("up");
                case 1:
                    move("right");
                case 2:
                    move("down");
                case 3:
                    move("left");
            }
        }
    }


}
