package TP2.Physics;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class FallingBodyApp implements IProcessingApp {

    private static final float dimY = 15;
    private static final float dimX = 20;
    private static final float g = -9.8f;
    private static final float mass = 80;
    private static final float radius = 0.5f;
    private final double[] window = {0, dimX, 0, dimY};
    private final float[] viewport = {0f, 0f, 1f, 1f};
    private SubPlot1 plt;
    private Body ball;
    private Water water;
    private Air air;
    private final float speedUp = 0.5f;
    private float timer;


    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        ball = new Body(new PVector(0, 12), new PVector(8, 6), mass, radius, p.color(255, 255, 0));
        water = new Water(4, p.color(0, 255, 255));
        air = new Air();
        timer = 0f;
    }

    public void draw(PApplet p, float dt) {
        PVector f = new PVector(0, mass * g);
        ball.applyConstantForce(f);
        if (water.isInside(ball))
            f = water.drag(ball);
        else
            f = air.drag(ball);
        ball.applyConstantForce(f);
        ball.move(dt * speedUp);

        water.display(p, plt);
        ball.display(p, plt);

        timer += (dt * speedUp);
        if (ball.getPos().y < 0) {
            p.noLoop();
            System.out.println(timer);
        }
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
