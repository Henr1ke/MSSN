package TP1.DLA;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class Walker {

    public enum State {
        STOPPED,
        WANDERING
    }

    private final DLA dla;
    private final PVector pos;
    private State state;
    private int color;

    public Walker(DLA dla, State state, PVector pos) {
        this.dla = dla;
        this.pos = pos;
        setState(state);
    }

    public DLA getDla() {
        return dla;
    }

    public PVector getPos() {
        return pos;
    }

    public State getState() {
        return state;
    }

    /**
     * Atualiza o estado do Walker. Se o novo estado é STOPPED então atribui uma cor dependendo da distância ao centro
     * da circunferência limite, caso contrario atribui branco.
     */
    public void setState(State state) {
        this.state = state;
        PApplet p = dla.getP();
        if (state == State.STOPPED) {
            int dist = (int) (PVector.dist(pos, new PVector(p.width / 2.0f, p.height / 2.0f)));
            color = dla.getCOLORS_STOPPED()[Math.min(dist / dla.getSTOPPED_COLOR_CHANGE_DIST(), dla.getN_STOPPED_COLORS() - 1)];
        } else
            color = p.color(255);
    }

    public boolean updateStateToStopped(List<Walker> stoppedWalkers) {
        if (state != State.STOPPED) {
            PApplet p = dla.getP();
            int walkerRadius = dla.getWALKER_RADIUS();
            float stickiness = dla.getSTICKINESS();
            for (Walker walker : stoppedWalkers) {
                boolean tocou = PVector.dist(pos, walker.pos) <= 2 * walkerRadius;
                if (tocou && p.random(1) <= stickiness) {
                    setState(State.STOPPED);
                    return true;
                }
            }
        }
        return false;
    }

    public void wander() {
        PApplet p = dla.getP();
        PVector step = PVector.random2D();
        pos.add(step);
        pos.lerp(new PVector(p.width / 2.0f, p.height / 2.0f), dla.getPULL_FORCE());
        pos.x = PApplet.constrain(pos.x, 0, p.width);
        pos.y = PApplet.constrain(pos.y, 0, p.height);
    }

    public void display() {
        PApplet p = dla.getP();
        p.fill(color);
        p.circle(pos.x, pos.y, 2 * dla.getWALKER_RADIUS());
    }
}
