package TP2.AgentesAutonomos;


public class DNABoid {
    private final float velocity;
    private final float force;
    private final float visionDist;
    private final float visionSafeDist;
    private final float visionAngle;
    private final float dtPursuit;
    private final float radiusArrive;
    private final float dtWander;
    private final float radiusWander;
    private final float dtPhiWander;

    public DNABoid() {
        velocity = random(3, 5);
        force = random(4, 7);

        visionDist = random(2, 4);
        visionSafeDist = .25f * visionDist;
        visionAngle = (float) Math.PI * .8f;

        dtPursuit = random(0.5f, 1f);

        radiusArrive = random(3, 5);

        dtWander = random(1f, 1f);
        radiusWander = random(3, 3);
        dtPhiWander = (float) (Math.PI / 8);
    }

    public float getVelocity() {
        return velocity;
    }

    public float getForce() {
        return force;
    }

    public float getVisionDist() {
        return visionDist;
    }

    public float getVisionSafeDist() {
        return visionSafeDist;
    }

    public float getVisionAngle() {
        return visionAngle;
    }

    public float getDtPursuit() {
        return dtPursuit;
    }

    public float getRadiusArrive() {
        return radiusArrive;
    }

    public float getDtWander() {
        return dtWander;
    }

    public float getRadiusWander() {
        return radiusWander;
    }

    public float getDtPhiWander() {
        return dtPhiWander;
    }

    public static float random(float min, float max) {
        return (float) (min + (max - min) * Math.random());
    }
}
