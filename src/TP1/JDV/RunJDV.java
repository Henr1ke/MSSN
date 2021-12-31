package TP1.JDV;

import Processing.IProcessingApp;
import TP1.CA.Cell;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class RunJDV implements IProcessingApp {
    protected JogoDaVida jv;
    protected boolean running = false;
    protected float aliveCellsPercentage = 0.25f;
    protected int nCols = 100;
    protected int nRows = 75;

    public void setup(PApplet p) {
//        jv = new JogoDaVida(p, nRows, nCols, aliveCellsPercentage);
        jv = new JDV23por2(p, nRows, nCols, aliveCellsPercentage);

        jv.initRandom();
        jv.display();
    }

    public void draw(PApplet p, float dt) {
        if (running)
            jv.update();
        jv.display();
    }

    public void mousePressed(PApplet p) {
        Cell cell = jv.pixelToCell(p.pmouseX, p.mouseY);
        cell.setState(cell.getState() == CellJDV.DEAD ? CellJDV.ALIVE : CellJDV.DEAD);
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {
        if (p.key == ' ')
            running = !running;
        if (p.key == 'n')
            jv.update();
    }
}
