package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import TP2.Physics.Body;
import processing.core.PVector;

public class Align extends Behaviour {
    public Align(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        PVector vd = self.getVel().copy();
        for (Body body : self.getEye().getFarSight())
            vd.add(body.getVel());

        return vd.div(self.getEye().getFarSight().size() + 1);
    }
}
