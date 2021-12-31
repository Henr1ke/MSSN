package TP2.Physics;

import processing.core.PVector;

public abstract class Mover {

    private static final double G = 6.67e-11;

    private PVector pos;
    private PVector vel;
    private PVector acc;
    private float mass;


    protected Mover(PVector pos, PVector vel, float mass) {
        this.pos = pos.copy();
        this.vel = vel;
        this.acc = new PVector();
        this.mass = mass;
    }

    public PVector getPos() {
        return pos;
    }

    public PVector getVel() {
        return vel;
    }

    public PVector getAcc() {
        return acc;
    }

    public float getMass() {
        return mass;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public void setVel(PVector vel) {
        this.vel = vel;
    }

    public void applyConstantForce(PVector force) {
        acc.add(PVector.div(force, mass));
    }

    public PVector getAttractionForce(Mover m) {
        PVector r = PVector.sub(pos, m.pos);
        float dist = r.mag();
        float strength = (float) (G * mass * m.mass / Math.pow(dist, 2));
        return r.normalize().mult(strength);
    }

    public void move(float dt) {
        vel.add(acc.mult(dt));
        pos.add(PVector.mult(vel, dt));
        acc.mult(0);
    }
}
