package TP1.CA;

import processing.core.PApplet;

public class Cell {
    private final CellularAutomata ca;
    private final int row, col;
    private int state;
    private Cell[] neighbors;

    public Cell(CellularAutomata ca, int row, int col) {
        this.ca = ca;
        this.row = row;
        this.col = col;
        this.state = 0;
        this.neighbors = null;
    }

    public CellularAutomata getCa() {
        return ca;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Cell[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Cell[] neighbors) {
        this.neighbors = neighbors;
    }


    public void display() {
        int width = this.ca.getCellWidth();
        int height = this.ca.getCellHeight();
        PApplet p = ca.getP();
        p.fill(this.ca.getStateColors()[state]);
        p.rect(this.col * width, this.row * height, width, height);
    }
}
