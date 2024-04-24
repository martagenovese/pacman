package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;

import javax.swing.*;
import java.awt.*;

public class PinkGhost extends Ghost {

    private String imagePath;

    public PinkGhost(My2DSyncArray charactersPosition, Tile[][] tiles, Pacman pacman){
        super(charactersPosition, tiles, pacman);
        imagePath = "src/images/ghosts/pink.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

        x=14;
        y=17;
        status=0;
        nGhost=3;
    }

    @Override
    public void chase() {
        if(status!=0) {
            turnAround();
            status = 0;
        }
        int xTarget=charactersPosition.get(0,0);
        int yTarget=charactersPosition.get(0,1);
        switch (charactersPosition.get(0,2)){
            case 0: {
                if(yTarget<=8){
                    yTarget=4;
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
        while(true){
            scatter();
        }

    }
}

