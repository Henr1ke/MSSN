package TP2.Physics;

import TP2.Tools.SubPlot1;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem extends Body {

    private final List<Particle> particles;
    private final PSControl psc;

    protected ParticleSystem(PVector pos, PVector vel, float mass, float radius, PSControl psc) {
        super(pos, vel, mass, radius, 0);
        this.psc = psc;
        this.particles = new ArrayList<>();
    }

    public PSControl getPSControl() {
        return psc;
    }

    private void addParticle() {
        particles.add(new Particle(this.getPos(), psc.getRndVel(), psc.getRndRadius(), psc.getColor(), psc.getRndLifetime()));
    }

    private void addParticles(float dt) {
        float particlesPFrame = psc.getFlow() * dt;
        int n = (int) particlesPFrame;
        float f = particlesPFrame - n;
        for (int i = 0; i < n; i++)
            addParticle();
        if (Math.random() < f)
            addParticle();
    }


    public void move(float dt) {
        super.move(dt);
        addParticles(dt);

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.move(dt);
            if (p.isDead())
                particles.remove(i);
        }
    }

    public void display(PApplet p, SubPlot1 plt) {
        for (Particle particle : particles)
            particle.display(p, plt);
    }
}
