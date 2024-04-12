package tiles_classes;

import javax.swing.*;

public abstract class Tile {
    public abstract boolean isWall();
    public abstract boolean isPacman();
    public abstract boolean isGhost();
    public abstract boolean isDot();
    public abstract boolean isSuperFood();
    public void setPacman(boolean b) {
    }
    public void setIntersection(boolean b) {
    }
}
