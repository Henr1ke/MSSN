package Processing;

import TP2.Physics.SolarSystemApp;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class ProcessingSetup extends PApplet {

    public static IProcessingApp app;
    private int lastUpdate;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        lastUpdate = millis();
        app.setup(this);
    }

    public void draw() {
        int now = millis();
        float deltaT = (now - lastUpdate) / 1000f;
        lastUpdate = now;
        app.draw(this, deltaT);
    }

    public void mousePressed() {
        app.mousePressed(this);
    }

    public void mouseDragged() {
        app.mouseDragged(this);
    }

    public void mouseWheel(MouseEvent event) {
        app.mouseWheel(this, event);
    }

    public void keyPressed() {
        app.keyPressed(this);
    }


    public static void main(String[] args) {
        app = new SolarSystemApp();
        PApplet.main(ProcessingSetup.class);
    }
}
