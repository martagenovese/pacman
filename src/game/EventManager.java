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
            String s;

            if (key == KeyEvent.VK_LEFT) {
                s = "Left";
            } else if (key == KeyEvent.VK_RIGHT) {
                s = "Right";
                model.movePacman("right", model.getRightTile(), model.getMyTile());
            } else if (key == KeyEvent.VK_UP) {
                s = "Up";
                model.movePacman("up", model.getUpTile(), model.getMyTile());
            } else if (key == KeyEvent.VK_DOWN) {
                s = "Down";
                model.movePacman("down", model.getDownTile(), model.getMyTile());
            } else {
                return;
            }

            Method method;
            try {
                method = model.getClass().getMethod("get"+s+"Tile");
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
            try {
                String s2 = s.charAt(0)+"";
                s2 = s2.toLowerCase();
                model.movePacman(s2, (Tile) method.invoke(model), model.getMyTile());
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
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
