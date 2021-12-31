package TP1.JDV;

import TP1.CA.Cell;
import processing.core.PApplet;

public class JDV23por2 extends JogoDaVida {
    public JDV23por2(PApplet p, int nRows, int nCols, float aliveCellsPercentage) {
        super(p, nRows, nCols, aliveCellsPercentage);
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
                if (aliveNeigh == 2) { // A Cell nasce
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
