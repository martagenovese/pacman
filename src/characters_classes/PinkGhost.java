package characters_classes;

import tiles_classes.Tile;

import javax.swing.*;
import java.awt.*;

public class PinkGhost extends Ghost {

        private String imagePath;

        public PinkGhost(){
            super();
                imagePath = "src/images/ghosts/pink.svg";
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();
                Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                setImage(new ImageIcon(scaledImageDot).getImage());

                x=17;
                y=14;
        }

    @Override
    public void chase() {

    }

    @Override
    public void scatter(Tile[][] tiles) {

    }

}
