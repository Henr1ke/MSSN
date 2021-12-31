package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public class Wander extends Behaviour {
    public Wander(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        PVector center = self.getPos().copy();
        center.add(self.getVel().copy().mult(self.getDna().getDtWander()));

        PVector target = new PVector(self.getDna().getRadiusWander() * (float) Math.cos(self.getPhiWander()), self.getDna().getRadiusWander() * (float) Math.sin(self.getPhiWander()));
        target.add(center);
        self.setPhiWander((float) (self.getPhiWander() + 2* (Math.random()-0.5) * self.getDna().getDtPhiWander()));

        return PVector.sub(target, self.getPos());
    }
}
