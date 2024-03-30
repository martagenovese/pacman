package tiles_classes;

import javax.swing.*;

public abstract class Tile extends JLabel {

    public Tile() {
        setOpaque(true);
    }
    public abstract boolean isWall();
    public abstract boolean isPacman();
    public abstract boolean isGhost();
    public abstract boolean isDot();
    public abstract boolean isSuperFood();
}
