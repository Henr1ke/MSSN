package TP2.Physics;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class SolarSystemApp implements IProcessingApp {
    private final float[] viewport = {0, 0, 1, 1};
    private final double[] window = {-500e9f, 500e9f, -500e9f, 500e9f};

    private SubPlot1 plt;
    private Body sun, mercury, venus, earth, mars, jupiter, saturn, uranus, neptune;
    private List<Body> planets;

    private float speedUp = 60 * 60 * 24 * 30;


    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        planets = new ArrayList<>();

        sun = new Body(new PVector(), new PVector(), 1.989e30f, 10 * 696340e3f, p.color(255, 128, 0));
        mercury = new Body(new PVector(61.036e9f, 0), new PVector(0, -47.9e3f), 3.285e23f, 1000 * 2439.7e3f, p.color(151, 151, 159));
        venus = new Body(new PVector(107.73e9f, 0), new PVector(0, -35.0e3f), 4.867e24f, 1000 * 6051.8e3f, p.color(139, 125, 130));
        earth = new Body(new PVector(147.14e9f, 0), new PVector(0, -29.8e3f), 5.972e24f, 1000 * 6371e3f, p.color(46, 58, 146));
        mars = new Body(new PVector(232.75e9f, 0), new PVector(0, -24.1e3f), 6.39e23f, 1000 * 3389.5e3f, p.color(188, 39, 50));
        jupiter = new Body(new PVector(747.5e9f, 0), new PVector(0, -13.1e3f), 1.898e27f, 1000 * 69911e3f, p.color(216, 202, 157));
        saturn = new Body(new PVector(1.4818e12f, 0), new PVector(0, -9.7e3f), 5.683e26f, 1000 * 58232e3f, p.color(234, 214, 184));
        uranus = new Body(new PVector(2.95e12f, 0), new PVector(0, -6.8e3f), 8.681e25f, 1000 * 25362e3f, p.color(79, 208, 231));
        neptune = new Body(new PVector(4.4748e12f, 0), new PVector(0, -5.4e3f), 1.024e26f, 1000 * 24622e3f, p.color(91, 93, 223));

        planets.add(mercury);
        planets.add(venus);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(saturn);
        planets.add(uranus);
        planets.add(neptune);
    }

    public void draw(PApplet p, float dt) {
        float[] pp = plt.getBoundingBox();
        p.fill(25, 29, 30, 128);
        p.rect(pp[0], pp[1], pp[2], pp[3]);

        applyAttractionForces();
        sun.display(p, plt);
        for (Body planet : planets) {
            planet.move(dt * speedUp);
            planet.display(p, plt);
        }
    }

    public void mousePressed(PApplet p) {
        if (p.mouseButton == p.LEFT)
            plt.setOriginalDragPos(new PVector(p.mouseX, p.mouseY));
    }

    public void mouseDragged(PApplet p) {
        if (p.mouseButton == p.RIGHT)
            plt.drag(new PVector(p.mouseX, p.mouseY));
    }

    public void mouseWheel(PApplet p, MouseEvent e) {
        plt.zoom(-0.1f * e.getCount());
    }

    public void keyPressed(PApplet p) {
        if (p.key == '+')
            speedUp *= 2;
        if (p.key == '-')
            speedUp /= 2;
    }

    private void applyAttractionForces() {
        for (Body planet : planets)
            planet.applyConstantForce(sun.getAttractionForce(planet));
//        for (Body body1 : planets)
//            for (Body body2 : planets)
//                if (body1 != body2)
//                    body1.applyForce(body2.attraction(body1));

    }
}
