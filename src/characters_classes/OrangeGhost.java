package characters_classes;

import javax.swing.*;
import java.awt.*;

public class OrangeGhost extends Ghost {

        private String imagePath;

        public OrangeGhost(){
            super();
                imagePath = "src/images/ghosts/orange.svg";
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();
                Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                setImage(new ImageIcon(scaledImageDot).getImage());

                x=17;
                y=15;
        }

}
