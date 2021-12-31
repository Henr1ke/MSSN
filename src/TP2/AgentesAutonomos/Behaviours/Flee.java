package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public class Flee extends Behaviour {
    public Flee(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        return PVector.sub(self.getEye().getTarget().getPos(), self.getPos()).mult(-1);
    }
}
