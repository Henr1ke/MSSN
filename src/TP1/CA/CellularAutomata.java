package TP1.CA;

import processing.core.PApplet;

public class CellularAutomata {

    private PApplet P;
    private final int nRows, nCols, nStates, mooreRadius;
    private Cell[][] cells;
    private final int[] stateColors;
    private int cellWidth, cellHeight;

    public CellularAutomata(PApplet p, int nRows, int nCols, int nStates, int mooreRadius) {
        this.P = p;
        this.nRows = nRows;
        this.nCols = nCols;
        this.nStates = nStates;
        this.mooreRadius = mooreRadius;
        this.cells = new Cell[nRows][nCols];
        this.stateColors = new int[nStates];
        this.cellWidth = p.width / nCols;
        this.cellHeight = p.height / nRows;
        createCells(this.cells);
        setStateColors();
    }

    public PApplet getP() {
        return P;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void setStateColors() {
        for (int i = 0; i < nStates; i++)
            stateColors[i] = P.color(P.random(255), P.random(255), P.random(255));
    }

    public int[] getStateColors() {
        return stateColors;
    }

    public void initRandom() {
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                this.cells[i][j].setState((int) (Math.random() * nStates));
    }

    public Cell pixelToCell(int x, int y) {
        int col = Math.min(x / this.getCellWidth(), nCols - 1);
        int row = Math.min(y / this.getCellHeight(), nRows - 1);
        return this.cells[row][col];
    }

    public void display() {
        for (Cell[] cellRow : this.cells)
            for (Cell cell : cellRow)
                cell.display();
    }

    protected void createCells(Cell[][] cells) {
        int nRows = cells.length;
        int nCols = cells[0].length;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                cells[i][j] = new Cell(this, i, j);
        setMooreNeighbors(cells);
    }

    protected void setMooreNeighbors(Cell[][] cells) {
        int NN = (int) Math.pow(2 * mooreRadius + 1, 2);
        int nRows = cells.length;
        int nCols = cells[0].length;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++) {
                Cell[] neighbors = new Cell[NN];
                int n = 0;

                for (int ii = -mooreRadius; ii <= mooreRadius; ii++) {
                    int row = (nRows + i + ii) % nRows;

                    for (int jj = -mooreRadius; jj <= mooreRadius; jj++) {
                        int col = (nCols + j + jj) % nCols;
                        neighbors[n++] = cells[row][col];
                    }
                }
                cells[i][j].setNeighbors(neighbors);
            }
    }
}
