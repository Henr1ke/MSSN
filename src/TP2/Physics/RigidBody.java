package TP2.Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class RigidBody {

    private static final int RADIUS = 30;

    private PVector pos;
    private PVector vel;
    private PVector acc;
    private final float mass;

    private MotionControl mc;

    public RigidBody(float mass) {
        pos = new PVector();
        vel = new PVector();
        acc = new PVector();
        this.mass = mass;
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public PVector getVel() {
        return vel;
    }

    public void setVel(PVector vel) {
        this.vel = vel;
    }

    public PVector getAcc() {
        return acc;
    }

    public void applyForce(PVector force) {
        this.acc = PVector.div(force, mass);
    }

    public float getMass() {
        return mass;
    }

    public void move(float dt, MotionControl.ControlType ct) {
        switch (ct) {
            case VELOCITY -> this.pos.add(PVector.mult(vel, dt));
            case FORCE -> {
                this.pos.add(PVector.mult(vel, dt));
                this.vel.add(PVector.mult(acc, dt));
            }
            default ->{
            }
        }
    }

    public void display(PApplet p) {
        p.circle(this.pos.x, this.pos.y, RADIUS);
    }
}
