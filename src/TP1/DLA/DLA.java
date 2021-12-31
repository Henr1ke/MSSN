package TP1.DLA;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class DLA {
    private final PApplet P; //PApplet à qual esta instância pertence
    private final List<Walker> WANDERING; //Contém os walkers em estado wandering
    private final List<Walker> STOPPED; //Contém os walkers em estado STOPPED
    private final List<Walker> WALKERS; //Contém todos os walkers
    public final int N_WANDERING = 100;
    private final float PULL_FORCE = 0.0005f;
    private final int WALKER_RADIUS = 4;
    private final float STICKINESS = 0.8f;
    private final int N_STOPPED_COLORS = 5;
    private int STOPPED_COLOR_CHANGE_DIST;
    private int[] COLORS_STOPPED;

    public DLA(PApplet p) {
        this.P = p;
        this.WANDERING = new ArrayList<>();
        this.STOPPED = new ArrayList<>();
        this.WALKERS = new ArrayList<>();

        inicializarCOLORS_STOPPED();
        inicializarWalkers();
    }

    public List<Walker> getWalkers() {
        return WALKERS;
    }

    public List<Walker> getWandering() {
        return WANDERING;
    }

    public List<Walker> getStopped() {
        return STOPPED;
    }

    public PApplet getP() {
        return P;
    }

    public float getPULL_FORCE() {
        return PULL_FORCE;
    }

    public int getWALKER_RADIUS() {
        return WALKER_RADIUS;
    }

    public int getN_STOPPED_COLORS() {
        return N_STOPPED_COLORS;
    }

    public float getSTICKINESS() {
        return STICKINESS;
    }

    public int getSTOPPED_COLOR_CHANGE_DIST() {
        return STOPPED_COLOR_CHANGE_DIST;
    }

    public int[] getCOLORS_STOPPED() {
        return COLORS_STOPPED;
    }

    private void inicializarCOLORS_STOPPED() {
        STOPPED_COLOR_CHANGE_DIST = Math.min(P.width, P.height) / 2 / N_STOPPED_COLORS;
        COLORS_STOPPED = new int[N_STOPPED_COLORS];
        for (int i = 0; i < N_STOPPED_COLORS; i++)
            COLORS_STOPPED[i] = P.color(P.random(255), P.random(255), P.random(255));
    }

    protected void inicializarWalkers() {
        newWalker(new Walker(this, Walker.State.STOPPED, new PVector(P.width / 2f, P.height / 2f)));
        for (int i = 0; i < N_WANDERING; i++)
            criarWalkerWandering();
    }

    protected void criarWalkerWandering() {
        PVector pos = new PVector(P.width / 2.0f, P.height / 2.0f);
        PVector r = PVector.random2D().mult(Math.min(P.width, P.height) / 2.0f);
        pos.add(r);
        newWalker(new Walker(this, Walker.State.WANDERING, pos));
    }

    /**
     * Adiciona o Walker criado à lista WALKERS e também à lista WANDERING ou STOPPED,
     * dependendo do seu estado
     */
    public void newWalker(Walker walker) {
        WALKERS.add(walker);
        if (walker.getState() == Walker.State.STOPPED)
            STOPPED.add(walker);
        else
            WANDERING.add(walker);
    }

    /**
     * Atualiza a posição de cada Walker na lista WANDERING. Caso o estado de um Walker tenha passado para STOPPED
     * então esse walker passa da lista WANDERING para a lista STOPPED e é criado um Walker no estado WANDERING no
     * seu lugar
     */
    public void updateWanderers() {
        List<Walker> wanderingToRemove = new ArrayList<>();
        for (Walker walker : WANDERING) {
            walker.wander();

            // Se o estado do Walker passou para STOPPED então atualiza as listas STOPPED e WANDERING
            boolean changedToSTOPPED = walker.updateStateToStopped(STOPPED);
            if (changedToSTOPPED) {
                wanderingToRemove.add(walker);
                STOPPED.add(walker);
            }
        }
        WANDERING.removeAll(wanderingToRemove); // Remove da lista WANDERING todos os Walkers que mudaram de estado
        if (!limitReached())
            while (WANDERING.size() < N_WANDERING) // Cria um novo Walker no estado WANDERING até voltar a ter N_WANDERING Walkers no estado WANDERING
                criarWalkerWandering();
    }

    public void updateDisplay() {
        P.background(190);
        P.fill(0);
        P.circle(P.width / 2.0f, P.height / 2.0f, Math.min(P.width, P.height) + 2);
        P.fill(190);
        P.circle(P.width / 2.0f, P.height / 2.0f, Math.min(P.width, P.height));
        for (Walker walker : WALKERS)
            walker.display();
    }

    /**
     * Retorna true caso algum Walker no estado STOPPED esteja fora da circunferência limite
     */
    public boolean limitReached() {
        float dist;
        for (Walker walker : STOPPED) {
            dist = (PVector.dist(walker.getPos(), new PVector(P.width / 2.0f, P.height / 2.0f)));
            if (dist >= Math.min(P.width / 2, P.height / 2) - WALKER_RADIUS)
                return true;
        }
        return false;
    }
}
