package myclasses;

public class My2DSyncArray {
    private int[][] array;



    public My2DSyncArray(int rows, int cols) {
        array = new int[rows][cols];
    }

    public synchronized void set(int row, int col, int value) {
        array[row][col] = value;
    }

    public synchronized int get(int row, int col) {
        return array[row][col];
    }


    //prettify the output of the print function
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            switch (i) {
                case 0:
                    sb.append("Pacman: ");
                    break;
                case 1:
                    sb.append("RedGhost: ");
                    break;
                case 2:
                    sb.append("CyanGhost: ");
                    break;
                case 3:
                    sb.append("OrangeGhost: ");
                    break;
                case 4:
                    sb.append("PinkGhost: ");
                    break;
            }
            sb.append("[");
            for (int j = 0; j < array[i].length; j++) {
                sb.append(array[i][j]);
                if (j < array[i].length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
