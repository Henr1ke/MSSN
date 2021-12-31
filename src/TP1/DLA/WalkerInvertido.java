package TP1.DLA;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class WalkerInvertido extends Walker {
    public WalkerInvertido(DLA dla, State state, PVector pos) {
        super(dla, state, pos);
    }

    public boolean updateStateToStopped(List<Walker> stoppedWalkers) {
        if (this.getState() != State.STOPPED) {
            DLA dla = this.getDla();
            PApplet p = dla.getP();
            PVector pos = this.getPos();
            int walkerRadius = dla.getWALKER_RADIUS();
            PVector center = new PVector(p.width / 2f, p.height / 2f);

            // O estado também passa para STOPPED caso toque na circunferência limite
            if (PVector.dist(pos, center) >= Math.min(p.width, p.height) / 2f - walkerRadius) {
                setState(State.STOPPED);
                return true;
            }

            float stickiness = dla.getSTICKINESS();
            for (Walker walker : stoppedWalkers) {
                boolean tocou = PVector.dist(pos, walker.getPos()) <= 2 * walkerRadius;
                if (tocou && p.random(1) <= stickiness) {
                    setState(State.STOPPED);
                    return true;
                }
            }
        }
        return false;
    }
}
