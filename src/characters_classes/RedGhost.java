package characters_classes;

import javax.swing.*;
import java.awt.*;

public class RedGhost extends Ghost {

    private String imagePath;

    public RedGhost(){
        super();
            imagePath = "src/images/ghosts/red.svg";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setImage(new ImageIcon(scaledImageDot).getImage());

            x=17;
            y=12;
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
