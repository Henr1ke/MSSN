package TP1.CA;

import Processing.IProcessingApp;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class RunTestCA implements IProcessingApp {
    private CellularAutomata ca;

    public void setup(PApplet p) {
        int nRows = 25, nCols = 30, nStates = 4, mooreRadius = 2;
        ca = new CellularAutomata(p, nRows, nCols, nStates, mooreRadius);
        ca.initRandom();
        ca.display();
    }


    public void draw(PApplet p, float dt) {
        ca.display();
    }


    public void mousePressed(PApplet p) {
        Cell cell = ca.pixelToCell(p.mouseX, p.mouseY);
        cell.setState(0);

        Cell[] cells = cell.getNeighbors();
        for (Cell cell1 : cells)
            cell1.setState(1);
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
