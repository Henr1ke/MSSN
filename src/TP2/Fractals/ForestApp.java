package TP2.Fractals;

import Processing.IProcessingApp;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ForestApp implements IProcessingApp {
    private final double[] window = {-15, 15, 0, 15};
    private final float[] viewport = {0, 0, 1, 1};
    private SubPlot1 plt;
    private List<Tree> forest;

    public void setup(PApplet p) {
        plt = new SubPlot1(window, viewport, p.width, p.height);
        forest = new ArrayList<>();


    }

    public void draw(PApplet p, float dt) {
        float[] bb = plt.getBoundingBox();
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        for (Tree tree : forest) {
            tree.grow(dt);
            tree.display(p, plt);
        }
    }

    public void mousePressed(PApplet p) {
        double[] w = plt.getWorldCoord(p.mouseX, p.mouseY);
        PVector pos = new PVector((float) w[0], (float) w[1]);
        Tree tree;
        if (p.random(100) < 50) {
            Rule[] rules = new Rule[1];
            rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
            tree = new Tree("F", rules, pos, .4f, PApplet.radians(22.5f), 4, 0.5f, 1f, p);
        } else {
            Rule[] rules = new Rule[2];
            rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
            rules[1] = new Rule('F', "FF");
            tree = new Tree("X", rules, pos, .4f, PApplet.radians(22.5f), 4, 0.5f, 1f, p);
        }
        forest.add(tree);
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
