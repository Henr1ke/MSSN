package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public interface IBehaviour {
    PVector getDesiredVel(Boid self);

    float getWeight();

    void setWeight(float weight);
}
