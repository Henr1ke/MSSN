package TP1.JDV;

import TP1.CA.Cell;
import TP1.CA.CellularAutomata;
import processing.core.PApplet;

import java.util.*;
import java.util.stream.Collectors;

public class CellJDV extends Cell {
    public static final int DEAD = 0;
    public static final int ALIVE = 1;
    protected static int deadColor; //Cor da Cell quando está no estado DEAD
    protected int aliveColor; //Cor da Cell quando está no estado ALIVE

    public CellJDV(CellularAutomata ca, int row, int col) {
        super(ca, row, col);
        PApplet p = ca.getP();
        this.aliveColor = p.color(p.random(255), p.random(255), p.random(255));
    }

    public static void setDeadColor(int deadColor) {
        CellJDV.deadColor = deadColor;
    }

    public int getAliveColor(){
        return this.aliveColor;
    }

    public void setAliveColor(int aliveColor) {
        this.aliveColor = aliveColor;
    }

    /**
     * Retorna uma lista com todas as Cell vizinhas no estado ALIVE, excluindo a propria Cell dessa lista
     */
    public CellJDV[] getAliveNeigh() {
        CellJDV[] aliveNeighbors = new CellJDV[]{};
        Cell[] neighbors = this.getNeighbors();
        for (int i = 0; i < neighbors.length; i++)
            if (i != neighbors.length / 2 && neighbors[i].getState() == CellJDV.ALIVE) {
                aliveNeighbors = Arrays.copyOf(aliveNeighbors, aliveNeighbors.length + 1);
                aliveNeighbors[aliveNeighbors.length - 1] = (CellJDV) neighbors[i];
            }
        return aliveNeighbors;
    }

    /**
     * Retorna a cor mais comum entre as cores de todos os vizinhos vivos
     */
    public int getCommonNeighborColor() {
        List<CellJDV> aliveNeighbors = new ArrayList<>(List.of(this.getAliveNeigh()));
        List<Integer> colorsList = aliveNeighbors.stream().map(CellJDV::getAliveColor).collect(Collectors.toList());
        int count = 0;
        int color = aliveColor;
        for (CellJDV neighbor : aliveNeighbors) {
            int newCount = Collections.frequency(colorsList, neighbor.aliveColor);
            if (newCount > count) {
                color = neighbor.aliveColor;
                count = newCount;
            }
        }
        return color;
    }

    public void display() {
        CellularAutomata ca = this.getCa();
        int width = ca.getCellWidth();
        int height = ca.getCellHeight();
        PApplet p = ca.getP();
        if (this.getState() == DEAD)
            p.fill(CellJDV.deadColor);
        else
            p.fill(this.aliveColor);
        p.rect(this.getCol() * width, this.getRow() * height, width, height);
    }
}
