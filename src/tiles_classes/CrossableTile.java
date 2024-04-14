package tiles_classes;

import characters_classes.Pacman;

import javax.swing.*;
import java.awt.*;

public class CrossableTile extends Tile {
    public int x, y;
    boolean isPacman, isGhost, isDot, isSuperFood, isIntersection;
    Icon dotIcon, superFoodIcon;

    public CrossableTile(int x, int y) {
        this.x = x;
        this.y = y;
        this.isPacman = false;
        this.isGhost = false;
        this.isDot = false;
        this.isSuperFood = false;
        this.isIntersection = false;
    }

    public void setPacman(boolean isPacman) {
        this.isPacman = isPacman;
    }
    public void setIntersection(boolean isIntersection) {
        this.isIntersection = isIntersection;
    }
    public void setGhost(boolean isGhost) {
        this.isGhost = isGhost;
    }
    public void setDot(boolean isDot) {
        this.isDot = isDot;
    }
    public void setSuperFood(boolean isSuperFood) {
        this.isSuperFood = isSuperFood;
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

    public boolean isIntersection() {
        return isIntersection;
    }
}
