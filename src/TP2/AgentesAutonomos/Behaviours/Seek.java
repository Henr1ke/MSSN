package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public class Seek extends Behaviour {
    public Seek(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        return PVector.sub(self.getEye().getTarget().getPos(), self.getPos());
    }
}
