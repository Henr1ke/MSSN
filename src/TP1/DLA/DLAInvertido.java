package TP1.DLA;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class DLAInvertido extends DLA {
    public DLAInvertido(PApplet p) {
        super(p);
    }

    public float getPULL_FORCE() {
        return -super.getPULL_FORCE();
    }

    protected void inicializarWalkers() {
        for (int i = 0; i < N_WANDERING; i++)
            criarWalkerWandering();
    }

    protected void criarWalkerWandering() {
        PApplet p = this.getP();
        newWalker(new WalkerInvertido(this, Walker.State.WANDERING, new PVector(p.width / 2f, p.height / 2f)));
    }

    public boolean limitReached() {
        PApplet p = this.getP();
        int walkerRadius = this.getWALKER_RADIUS();
        List<Walker> stopped = this.getStopped();
        float dist;
        for (Walker walker : stopped) {
            dist = (PVector.dist(walker.getPos(), new PVector(p.width / 2.0f, p.height / 2.0f)));
            if (dist <= walkerRadius)
                return true;
        }
        return false;
    }
}
