package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;

public abstract class Ghost extends ImageIcon implements Runnable {

    protected int x, y;
    protected String direction;
    //0-> chase, 1->scatter, 2->frightened
    protected int status;
    protected My2DSyncArray charactersPosition;
    protected Tile[][] tiles;
    int nGhost;

    public Ghost(My2DSyncArray charactersPosition, Tile[][] tiles){
        this.charactersPosition=charactersPosition;
        this.tiles=tiles;
        direction="up";
    }

    public void frightened()  {
        if(status!=2){
            turnAround();
            status=2;
        }
        int xTarget, yTarget;
        do{
            xTarget=(int)(Math.random()*28+1);
            yTarget=(int)(Math.random()*36+1);
        }while(tiles[xTarget][yTarget] instanceof WallTile);

        getToTheTarget(xTarget, yTarget);
    }

    public void move(String direction) {
        switch (direction) {
            case "left" -> {
                if (y == 0) y = 27;
                else y--;
                direction="left";
            }
            case "right" -> {
                if (y == 27) y = 0;
                else y++;
                direction="right";
            }
            case "up" -> {
                x--;
                direction="up";
            }
            case "down" -> {
                x++;
                direction="down";
            }
        }
        charactersPosition.set(nGhost,0, y);
        charactersPosition.set(nGhost,0, x);
    }

    public void getToTheTarget(int xTarget, int yTarget) {
        while (x != xTarget && y != yTarget){
            reachTarget(xTarget, yTarget);
        }
    }

    public void reachTarget(int xTarget, int yTarget){
        //{ Up, Left, Down, Right }
        int[][] directions = {{y + 1, x}, {y, x - 1}, {y - 1, x}, {y, x + 1}};
        double distance;
        int chosenDirection;

        switch(direction){
            case "up":
                chosenDirection=0;
            case "left":
                chosenDirection=1;
            case "down":
                chosenDirection=2;
            case "right":
                chosenDirection=3;
            default:
                chosenDirection=2;
        }

        //TODO: gestire i tunnel
        double distanceMin = Math.sqrt(Math.pow(yTarget - directions[0][0], 2) + Math.pow(xTarget - directions[0][1], 2));

        //parte da 1 perchè assumo che la prima distanceMin sia quella di 0

        //solo se è un incrocio
        if (tiles[y][x].isIntersection()) {
            for (int i = 1; i < directions.length; i++) {
                if (!(tiles[directions[i][0]][directions[i][1]] instanceof WallTile) && i != chosenDirection) {
                    distance = Math.sqrt(Math.pow(yTarget - directions[i][0], 2) + Math.pow(xTarget - directions[i][1], 2));
                    if (distance < distanceMin) {
                        distanceMin = distance;
                        chosenDirection = i;
                    }
                }
            }
        }

        switch (chosenDirection) {
            case 0:
                move("up");
            case 1:
                move("left");
            case 2:
                move("down");
            case 3:
                move("right");
        }

    }
    protected abstract void startGame();
    public abstract void chase();
    public abstract void scatter();


    public void turnAround(){
        switch (direction) {
            case "up":
                move("down");
            case "left":
                move("right");
            case "down":
                move("up");
            case "right":
                move("left");
        }
    }

}
