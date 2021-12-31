package TP2.Physics;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystemApp implements IProcessingApp {

    private List<ParticleSystem> pss;
    private final double[] window = {-10, 10, -10, 10};
    private final float[] viewport = {0, 0, 1, 1};
    private final float[] angle = {PApplet.radians(90), PApplet.radians(50)};
    private final float[] velocity = {1, 7};
    private final float[] lifetime = {3, 5};
    private final float[] radius = {0.05f, 2f};
    private final float flow = 50;
    private SubPlot1 plt;

    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        pss = new ArrayList<>();
    }

    public void draw(PApplet p, float dt) {
        p.background(255);
        for (ParticleSystem ps : pss) {
//            ps.applyForce(new PVector(0, -1));
            ps.move(dt);
            ps.display(p, plt);
        }

        angle[0] = PApplet.map(p.mouseX, 0, p.width, PApplet.radians(0), PApplet.radians(360));
        for (ParticleSystem ps : pss) {
            PSControl psc = ps.getPSControl();
            psc.setAngle(angle);
        }
    }

    public void mousePressed(PApplet p) {
        double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
        int color = p.color(p.random(255), p.random(255), p.random(255));
        PSControl psc = new PSControl(angle, velocity, lifetime, radius, flow, color);
        pss.add(new ParticleSystem(new PVector((float) ww[0], (float) ww[1]), new PVector(), 1f, .2f, psc));
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
