package characters_classes;

import tiles_classes.*;
import java.util.*;
import javax.swing.*;

public class Ghost extends ImageIcon {

    protected int x, y;

    public Ghost(){
    }

    public void frightened(){
    }

    public void getToTheTarget(Tile[][] tiles){
        int xTarget, yTarget;
        do{
        xTarget=(int)(Math.random()*36+1);
        yTarget=(int)(Math.random()*36+1);
        }while(tiles[xTarget][yTarget] instanceof WallTile);


        while(x!=xTarget && y!=yTarget){

            Dictionary<String, Boolean> directionAvailable = new Hashtable<>();
            directionAvailable.put("Up", false);            boolean[] directionAvailable = [false, false, false, false]



            */

            int l;
        }
    }
}
