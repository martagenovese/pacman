package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class PinkGhost extends Ghost {

    private String imagePath;


    public PinkGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman){
        super(charactersPosition, tiles, pacman);
        imagePath = "src/images/ghosts/pink.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 23, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

        x=14;
        y=17;
        status=0;
        nGhost=3;

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
            System.out.println("Inizio: xtarget "+xTarget+", ytarget "+yTarget);
            System.out.println(charactersPosition.get(0,2));


            switch (pacman.getDirection()){
                case "Up": {
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
                case "Left": {
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
                case "Down": {
                    System.out.println("Down");
                    //down
                    for(int i=4; i>=0; i--)  {
                        if( yTarget+i>=0 && !(tiles[yTarget+i][xTarget] instanceof WallTile) ){
                            yTarget=yTarget+i;
                            break;
                        }
                    }
                    break;
                }
                case "Right": {
                    System.out.println("Right");
                    //right
                    for(int i=4; i>=0; i--)  {
                        if( xTarget+i>=0 && !(tiles[yTarget][xTarget+i] instanceof WallTile) ){
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
        status=0;
        move("up");
        move("up");
        move("up");
    }


    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(1170);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startGame();
        while(true) {
            chase();
        }

    }
}

