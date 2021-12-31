package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import TP2.Physics.Body;
import processing.core.PVector;

public class Pursuit extends Behaviour {
    public Pursuit(float weight) {
        super(weight);
    }

    @Override
    public PVector getDesiredVel(Boid self) {
        Body bTarget = self.getEye().getTarget();
        PVector d = bTarget.getVel().mult(self.getDna().getDtPursuit());
        PVector target = PVector.add(bTarget.getPos(), d);
        return PVector.sub(target, self.getPos());
    }
}
