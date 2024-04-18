package characters_classes;

import myclasses.My2DSyncArray;
import tiles_classes.*;
import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class RedGhost extends Ghost {

    private String imagePath;


    public RedGhost(My2DSyncArray charactersPosition, Tile[][] tiles){
        super(charactersPosition, tiles);
        imagePath = "src/images/ghosts/red.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
        x=17;
        y=12;
        status=0;
        nGhost=1;
    }

    protected void startGame() {
        status=0;
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
        int xTarget=charactersPosition.get(0,0);
        int yTarget=charactersPosition.get(0,1);
        reachTarget(xTarget, yTarget);
    }


    @Override
    public void run() {
        try {
            startGame();
            getToTheTarget(charactersPosition.get(0, 0), charactersPosition.get(0,1));
            System.out.println("chase");
            chase();
            System.out.println("scatter");
            scatter();
            sleep(210);
            eventManager.clearGhostPosition(this);
            eventManager.updateGhostPosition(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}