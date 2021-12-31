package TP2.AgentesAutonomos;

import TP2.AgentesAutonomos.Behaviours.Behaviour;
import TP2.Physics.Body;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Boid extends Body {

    private PShape shape;
    private final List<Behaviour> behaviours;
    private final DNABoid dna;
    private Eye eye;
    private float phiWander;
    private final double[] window;
    private float sumWeights;

    protected Boid(PVector pos, float mass, float radius, int color, PApplet p, SubPlot1 plt) {
        super(pos, new PVector(), mass, radius, color);
        setShape(p, plt);
        behaviours = new ArrayList<>();
        dna = new DNABoid();
        window = plt.getWorldWindow();
    }

    public DNABoid getDna() {
        return dna;
    }

    public Eye getEye() {
        return eye;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public float getPhiWander() {
        return phiWander;
    }

    public void setPhiWander(float phiWander) {
        this.phiWander = phiWander;
    }

    private void updateSumWeights() {
        sumWeights = 0;
        for (Behaviour behaviour : behaviours) {
            sumWeights += behaviour.getWeight();
        }
    }

    public void setShape(PApplet p, SubPlot1 plt, float radius, int color) {
        this.setRadius(radius);
        this.setColor(color);
        setShape(p, plt);
    }

    public void setShape(PApplet p, SubPlot1 plt) {
        float[] rr = plt.getVectorCoord(this.getRadius(), this.getRadius());
        shape = p.createShape();
        shape.beginShape();
        shape.noStroke();
        shape.fill(this.getColor());
        shape.vertex(-rr[0], rr[0] / 2);
        shape.vertex(rr[0], 0);
        shape.vertex(-rr[0], -rr[0] / 2);
        shape.vertex(-rr[0] / 2, 0);
        shape.endShape(PConstants.CLOSE);
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
        updateSumWeights();
    }

    public void removeBehaviour(Behaviour behaviour) {
        behaviours.remove(behaviour);
        updateSumWeights();
    }

    public void applyBehaviour(int i, float dt) {
        if (eye != null)
            eye.look();
        Behaviour behaviour = behaviours.get(i);
        PVector vd = behaviour.getDesiredVel(this);
        move(dt, vd);
    }

    public void applyBehaviours(float dt) {
        if (eye != null)
            eye.look();
        PVector vd = new PVector();

        for (Behaviour behaviour : behaviours) {
            PVector vdd = behaviour.getDesiredVel((this));
            vdd.mult(behaviour.getWeight() / sumWeights);
            vd.add(vdd);
        }
        move(dt, vd);
    }

    protected void move(float dt, PVector vd) {
        vd.normalize().mult(dna.getVelocity());
        PVector fs = PVector.sub(vd, this.getVel());
        applyConstantForce(fs.limit(dna.getForce()));
        super.move(dt);
        if (this.getPos().x < window[0])
            this.getPos().x += window[1] - window[0];
        if (this.getPos().y < window[2])
            this.getPos().y += window[3] - window[2];
        if (this.getPos().x >= window[1])
            this.getPos().x -= window[1] - window[0];
        if (this.getPos().y >= window[3])
            this.getPos().y -= window[3] - window[2];
    }

    public PVector seek(PVector target) {
        PVector vd = PVector.sub(target, this.getPos());
        vd.normalize().mult(dna.getVelocity());
        return PVector.sub(vd, this.getVel());
    }

    public void display(PApplet p, SubPlot1 plt) {
        p.pushMatrix();
        PVector vel = this.getVel();
        float[] pp = plt.getPixelCoord(this.getPos().x, this.getPos().y);
        float[] vv = plt.getVectorCoord(vel.x, vel.y);
        PVector vaux = new PVector(vv[0], vv[1]);
        p.translate(pp[0], pp[1]);
        p.rotate(-vaux.heading());
        p.shape(shape);
        p.popMatrix();
    }
}
