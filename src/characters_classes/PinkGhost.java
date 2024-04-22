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

            x=17;
            y=14;
            status=0;
        }

    @Override
    public void chase() {
        if(status!=0) {
            turnAround();
            status = 0;
        }
        int xTarget=charactersPosition.get(0,0);
        int yTarget=charactersPosition.get(0,1);
        reachTarget(xTarget, yTarget);
    }

    @Override
    public void scatter() {

    }
    @Override
    public void startGame() {

    }


    @Override
    public void run() {
        startGame();
        System.out.println("chase");
        chase();
        System.out.println("scatter");
        scatter();
    }
}

