package TP1.DLA;

import processing.core.PApplet;
import processing.core.PVector;

public class RunDLAInvertido extends RunDLA{
    public void setup(PApplet p) {
        dla = new DLAInvertido(p);
    }

    public void mousePressed(PApplet p) {
        if (!dla.limitReached()) {
            dla.newWalker(new WalkerInvertido(dla, Walker.State.WANDERING, new PVector(p.mouseX, p.mouseY)));
        }
    }
}
