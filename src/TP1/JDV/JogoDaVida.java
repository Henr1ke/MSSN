package TP1.JDV;

import TP1.CA.Cell;
import TP1.CA.CellularAutomata;
import processing.core.PApplet;

public class JogoDaVida extends CellularAutomata {
    private final float aliveCellsPercentage;

    public JogoDaVida(PApplet p, int nRows, int nCols, float aliveCellsPercentage) {
        super(p, nRows, nCols, 2, 1);
        this.aliveCellsPercentage = aliveCellsPercentage;
        CellJDV.setDeadColor(p.color(p.random(255), p.random(255), p.random(255)));
    }

    /**
     * Método de CellularAutomata() adaptado pra criar CellJDV()
     */
    protected void createCells(Cell[][] cells) {
        int nRows = cells.length;
        int nCols = cells[0].length;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                cells[i][j] = new CellJDV(this, i, j);
        setMooreNeighbors(cells);
    }

    public void initRandom() {
        PApplet p = this.getP();
        int nRows = this.getnRows();
        int nCols = this.getnCols();
        Cell[][] cells = this.getCells();
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                if (p.random(1) <= aliveCellsPercentage)
                    cells[i][j].setState(CellJDV.ALIVE);
    }

    public void update() {
        int nRows = this.getnRows();
        int nCols = this.getnCols();
        Cell[][] cells = this.getCells();
        CellJDV[][] cellsUpdated = new CellJDV[nRows][nCols];
        createCells(cellsUpdated);
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++) {
                CellJDV cell = (CellJDV) cells[i][j];
                int color = cell.getCommonNeighborColor();
                int aliveNeigh = cell.getAliveNeigh().length;
                if (aliveNeigh == 3) { // A Cell nasce
                    cellsUpdated[i][j].setState(CellJDV.ALIVE);
                    cellsUpdated[i][j].setAliveColor(color);
                } else if (aliveNeigh < 2 || aliveNeigh > 3) // A Cell morre
                    cellsUpdated[i][j].setState(CellJDV.DEAD);
                else { // A Cell mantém o estado anterior
                    cellsUpdated[i][j].setState(cell.getState());
                    cellsUpdated[i][j].setAliveColor(color);
                }
            }
        this.setCells(cellsUpdated);
    }
}
