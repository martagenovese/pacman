import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Pacman extends ImageIcon {
    int x, y;
    public Pacman() {
        ImageIcon originalIcon = new ImageIcon("src/images/pacman/right.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

        x=26;
        y=13;
    }
    private void setDirection(String direction) {
        ImageIcon originalIcon = new ImageIcon("src/images/pacman/" + direction + ".png");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
    }
    public void move(String direction) {
        setDirection(direction);
        if (direction.equals("left")) {
            y--;
        } else if (direction.equals("right")) {
            y++;
        } else if (direction.equals("up")) {
            x--;
        } else if (direction.equals("down")) {
            x++;
        }
        //System.out.println(x + " " + y);
    }

    public String getDirection() {
        String path = getDescription();
        return path.substring(path.length() - 9, path.length() - 4);
    }

}
