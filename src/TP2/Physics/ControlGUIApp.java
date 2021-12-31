package TP2.Physics;

import Processing.IProcessingApp;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class ControlGUIApp implements IProcessingApp {
    private RigidBody rb;
    private MotionControl mc;
    private final float mass = 1f;
    private MotionControl.ControlType ct = MotionControl.ControlType.POSITION;


    public void setup(PApplet p) {
        rb = new RigidBody(mass);
        mc = new MotionControl(rb, ct);
    }

    public void draw(PApplet p, float dt) {
        p.background(255);
        p.translate(p.width / 2f, p.height / 2f);
        rb.move(dt, ct);
        rb.display(p);
        mc.display(p);
    }

    public void mousePressed(PApplet p) {
        float x = p.mouseX - p.width / 2f;
        float y = p.mouseY - p.height / 2f;
        mc.setVector(new PVector(x, y));
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {
        switch (p.key) {
            case 'p' -> ct = MotionControl.ControlType.POSITION;
            case 'v' -> ct = MotionControl.ControlType.VELOCITY;
            case 'f' -> ct = MotionControl.ControlType.FORCE;
            default -> {
            }
        }
        rb = new RigidBody(mass);
        mc = new MotionControl(rb, ct);
    }
}
