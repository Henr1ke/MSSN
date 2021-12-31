package TP2.AgentesAutonomos;

import TP2.AgentesAutonomos.Behaviours.Align;
import TP2.AgentesAutonomos.Behaviours.Cohesion;
import TP2.AgentesAutonomos.Behaviours.Separate;
import TP2.Physics.Body;
import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Flock {
    private final List<Boid> boids;

    public Flock(int nBoids, float mass, float radius, int color, float[] sacWeights, PApplet p, SubPlot1 plt) {
        boids = new ArrayList<>();

        double[] w = plt.getWorldWindow();
        for (int i = 0; i < nBoids; i++) {

            float x = p.random((float) w[0], (float) w[1]);
            float y = p.random((float) w[2], (float) w[3]);
            Boid boid = new Boid(new PVector(x, y), mass, radius, color, p, plt);
            boid.addBehaviour(new Separate(sacWeights[0]));
            boid.addBehaviour(new Align(sacWeights[1]));
            boid.addBehaviour(new Cohesion(sacWeights[2]));
            boids.add(boid);
        }

        List<Body> bodies = boidList2BodyList(boids);
        for (Boid boid : boids)
            boid.setEye(new Eye(boid, bodies));

    }

    public Boid getBoid(int n) {
        return boids.get(n);
    }

    private List<Body> boidList2BodyList(List<Boid> boids) {
        return new ArrayList<>(boids);
    }

    public void applyBehabiour(float dt) {
        for (Boid boid : boids) {
            boid.applyBehaviours(dt);
        }
    }

    public void display(PApplet p, SubPlot1 plt) {
        for (Boid b : boids)
            b.display(p, plt);
    }
}

