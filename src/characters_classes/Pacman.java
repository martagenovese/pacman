package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Pacman extends ImageIcon {
    //TODO: controlla costruttore
    int x, y;
    private String imagePath;
    private My2DSyncArray charactersPosition;

    public Pacman(My2DSyncArray charactersPosition) {
        imagePath = "src/images/pacman/right.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
        this.charactersPosition=charactersPosition;
        x=26;
        y=13;
    }
    private void setDirection(String direction) {
        imagePath = "src/images/pacman/" + direction + ".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

    }
    public void move(String direction) {
        setDirection(direction);
        switch (direction) {
            case "left" -> {
                if (y == 0) y = 27;
                else y--;
            }
            case "right" -> {
                if (y == 27) y = 0;
                else y++;
            }
            case "up" -> x--;
            case "down" -> x++;
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
    private int directionStrToInt(String direction){
        switch (direction) {
            case "up":
                return 0;
            case "left":
                return 1;
            case "down":
                return 2;
            case "right":
                return 3;
        }
        return 0;
    }
}