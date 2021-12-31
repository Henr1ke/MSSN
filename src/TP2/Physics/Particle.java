package TP2.Physics;

import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

public class Particle extends Body {

    private final float lifespan;
    private float timer;

    protected Particle(PVector pos, PVector vel, float radius, int color, float lifespan) {
        super(pos, vel, 0f, radius, color);
        this.lifespan = lifespan;
        this.timer = 0;
    }

    public boolean isDead() {
        return timer > lifespan;
    }

    public void move(float dt) {
        super.move(dt);
        timer += dt;
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushStyle();
        float alpha = PApplet.map(timer, 0, lifespan, 255, 0);
        p.fill(this.getColor(), alpha);
        float[] pp = plt.getPixelCoord(this.getPos().x, this.getPos().y);
        float[] r = plt.getVectorCoord(this.getRadius(), this.getRadius());
        p.noStroke();
        p.circle(pp[0], pp[1], 2*r[0]);
        p.popStyle();
    }
}
