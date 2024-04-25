package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class RedGhost extends Ghost {

    private String imagePath;


    public RedGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman){
        super(charactersPosition, tiles, pacman);
        imagePath = "src/images/ghosts/red.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 23, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
        x=12;
        y=17;
        status=0;
        nGhost=1;
    }

    protected void startGame() {
        status=0;
        move("up");
        move("right");
        move("up");
        move("up");
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
    public void run() {
        startGame();
        /*getToTheTarget(0,17);
        move("left");*/
        while(true){
            chase();
        }

    }
}