package characters_classes;

import myclasses.My2DSyncArray;
import myclasses.SVGIcon;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Pacman extends ImageIcon {
    //TODO: controlla costruttore
    protected int x, y;
    private String imagePath;
    private My2DSyncArray charactersPosition;
    protected boolean isSuper;

    public Pacman(My2DSyncArray charactersPosition) {
        imagePath = "src/images/pacman/right.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        //SVGIcon originalIcon = new SVGIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
        this.charactersPosition=charactersPosition;
        x=13;
        y=26;
    }
    public boolean isSuper() {
        return isSuper;
    }
    private void setDirection(String direction) {
        imagePath = "src/images/pacman/" + direction + ".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

    }
    public void move(String direction) {
        setDirection(direction);
        switch (direction) {
            case "left" : {
                if (x == 0) x = 27;
                else x-=1;
                charactersPosition.set(0,0, 1);
                break;
            }
            case "right" : {
                if (x == 27) x = 0;
                else x++;
                charactersPosition.set(0,0, 3);
                break;
            }
            case "up" : {
                y-=1;
                charactersPosition.set(0,0, 0);
                break;
            }
            case "down" : {
                y++;
                charactersPosition.set(0,0, 2);
                break;
            }
        }
        charactersPosition.set(0,0, x);
        charactersPosition.set(0,0, y);
    }

    public String getDirection() {
        return imagePath.substring(imagePath.lastIndexOf('/'), imagePath.lastIndexOf('.'));
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

    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
        System.out.println("Pacman is super: "+isSuper);
        if (isSuper) {
            imagePath = "src/images/pacman/super/"+getDirection()+".png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            setImage(new ImageIcon(scaledImageDot).getImage());
            new Thread(() -> {
                try {
                    sleep(7000);
                    this.isSuper = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}