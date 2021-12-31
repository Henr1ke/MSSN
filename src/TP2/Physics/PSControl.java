package TP2.Physics;

import processing.core.PVector;

public class PSControl {

    private float averageAngle;
    private float dispersionAngle;
    private float minVelocity;
    private float maxVelocity;
    private float minLifetime;
    private float maxLifetime;
    private float minRadius;
    private float maxRadius;
    private float flow;
    private int color;

    public PSControl(float[] angle, float[] velocity, float[] lifetime, float[] radius, float flow, int color) {
        setAngle(angle);
        setVelocity(velocity);
        setLifetime(lifetime);
        setRadius(radius);
        setFlow(flow);
        setColor(color);
    }

    public void setAngle(float[] angle) {
        averageAngle = angle[0];
        dispersionAngle = angle[1];
    }

    public void setVelocity(float[] velocity) {
        minVelocity = velocity[0];
        maxVelocity = velocity[1];
    }

    public void setLifetime(float[] lifetime) {
        minLifetime = lifetime[0];
        maxLifetime = lifetime[1];
    }

    public void setRadius(float[] radius) {
        minRadius = radius[0];
        maxRadius = radius[1];
    }

    public float getFlow() {
        return flow;
    }

    public void setFlow(float flow) {
        this.flow = flow;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getRndAngle() {
        return getRnd(averageAngle - dispersionAngle / 2, averageAngle + dispersionAngle / 2);
    }

    public PVector getRndVel() {
        PVector v = PVector.fromAngle(getRndAngle());
        return v.mult(getRnd(minVelocity, maxVelocity));
    }

    public float getRndRadius() {
        return getRnd(minRadius, maxRadius);
    }

    public float getRndLifetime() {
        return getRnd(minLifetime, maxLifetime);
    }

    public static float getRnd(float min, float max) {
        return min + (float) (Math.random() * (max - min));
    }
}
