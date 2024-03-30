package tiles_classes;

import javax.swing.*;
import java.awt.*;

public class CrossableTile extends Tile {
    public int x, y;
    boolean isPacman, isGhost, isDot, isSuperFood;
    Icon dotIcon, superFoodIcon;

    public CrossableTile(int x, int y) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon originalIcon = new ImageIcon("src/images/dot.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImageDot = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        dotIcon = new ImageIcon(scaledImageDot);

        Image scaledImageSFood = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        superFoodIcon = new ImageIcon(scaledImageSFood);

        this.setBackground(Color.BLACK);
        this.x = x;
        this.y = y;
        this.isPacman = false;
        this.isGhost = false;
    }

    public void setPacman(boolean isPacman) {
        this.isPacman = isPacman;
    }
    public void setGhost(boolean isGhost) {
        this.isGhost = isGhost;
    }
    public void setDot(boolean isDot) {
        this.isDot = isDot;
        if (isDot) this.setIcon(dotIcon);
        else this.setIcon(null);
        this.repaint();
        this.revalidate();
    }
    public void setSuperFood(boolean isSuperFood) {
        this.isSuperFood = isSuperFood;
        if (isSuperFood) this.setIcon(superFoodIcon);
        else this.setIcon(null);
        this.repaint();
        this.revalidate();
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public boolean isPacman() {
        return isPacman;
    }

    @Override
    public boolean isGhost() {
        return isGhost;
    }

    @Override
    public boolean isDot() {
        return isDot;
    }

    @Override
    public boolean isSuperFood() {
        return isSuperFood;
    }
}
