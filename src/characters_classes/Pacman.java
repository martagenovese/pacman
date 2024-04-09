package characters_classes;

import javax.swing.*;
import java.awt.*;

public class Pacman extends ImageIcon {
    protected int x, y;
    private String imagePath;

    public Pacman() {
        imagePath = "src/images/pacman/right.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

        x=26;
        y=13;
    }
    private void setDirection(String direction) {
        imagePath = "src/images/pacman/" + direction + ".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
    }
    public void move(String direction) {
        setDirection(direction);
        if (direction.equals("left")) {
            if (y == 0) y = 27;
            else y--;
        } else if (direction.equals("right")) {
            if (y == 27) y = 0;
            else y++;
        } else if (direction.equals("up")) {
            x--;
        } else if (direction.equals("down")) {
            x++;
        }
    }

    public String getDirection() {
        return imagePath.substring(imagePath.length() - 9, imagePath.length() - 4);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}