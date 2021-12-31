package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import TP2.Physics.Body;
import processing.core.PVector;

public class Cohesion extends Behaviour{
    public Cohesion(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        PVector target = self.getPos().copy();

        for (Body body : self.getEye().getFarSight())
            target.add(body.getPos());
        target.div(self.getEye().getFarSight().size()+1);
        return PVector.sub(target, self.getPos());
    }
}
