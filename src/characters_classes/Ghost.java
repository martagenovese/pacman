 package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;
import mcv.*;

public abstract class Ghost extends ImageIcon implements Runnable {

    protected int x, y;
    protected String direction;
    //0-> chase, 1->scatter, 2->frightened
    protected int status;
    protected My2DSyncArray charactersPosition;
    protected Tile[][] tiles;
    protected int nGhost;
    protected EventManager eventManager;
    protected Pacman pacman;


    public Ghost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman){
        this.charactersPosition=charactersPosition;
        this.tiles=tiles;
        this.pacman=pacman;
    }
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void frightened()  {
        if(status!=2){
            turnAround();
            status=2;
        }
        int xTarget, yTarget;
        do{
            //TODO: va nei tunnel modifica
            xTarget=(int)(Math.random()*26+1);
            yTarget=(int)(Math.random()*29+4);
        } while(tiles[yTarget][xTarget] instanceof WallTile);
        System.out.println("xTarget: "+xTarget+" yTarget: "+yTarget);

        getToTheTarget(xTarget, yTarget);
    }

    public void move(String direction) {
        eventManager.clearGhostPosition(this);
        switch (direction) {
            case "left" : {
                if (x == 0) x = 27;
                else x--;
                this.direction="left";
                break;
            }
            case "right" : {
                if (x == 27) x = 0;
                else x++;
                this.direction="right";
                break;
            }
            case "up" : {
                y--;
                this.direction="up";
                break;
            }
            case "down" : {
                y++;
                this.direction="down";
                break;
            }
        }
        charactersPosition.set(nGhost,0, x);
        charactersPosition.set(nGhost,1, y);
        eventManager.updateGhostPosition(this);
        try {
            Thread.currentThread().sleep(195);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getToTheTarget(int xTarget, int yTarget) {
        while(x != xTarget || y != yTarget) {
            reachTarget(xTarget, yTarget);
        }
    }

    public void reachTarget(int xTarget, int yTarget){
        //{ Up, Left, Down, Right }
        int[][] directions = {{y - 1, x}, {y, x - 1}, {y + 1, x}, {y, x + 1}};
        double distance;
        int chosenDirection;

        switch(direction){
            case "up":
                chosenDirection=0;
                break;
            case "left":
                chosenDirection=1;
                break;
            case "down":
                chosenDirection=2;
                break;
            case "right":
                chosenDirection=3;
                break;
            default:
                chosenDirection=2;
                break;
        }

        //TODO: gestire i tunnel

        //tiles[directions[chosenDirection][0]][directions[chosenDirection][1]] instanceof WallTile
        if(tiles[directions[chosenDirection][0]][directions[chosenDirection][1]] instanceof WallTile){
            //TODO: TROVA NUOVA DIREZIONE
            double distanceMin = Math.sqrt(Math.pow(yTarget - directions[0][0], 2) + Math.pow(xTarget - directions[0][1], 2));
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

        //solo se è un incrocio
        if (tiles[y][x].isIntersection()) {
            double distanceMin = Math.sqrt(Math.pow(yTarget - directions[0][0], 2) + Math.pow(xTarget - directions[0][1], 2));
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
                break;
            case 1:
                move("left");
                break;
            case 2:
                move("down");
                break;
            case 3:
                move("right");
                break;
        }

    }
    protected abstract void startGame();
    public abstract void chase();
    public abstract void scatter();


    public void turnAround(){
        int[][] directions = {{y + 1, x}, {y, x - 1}, {y - 1, x}, {y, x + 1}};
        //TODO: CONTROLLA MURI
        switch (direction) {
            case "up":
                if(!(tiles[y-1][x] instanceof WallTile)){move("down");}
                break;
            case "left":
                if(!(tiles[y][x-1] instanceof WallTile)){move("right");}
                break;
            case "down":
                if(!(tiles[y+1][x] instanceof WallTile)){move("up");}
                break;
            case "right":
                if(!(tiles[y][x+1] instanceof WallTile)){move("left");}
                break;
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
