package characters_classes;

import javax.swing.*;
import java.awt.*;

public class RedGhost extends Ghost {

    protected int x, y;
    private String imagePath;

    public RedGhost(){
        super();
            imagePath = "src/images/ghosts/red.png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setImage(new ImageIcon(scaledImageDot).getImage());
    }
}
