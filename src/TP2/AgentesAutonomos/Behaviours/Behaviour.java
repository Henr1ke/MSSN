package TP2.AgentesAutonomos.Behaviours;

import TP2.AgentesAutonomos.Boid;
import processing.core.PVector;

public abstract class Behaviour implements IBehaviour {
    private float weight;

    public Behaviour(float weight){
        this.weight = weight;
    }

    public PVector getDesiredVel(Boid self) {
        return null;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
