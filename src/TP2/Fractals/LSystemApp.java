package TP2.Fractals;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class LSystemApp implements IProcessingApp {
    LSystem lSys;
    private final double[] window = {-15, 15, 0, 15};
    private final float[] viewport = {.1f, .1f, .8f, .8f,};
    private final PVector startingPos = new PVector();
    private SubPlot1 plt;
    private Turtle turtle;

    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        Rule[] rules = new Rule[1];
//        rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
//        rules[1] = new Rule('F', "FF");
        rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
        lSys = new LSystem("F", rules);
        turtle = new Turtle(5, PApplet.radians(22.5f));
    }

    public void draw(PApplet p, float dt) {
        float[] bb = plt.getBoundingBox();
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        turtle.setPose(startingPos, PApplet.radians(90), p, plt);
        turtle.render(lSys, p, plt);
    }

    public void mousePressed(PApplet p) {
        System.out.println(lSys.getSequence());
        lSys.nextGeneration();
        turtle.scaling(0.5f);
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
