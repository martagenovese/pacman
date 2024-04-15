package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;
import tiles_classes.Tile;

import javax.swing.*;
import java.awt.*;

public class CyanGhost extends Ghost {

        private String imagePath;

        public CyanGhost(My2DSyncArray charactersPosition, Tile[][] tiles){
            super(charactersPosition, tiles);
            imagePath = "src/images/ghosts/cyan.png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            //SVGIcon originalIcon = new SVGIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setImage(new ImageIcon(scaledImageDot).getImage());

            x=17;
            y=13;
            status=0;
        }

    @Override
    public void chase() {

    }

    @Override
    public void scatter(Tile[][] tiles) {

    }
}
