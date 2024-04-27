package mcv;

import characters_classes.Ghost;
import characters_classes.Pacman;
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
        startGhost = false;
    }
    public void setModel(Model model) {
        this.model = model;
        model.getRedGhost().setEventManager(this);
        model.getCyanGhost().setEventManager(this);
        model.getPinkGhost().setEventManager(this);
        model.getOrangeGhost().setEventManager(this);

        model.pacman.setEventManager(this);

        charactersPosition = model.charactersPosition;
        model.sThread.start();

        model.supervisor.setEventManager(this);
    }

    public void setTable(Table table) {
        this.table = table;

        // build table
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 28; j++) {
                if (model.tiles[i][j] instanceof WallTile) {
                    if (i<3) table.tiles[i][j].setBackground(Color.BLACK);
                    else if (( (i>12 && i<16) || (i>18 && i<22) ) && (j<5 || j>22)) table.tiles[i][j].setBackground(Color.BLACK);
                    else if (i>33) table.tiles[i][j].setBackground(Color.BLACK);
                    else if (i == 15 && (j == 13 || j == 14)) table.tiles[i][j].setBackground(Color.BLACK);
                    else if ((i>=16 && i<=18) && (j>=11 && j<=16)) table.tiles[i][j].setBackground(Color.BLACK);
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
        table.setCyanGhost(model.getCyanGhost());
        table.setPinkGhost(model.getPinkGhost());
        table.setOrangeGhost(model.getOrangeGhost());
    }
    public Table getTable() {
        return table;
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
        boolean isDot = model.tiles[ghost.getY()][ghost.getX()].isDot();
        boolean isSuperFood = model.tiles[ghost.getY()][ghost.getX()].isSuperFood();
        table.clearGhost(ghost.getX(), ghost.getY(), isDot, isSuperFood);
    }
    public void updateGhostPosition(Ghost ghost) {
        table.updateGhost(ghost);
    }

    public void sevenSecondsInHeaven() {
        model.getRedGhost().setScared(true);
        model.getCyanGhost().setScared(true);
        model.getPinkGhost().setScared(true);
        model.getOrangeGhost().setScared(true);
        model.ghostsEaten = 0;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                model.pacman.setSuper(false);
                model.getRedGhost().setScared(false);
                model.getCyanGhost().setScared(false);
                model.getPinkGhost().setScared(false);
                model.getOrangeGhost().setScared(false);
            }
        }, 7000);
    }

    public void stopGame(boolean victory) {
        if (victory) {
            table.showVictory();
        } else {
            table.showDefeat();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!startGhost) {
            model.startRedGhost();
            model.startCyanGhost();
            model.startPinkGhost();
            model.startOrangeGhost();
            model.startGhostSupervisor();
            startGhost = true;
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

            table.updateScore(model.getScore());
            model.updatePosition();
            table.updatePosition();

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
