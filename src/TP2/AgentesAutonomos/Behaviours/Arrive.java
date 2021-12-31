package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public class Arrive extends Behaviour {
    public Arrive(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        PVector vd = PVector.sub(self.getEye().getTarget().getPos(), self.getPos());
        float d = vd.mag();
        float R = self.getDna().getRadiusArrive();

        if (d < R)
            vd.mult(d / R);
        return vd;
    }
}
