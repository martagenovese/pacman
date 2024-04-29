package myclasses;

import java.awt.*;

public class My2DSyncArray {
    private Point[] array;



    public My2DSyncArray(int rows) {
        array = new Point[rows];
        for (int i = 0; i < rows; i++) {
            array[i] = new Point();
        }
    }

    /*public synchronized void set(int row, int col) {
        array[row][col] = value;
    } */
    public synchronized void setX(int row, int value) {
        array[row].x = value;
    }
    public synchronized void setY(int row, int value) {
        array[row].y = value;
    }

    /* public synchronized int get(int row, int col) {
        return array[row][col];
    } */
    public synchronized int getX(int row) {
        return array[row].x;
    }
    public synchronized int getY(int row) {
        return array[row].y;
    }


    //prettify the output of the print function
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : array) {
            sb.append("[").append(point).append("]\n");
        }
        return sb.toString();
    }
}
