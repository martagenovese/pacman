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
}
