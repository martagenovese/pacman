package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.*;

import javax.swing.*;
import java.awt.*;

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

    @Override
    public void scatter(Tile[][] tiles){
        getToTheTarget(tiles, 25,0);

        /*


        21,4
        26,4
        26,8
         */
        if(x==21 && y==1){
            move("right");
        }

    }

    public void chase() {
        int i;
    }

}
