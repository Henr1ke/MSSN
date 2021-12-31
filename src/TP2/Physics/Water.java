package TP2.Physics;

import TP2.Tools.SubPlot1;
import processing.core.PApplet;

public class Water extends Fluid {
    private final float waterHeight;
    private final int color;

    public Water(float waterHeight, int color) {
        super(1000f);
        this.waterHeight = waterHeight;
        this.color = color;
    }

    public boolean isInside(Body b) {
        return (b.getPos().y - b.getRadius() <= waterHeight);
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushStyle();
        p.noStroke();
        p.fill(color);
        float[] pp = plt.getBox(0, 0, plt.getWorldWindow()[1], waterHeight);
        p.rect(pp[0], pp[1], pp[2], pp[3]);
        p.popStyle();
    }
}
