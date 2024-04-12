package game;

import characters_classes.Pacman;
import tiles_classes.CrossableTile;
import tiles_classes.Tile;
import tiles_classes.WallTile;

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
    }
    public void setModel(Model model) {
        this.model = model;
    }
    public void setTable(Table table) {
        this.table = table;

        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                if (model.tiles[i][j] instanceof WallTile) {
                    table.tiles[i][j].setBackground(Color.BLUE);
                } else if (model.tiles[i][j] instanceof CrossableTile tile) {
                    if (tile.isDot()) {
                        table.setDot(i, j);
                    } else if (tile.isSuperFood()) {
                        table.setSuperFood(i, j);
                    }
                    table.tiles[i][j].setBackground(Color.BLACK);
                }
            }
        }

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

            int key = e.getKeyCode();
            String s;

            if (key == KeyEvent.VK_LEFT) {
                s = "Left";
            } else if (key == KeyEvent.VK_RIGHT) {
                s = "Right";
            } else if (key == KeyEvent.VK_UP) {
                s = "Up";
            } else if (key == KeyEvent.VK_DOWN) {
                s = "Down";
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
                String s2 = s.toLowerCase();
                table.clearPacman(model.getPacman().getX(), model.getPacman().getY());
                model.movePacman(s2, (Tile) method.invoke(model), model.getMyTile());
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }

            model.updatePosition();
            table.updatePosition();
            //table.repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
