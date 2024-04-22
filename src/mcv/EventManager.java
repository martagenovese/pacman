package mcv;

import characters_classes.Ghost;
import myclasses.My2DSyncArray;
import tiles_classes.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class EventManager implements KeyListener {
    My2DSyncArray charactersPosition;
    protected Table table;
    protected Model model;
    protected boolean isListenerActive;
    protected String nextDirection, lastDirection;
    protected boolean startGhost;

    public EventManager() {
        isListenerActive = true;
        startGhost=false;
    }
    public void setModel(Model model) {
        this.model = model;
        model.getRedGhost().setEventManager(this);
    }

    public void setTable(Table table) {
        this.table = table;

        // build table
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                if (model.tiles[i][j] instanceof WallTile) {
                    if (i == 15 && (j == 13 || j == 14)) table.tiles[i][j].setBackground(Color.BLACK);
                    else table.tiles[i][j].setBackground(Color.BLUE);
                } else if (model.tiles[i][j] instanceof CrossableTile) {
                    CrossableTile tile = (CrossableTile) model.tiles[i][j];
                    if (tile.isDot()) {
                        table.setDot(i, j);
                    } else if (tile.isSuperFood()) {
                        table.setSuperFood(i, j);
                    }
                    table.tiles[i][j].setBackground(Color.BLACK);
                }
            }
        }

        table.setScoreBar();
        table.setLives();
        table.setFruit();

        //table.tiles[17][12].setBackground(Color.RED);

        table.setCharacter(model.getPacman());
        table.setRedGhost(model.getRedGhost());
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
    public void clearGhostPosition(Ghost ghost) {
        table.clearGhost(ghost.getX(), ghost.getY());
    }
    public void updateGhostPosition(Ghost ghost) {
        table.updateGhost(ghost);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(startGhost==false){
            startGhost=true;
            model.startRedGhost();
        }
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


            table.clearPacman(model.getPacman().getX(), model.getPacman().getY());
            try {
                model.keepDirection(s);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            //table.clearPacman(model.getPacman().getX(), model.getPacman().getY());
            table.updateScore(model.getScore());
            //model.movePacman(lastDirection, (Tile) method.invoke(model), model.getMyTile());


            model.updatePosition();
            table.updatePosition();
            //table.repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}