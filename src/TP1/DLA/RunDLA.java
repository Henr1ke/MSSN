package TP1.DLA;

import Processing.IProcessingApp;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;


public class RunDLA implements IProcessingApp {

    protected DLA dla;
    protected final int N_UPDATES_P_FRAME = 100;
    protected boolean running = false;

    public void setup(PApplet p) {
        dla = new DLA(p);
    }

    public void draw(PApplet p, float dt) {
        if (!dla.limitReached() && running)
            update();
        dla.updateDisplay();
    }

    public void mousePressed(PApplet p) {
        if (!dla.limitReached()) {
            dla.newWalker(new Walker(dla, Walker.State.WANDERING, new PVector(p.mouseX, p.mouseY)));
        }
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {
        if (!dla.limitReached()) {
            if (p.key == ' ')
                running = !running;
            if (p.key == 'n')
                update();
        }
    }

    private void update() {
        for (int i = 0; i < N_UPDATES_P_FRAME; i++)
            if (!dla.limitReached())
                dla.updateWanderers();
        System.out.println("Walkers: " + dla.getWalkers().size() + "; Stopped: " + dla.getStopped().size() + "; Wandering: " + dla.getWandering().size());
    }
}
