import characters_classes.Pacman;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class EventManager implements KeyListener {
    My2DSyncArray charactersPosition;
    Table table;
    private Model model;
    public EventManager() {}
    public void setModel(Model model) {
        this.model = model;
        model.setTiles(table.getTiles());
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public Pacman getCharacter() {
        return model.getCharacter();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            model.movePacman("left", model.getLeftTile(), model.getMyTile());
        } else if (key == KeyEvent.VK_RIGHT) {
            model.movePacman("right", model.getRightTile(), model.getMyTile());
        } else if (key == KeyEvent.VK_UP) {
            model.movePacman("up", model.getUpTile(), model.getMyTile());
        } else if (key == KeyEvent.VK_DOWN) {
            model.movePacman("down", model.getDownTile(), model.getMyTile());
        }
        model.updatePosition();
        table.repaint();

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
