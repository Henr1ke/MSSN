package TP2.Physics;

import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

public class Body extends Mover {

    private int color;
    private float radius;


    public Body(PVector pos, PVector vel, float mass, float radius, int color) {
        super(pos, vel, mass);
        this.radius = radius;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushStyle();
        float[] pp = plt.getPixelCoord(this.getPos().x, this.getPos().y);
        float[] r = plt.getVectorCoord(radius, radius);
        p.noStroke();
        p.fill(color);
        p.circle(pp[0], pp[1], 2 * r[0]);
        p.popStyle();
    }
}
