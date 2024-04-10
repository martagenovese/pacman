package game;

import characters_classes.Pacman;
import tiles_classes.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class EventManager implements KeyListener {
    My2DSyncArray charactersPosition;
    private Table table;
    private Model model;
    private boolean isListenerActive;
    protected String nextDirection, lastDirection;

    public EventManager() {
        isListenerActive = true;
    }
    public void setModelandTable(Model model, Table table) {
        this.model = model;
        this.table = table;
        table.setCharacter(model.getPacman());
        model.setTiles(table.getTiles());
        table.setCharacter(model.getPacman());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void disableListenerFor(int milliseconds) {
        isListenerActive = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isListenerActive = true;
            }
        }, milliseconds);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (isListenerActive) {
            disableListenerFor(200);
            System.out.println("key pressed maybe");

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                nextDirection = model.movePacman("left", model.getLeftTile(), model.getMyTile());
                if (lastDirection==null) return;
                try {
                    Method m = this.getClass().getDeclaredMethod("movePacman", String.class, Tile.class, Tile.class);
                    m.invoke(this, nextDirection, model.getMyTile(), model.getMyTile());
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (key == KeyEvent.VK_RIGHT) {
                model.movePacman("right", model.getRightTile(), model.getMyTile());
            } else if (key == KeyEvent.VK_UP) {
                model.movePacman("up", model.getUpTile(), model.getMyTile());
            } else if (key == KeyEvent.VK_DOWN) {
                model.movePacman("down", model.getDownTile(), model.getMyTile());
            } else {
                return;
            }

            model.updatePosition();
            table.updatePosition();
            table.repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
