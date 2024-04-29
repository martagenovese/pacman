package characters_classes;

import mcv.EventManager;
import myclasses.My2DSyncArray;
import javax.swing.*;
import java.awt.*;


public class Pacman extends ImageIcon {
    //TODO: (optimization) n Icons for each direction
    protected int x, y;
    private String imagePath;
    private My2DSyncArray charactersPosition;
    protected boolean isSuper;
    protected EventManager eventManager;

    public Pacman(My2DSyncArray charactersPosition) {
        imagePath = "src/images/pacman/right.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());
        this.charactersPosition=charactersPosition;
        x=13;
        y=26;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
    private void setDirection(String direction) {
        imagePath = "src/images/pacman/" + direction + ".png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImageDot).getImage());

    }
    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
        if (isSuper) {
            eventManager.muchoMachoPacman();
            imagePath = "src/images/pacman/super/" + getDirection() + ".png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image scaledImageDot = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            setImage(new ImageIcon(scaledImageDot).getImage());
        }
    }

    public boolean isSuper() {
        return isSuper;
    }
    public String getDirection() {
        return imagePath.substring(imagePath.lastIndexOf('/')+1, imagePath.lastIndexOf('.'));
    }
    public synchronized int getX() {
        return x;
    }
    public synchronized int getY() {
        return y;
    }

    public void move(String direction) {
        if (isSuper) setDirection("super/"+direction);
        else setDirection(direction);
        switch (direction) {
            case "left" : {
                if (x == 0) x = 27;
                else x-=1;
                break;
            }
            case "right" : {
                if (x == 27) x = 0;
                else x++;
                break;
            }
            case "up" : {
                y-=1;
                break;
            }
            case "down" : {
                y++;
                break;
            }
        }
        charactersPosition.setX(0, x);
        charactersPosition.setY(0, y);
    }
    public void eaten() {
        //se è stato mangiato torna alla posizione di partenza
        eventManager.getTable().clearPacman(x,y);
        x=13;
        y=26;
        charactersPosition.setX(0, x);
        charactersPosition.setY(0, y);
        setDirection("right");
        eventManager.getTable().updatePosition();
    }
}