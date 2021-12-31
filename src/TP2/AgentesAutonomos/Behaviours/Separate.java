package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import TP2.Physics.Body;
import processing.core.PVector;

public class Separate extends Behaviour {
    public Separate(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        PVector vd = new PVector();

        for (Body body : self.getEye().getNearSight()) {
            PVector r = PVector.sub(self.getPos(), body.getPos());
            float d = r.mag();
            r.div(d * d);
            vd.add(r);
        }

        return vd;
    }
}
