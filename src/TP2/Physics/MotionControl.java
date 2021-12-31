package TP2.Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class MotionControl {

    public enum ControlType {
        POSITION,
        VELOCITY,
        FORCE
    }

    private final RigidBody rb;
    private ControlType ct;
    private PVector vector;

    public MotionControl(RigidBody rb, ControlType ct) {
        this.rb = rb;
        this.ct = ct;
        vector = new PVector();
    }

    public ControlType getCt() {
        return ct;
    }

    public void setCt(ControlType ct) {
        this.ct = ct;
    }

    public void setVector(PVector vector) {
        this.vector = vector;
        switch (this.ct) {
            case POSITION -> rb.setPos(vector);
            case VELOCITY -> rb.setVel(vector);
            case FORCE -> rb.applyForce(vector);
        }
    }

    public void display(PApplet p) {
        p.line(0, 0, vector.x, vector.y);
    }
}
