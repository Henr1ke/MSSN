package TP2.AgentesAutonomos;

import TP2.Physics.Body;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Eye {
    private final List<Body> allTrackingBodies;
    private List<Body> farSight;
    private List<Body> nearSight;
    private final Boid self;
    private final Body target;

    public Eye(Boid self, List<Body> allTrackingBodies) {
        this.self = self;
        this.allTrackingBodies = allTrackingBodies;
        this.target = allTrackingBodies.get(0);
    }

    public Body getTarget() {
        return target;
    }

    public List<Body> getFarSight() {
        return farSight;
    }

    public List<Body> getNearSight() {
        return nearSight;
    }

    public void look() {
        farSight = new ArrayList<>();
        nearSight = new ArrayList<>();

        for (Body body : allTrackingBodies) {
            if (farSight(body.getPos()))
                farSight.add(body);
            if (nearSight(body.getPos()))
                nearSight.add(body);
        }
    }

    public boolean inSight(PVector t, float maxDistance, float maxAngle) {
        PVector r = PVector.sub(t, self.getPos());
        float d = r.mag();
        float angle = PVector.angleBetween(r, self.getVel());
        return d > 0 && d < maxDistance && angle < maxAngle;
    }

    public boolean farSight(PVector t) {
        DNABoid dna = self.getDna();
        return inSight(t, dna.getVisionDist(), dna.getVisionAngle());
    }

    public boolean nearSight(PVector t) {
        return inSight(t, self.getDna().getVisionDist(), (float) Math.PI);
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushStyle();
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(self.getPos().x, self.getPos().y);
        p.translate(pp[0], pp[1]);
        p.rotate(-self.getVel().heading());
        p.noFill();
        p.stroke(255, 0, 0);
        p.strokeWeight(3);
        DNABoid dna = self.getDna();
        float[] dd1 = plt.getVectorCoord(dna.getVisionDist(), dna.getVisionDist());
        float[] dd2 = plt.getVectorCoord(dna.getVisionSafeDist(), dna.getVisionSafeDist());
        float visionAngle = dna.getVisionAngle();
        p.rotate(visionAngle);
        p.line(0, 0, dd1[0], 0);
        p.rotate(-2 * visionAngle);
        p.line(0, 0, dd1[0], 0);
        p.rotate(visionAngle);
        p.arc(0, 0, 2 * dd1[0], 2 * dd1[0], -visionAngle, visionAngle);
        p.stroke(255, 0, 255);
        p.circle(0, 0, 2 * dd2[0]);
        p.popMatrix();
        p.popStyle();
    }
}
