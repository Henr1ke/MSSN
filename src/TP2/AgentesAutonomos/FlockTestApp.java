package TP2.AgentesAutonomos;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class FlockTestApp implements IProcessingApp {
    private Flock flock;
    private final float[] sacWeights = {0.5f, 2f, 5f};
    private final double[] window = {-10, 10, -10, 10};
    private final float[] viewport = {0, 0, 1, 1};
    private SubPlot1 plt;

    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        flock = new Flock(200, .1f, 0.3f, p.color(0,100,200), sacWeights, p, plt);
    }

    public void draw(PApplet p, float dt) {
//        p.background(255);
        float[]bb = plt.getBoundingBox();
        p.fill(255,128);
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        flock.applyBehabiour(dt);
        flock.display(p, plt);
    }

    public void mousePressed(PApplet p) {

    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
