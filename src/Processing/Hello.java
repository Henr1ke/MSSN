package Processing;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Hello implements IProcessingApp{

    private int c;

    public void setup(PApplet p){
        c = p.color(255,0,0);
    }

    public void draw(PApplet p, float dt){
        p.fill(c);
        p.circle(p.mouseX,p.mouseY,50);
        System.out.println("Time: " + dt + "ms;\tFPS: " + Math.round(1f/ dt));
    }

    public void mousePressed(PApplet p){
        c = c == p.color(255,0,0)? p.color(0,255,0) : p.color(255,0,0);
    }

    public void mouseDragged(PApplet p) {

    }

    public void mouseWheel(PApplet p, MouseEvent e) {

    }

    public void keyPressed(PApplet p) {

    }
}
