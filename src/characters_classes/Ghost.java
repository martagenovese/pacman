 package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;
import mcv.*;

import java.awt.*;

 public abstract class Ghost extends ImageIcon implements Runnable {

    protected int x, y;
    protected String direction;
    //0-> chase, 1->scatter, 2->frightened, 3->eaten, 4->pacmanEaten
    protected int status, nGhost, waitingTime;
    protected My2DSyncArray charactersPosition;
    protected Tile[][] tiles;
    protected EventManager eventManager;
    protected Pacman pacman;
    protected boolean targetReached;
    protected int xTarget, yTarget;
    protected String colour;

    public Ghost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman, String colour){
        this.charactersPosition=charactersPosition;
        this.tiles=tiles;
        this.pacman=pacman;
        this.colour=colour;
        String imagePath = "src/images/ghosts/"+colour+".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 23, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

        targetReached=true;
        xTarget=0;
        yTarget=0;
        waitingTime=200;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
    private void setImagePath(String colour){
        String imagePath = "src/images/ghosts/"+colour+".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 23, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
    }
    public void setScared(boolean scared){
         if(scared){
             setImagePath("scared");
             status=2;
         } else {
             setImagePath(colour);
             status=0;
         }
    }
    public void setStatus(int n){
         status=n;
    }
    public int getStatus() {
         return status;
    }
    public int getX() {
         return x;
    }
    public int getY() {
         return y;
    }

    public void move(String dir) {
        eventManager.clearGhostPosition(this);
        switch (dir) {
            case "left" : {
                if (x == 0) x = 27;
                else x--;
                this.direction="left";
                //charactersPosition.set(nGhost,0, 1);
                break;
            }
            case "right" : {
                if (x == 27) x = 0;
                else x++;
                this.direction="right";
                //charactersPosition.set(nGhost,0, 3);
                break;
            }
            case "up" : {
                y--;
                this.direction="up";
                //charactersPosition.set(nGhost,0, 0);
                break;
            }
            case "down" : {
                y++;
                this.direction="down";
                //charactersPosition.set(nGhost,0, 2);
                break;
            }
        }
        //charactersPosition.set(nGhost,0, x);
        //charactersPosition.set(nGhost,1, y);
        charactersPosition.setX(nGhost, x);
        charactersPosition.setY(nGhost, y);
        eventManager.updateGhostPosition(this);
        try {
            Thread.sleep(waitingTime);//195
        } catch (InterruptedException ignored) {}
    }
    public void reachTarget(int xTarget, int yTarget){
        //{ Up, Left, Down, Right }
        int[][] directions = {{y - 1, x}, {y, x - 1}, {y + 1, x}, {y, x + 1}};
        if (directions[1][1] == -1) directions[1][1] = 27;
        if (directions[3][1] == 28) directions[3][1] = 0;
        double distance;
        int chosenDirection;
        int back;

        //salvo la direzione in cui sta andando pacman e la direzione opposta
        switch(direction){
            case "up":
                chosenDirection=0;
                back=2;
                break;
            case "left":
                chosenDirection=1;
                back=3;
                break;
            case "down":
                chosenDirection=2;
                back=0;
                break;
            case "right":
                chosenDirection=3;
                back=1;
                break;
            default:
                chosenDirection=2;
                back=0;
                break;
        }

        //se è un incrocio o se continuando su quella direzione trova un muro allora cambio choosenDirection
        if ( (tiles[y][x].isIntersection()) || (tiles[directions[chosenDirection][0]][directions[chosenDirection][1]] instanceof WallTile) ) {
            double distanceMin = 100;
            for (int i = 0; i < directions.length; i++) {
                if (!(tiles[directions[i][0]][directions[i][1]] instanceof WallTile) && i != back) {
                    distance = Math.sqrt(Math.pow(yTarget - directions[i][0], 2) + Math.pow(xTarget - directions[i][1], 2));
                    if (distance < distanceMin) {
                        distanceMin = distance;
                        chosenDirection = i;
                    }
                }
            }
        }

        //converto la direzione da int in stringa
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
    public abstract void startGame();
    public abstract void chase();
    public abstract void scatter();
    public void frightened()  {
         //quando cambia modalità inverte la direzione
         if(status!=2){
             targetReached=true;
             turnAround();
             status=2;
         }

         //controllo se il target è stato raggiunto
         if(x==xTarget&&y==yTarget) {
             targetReached=true;
         }

         //se è stato raggiunto acquisice randomicamente un nuovo target
         if(targetReached){
             do{
                 xTarget=(int)(Math.random()*26+1);
                 yTarget=(int)(Math.random()*29+4);
             } while(tiles[yTarget][xTarget] instanceof WallTile);
         }
         reachTarget(xTarget, yTarget);
    }
    public void eaten(){
         restorePosition();
         if(!pacman.isSuper()){
             startGame();
         }
    }
    protected abstract void restorePosition();
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
     public void pacmanEaten(){
         restorePosition();
         status=4;
         eventManager.updateGhostPosition(this);
         eventManager.clearGhostPosition(this);
    }

     @Override
     public void run() {
         startGame();
         while(true){
             //0-> chase, 1->scatter, 2->frightened, 3->eaten, 4->gameLost
             switch(status){
                 case 0: {
                     chase();
                     break;
                 }
                 case 1: {
                     scatter();
                     break;
                 }
                 case 2: {
                     frightened();
                     break;
                 }
                 case 3: {
                     eaten();
                     break;
                 }
                 case 4:{
                     restorePosition();
                     break;
                 }
                 case 5:{
                     startGame();
                     break;
                 }
             }
         }
     }

}
