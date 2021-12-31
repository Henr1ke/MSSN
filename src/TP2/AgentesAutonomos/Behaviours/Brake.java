package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public class Brake extends Behaviour {
    public Brake(float weight) {
        super(weight);
    }

    public PVector getDesiredVel(Boid self) {
        return new PVector();
    }
}
