package Processing;

import processing.core.PApplet;
import processing.event.MouseEvent;

public interface IProcessingApp {

    public void setup(PApplet p);

    public void draw(PApplet p, float dt);

    public void mousePressed(PApplet p);

    public void mouseDragged(PApplet p);

    public void mouseWheel(PApplet p, MouseEvent e);

    public void keyPressed(PApplet p);
}
