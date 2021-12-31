package TP2.AgentesAutonomos;

import Processing.IProcessingApp;
import TP2.AgentesAutonomos.Behaviours.Flee;
import TP2.AgentesAutonomos.Behaviours.Seek;
import TP2.AgentesAutonomos.Behaviours.Wander;
import TP2.Physics.Body;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class BoidApp implements IProcessingApp {
    private Boid b;
    private final double[] window = {-10, 10, -10, 10};
    private final float[] viewport = {0, 0, 1, 1};
    private SubPlot1 plt;
    private final float[] velocity = {4, 4};
    private Body target;
    private final int index = 2;

    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        b = new Boid(new PVector(), 1, 0.5f, p.color(0), p, plt);
        b.addBehaviour(new Seek(1f));
        b.addBehaviour(new Flee(1f));
        b.addBehaviour(new Wander(1f));
        target = new Body(new PVector(), new PVector(), 1F, 0.2F, p.color(255, 0, 0));
        List<Body> allTrackingBodies = new ArrayList<>();
        allTrackingBodies.add(target);
        Eye eye = new Eye(b, allTrackingBodies);
        b.setEye(eye);
    }

    public void draw(PApplet p, float dt) {
        p.background(255);
        b.applyBehaviour(index, dt);
        b.display(p, plt);
    }

    public void mousePressed(PApplet p) {
        double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
        target.setPos(new PVector((float) ww[0], (float) ww[1]));
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
