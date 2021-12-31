package TP2.AgentesAutonomos;

import Processing.IProcessingApp;
import TP2.AgentesAutonomos.Behaviours.Flee;
import TP2.AgentesAutonomos.Behaviours.Pursuit;
import TP2.AgentesAutonomos.Behaviours.Seek;
import TP2.AgentesAutonomos.Behaviours.Wander;
import TP2.Physics.Body;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ReynoldsTestApp implements IProcessingApp {
    private Boid wander, seeker, pursuiter, boid;
    private Flock flock;
    private final float[] sacWeights = {0.5f, 2f, 5f};
    private final double[] window = {-10, 10, -10, 10};
    private final float[] view1 = {0.02f, 0.51f, 0.96f, 0.47f};
    private final float[] view2 = {0.02f, 0.02f, 0.47f, 0.47f};
    private final float[] view3 = {0.51f, 0.02f, 0.47f, 0.47f};
    private SubPlot1 plt1, plt2, plt3;
    private Body target;
    private int ix = 0;

    public void setup(PApplet p) {
        plt1 = new SubPlot1(window, view1, p.width, p.height);
        plt2 = new SubPlot1(window, view2, p.width, p.height);
        plt3 = new SubPlot1(window, view3, p.width, p.height);

        flock = new Flock(200, .1f, 0.3f, p.color(0, 100, 200), sacWeights, p, plt1);
        boid = flock.getBoid(4);

        wander = new Boid(new PVector(p.random((float) window[0], (float) window[1]), p.random((float) window[2], (float) window[3])), 0.5f, 0.5f, p.color(255, 0, 0), p, plt2);
        wander.addBehaviour(new Wander(1f));

        pursuiter = new Boid(new PVector(p.random((float) window[0], (float) window[1]), p.random((float) window[2], (float) window[3])), 0.5f, 0.5f, p.color(0, 255, 0), p, plt2);
        pursuiter.addBehaviour(new Pursuit(1f));
        List<Body> allTrackingBodies = new ArrayList<>();
        allTrackingBodies.add(wander);
        pursuiter.setEye(new Eye(pursuiter, allTrackingBodies));

        target = new Body(new PVector(), new PVector(), 1f, .3f, p.color(0));
        seeker = new Boid(new PVector(p.random((float) window[0], (float) window[1]), p.random((float) window[2], (float) window[3])), 0.5f, 0.5f, p.color(0, 255, 255), p, plt3);
        seeker.addBehaviour(new Seek(3f));
//        seeker.addBehaviour(new Wander(1f));
        seeker.addBehaviour(new Flee(1f));
        allTrackingBodies = new ArrayList<>();
        allTrackingBodies.add(target);
        seeker.setEye(new Eye(seeker, allTrackingBodies));
    }

    public void draw(PApplet p, float dt) {
        p.background(255);
        float[] bb = plt1.getBoundingBox();
//        p.fill(255, 128);
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        flock.applyBehabiour(dt);
        flock.display(p, plt1);
        boid.getEye().display(p, plt1);

        bb = plt2.getBoundingBox();
//        p.fill(255, 128);
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        wander.applyBehaviours(dt);
        wander.display(p, plt2);
        pursuiter.applyBehaviours(dt);
        pursuiter.display(p, plt2);

        bb = plt3.getBoundingBox();
//        p.fill(255, 128);
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        seeker.applyBehaviour(ix, dt);
        seeker.display(p, plt3);
        target.display(p, plt3);
    }

    public void mousePressed(PApplet p) {
        if (plt3.isInside(p.mouseX, p.mouseY)) {
            double[] w = plt3.getWorldCoord(p.mouseX, p.mouseY);
            target.setPos(new PVector((float) w[0], (float) w[1]));
        }
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {
        if (p.key == 't') ix = (ix + 1) % 2;
    }
}
