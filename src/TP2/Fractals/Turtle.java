package TP2.Fractals;

import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

public class Turtle {
    private float stepSize;
    private float angle;

    public Turtle(float stepSize, float angle) {
        this.stepSize = stepSize;
        this.angle = angle;
    }

    public float getStepSize() {
        return stepSize;
    }

    public void setStepSize(float stepSize) {
        this.stepSize = stepSize;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

        public void setPose(PVector position, float orientation, PApplet p, SubPlot1 plt) {
        float[] pp = plt.getPixelCoord(position.x, position.y);
        p.translate(pp[0], pp[1]);
        p.rotate(-orientation);
    }

    public void scaling(float s) {
        stepSize *= s;
    }

    public void render(LSystem lSys, PApplet p, SubPlot1 plt) {
        String sequence = lSys.getSequence();
        float[] stepSize = plt.getVectorCoord(this.stepSize, this.stepSize);
        p.stroke(0);
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c == 'F' || c == 'G') {
                p.line(0, 0, stepSize[0], 0);
                p.translate(stepSize[0], 0);
            } else if (c == 'f') p.translate(stepSize[0], 0);
            else if (c == '+') p.rotate(angle);
            else if (c == '-') p.rotate(-angle);
            else if (c == '[') p.pushMatrix();
            else if (c == ']') p.popMatrix();
        }
    }
}
