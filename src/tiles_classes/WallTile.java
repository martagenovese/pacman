package tiles_classes;

import java.awt.*;

public class WallTile extends Tile {
    public WallTile() {
        this.setBackground(Color.BLUE);
    }

    @Override
    public boolean isWall() {
        return true;
    }

    @Override
    public boolean isPacman() {
        return false;
    }

    @Override
    public boolean isGhost() {
        return false;
    }

    @Override
    public boolean isDot() {
        return false;
    }

    @Override
    public boolean isSuperFood() {
        return false;
    }
}
