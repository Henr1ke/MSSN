package TP2.Fractals;


import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

public class Tree {
    private final LSystem lSys;
    private final Turtle turtle;
    private final PVector position;

    private float stepSize;
    private float growthRate;

    private final int numOfSeasonsToGrow;
    private final float scalingFactor;
    private final float intervalBetweenSeasons;
    private float now;
    private float nextSeasonTime;

    public Tree(String axiom, Rule[] ruleset, PVector position, float stepSize, float angle, int niter, float scalingFactor, float intervalBetweenSeasons, PApplet p) {
        lSys = new LSystem(axiom, ruleset);

        this.stepSize = 0;
        growthRate = stepSize / intervalBetweenSeasons;
        turtle = new Turtle(0, angle);

        this.position = position;
        numOfSeasonsToGrow = niter;
        this.scalingFactor = scalingFactor;
        this.intervalBetweenSeasons = intervalBetweenSeasons;
        now = p.millis() / 1000f;
        nextSeasonTime = now + intervalBetweenSeasons;
    }

    public void grow(float dt) {
        now += dt;
        if (now < nextSeasonTime) {
            stepSize += growthRate * dt;
            turtle.setStepSize(stepSize);
        }
        else if (lSys.getGeneration() < numOfSeasonsToGrow) {
            lSys.nextGeneration();
            stepSize *= scalingFactor;
            growthRate *= scalingFactor;
            turtle.setStepSize(stepSize);
            nextSeasonTime = now + intervalBetweenSeasons;
        }
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushMatrix();
        turtle.setPose(position, (float) (Math.PI / 2f), p, plt);
        turtle.render(lSys, p, plt);
        p.popMatrix();
    }
}
