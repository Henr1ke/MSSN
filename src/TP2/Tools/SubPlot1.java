package TP2.Tools;

import processing.core.PVector;

public class SubPlot1 {

    private double[] worldWindow;
    private float[] viewport;
    private double mx, my, bx, by;
    private final float canvasW, canvasH;
    private PVector originalDragPos;

    public SubPlot1(double[] worldWindow, float[] viewport, float canvasW, float canvasH) {

        this.worldWindow = worldWindow;
        this.viewport = viewport;

        this.canvasW = canvasW;
        this.canvasH = canvasH;

        this.originalDragPos = new PVector();
        updateMB();
    }

    public void setOriginalDragPos(PVector pVector) {
        originalDragPos = pVector;
    }

    private void updateMB() {
        mx = viewport[2] * canvasW / (worldWindow[1] - worldWindow[0]);
        my = -viewport[3] * canvasH / (worldWindow[3] - worldWindow[2]);
        bx = viewport[0] * canvasW;
        by = (1 - viewport[1]) * canvasH;
    }

    public void zoom(float zoomFactor) {
        for (int i = 0; i < worldWindow.length; i++)
            worldWindow[i] /= (1 + zoomFactor);
        updateMB();
    }

    public void drag(PVector pVector) {
        double[] coord = getWorldCoord(pVector.x, pVector.y);
        worldWindow[0] -= coord[0] - originalDragPos.x;
        worldWindow[1] -= coord[0] - originalDragPos.x;
        worldWindow[2] -= coord[1] - originalDragPos.y;
        worldWindow[3] -= coord[1] - originalDragPos.y;

        originalDragPos = pVector;
    }

    public float[] getPixelCoord(double x, double y) {
        float[] coord = new float[2];
        coord[0] = (float) (bx + mx * (x - worldWindow[0]));
        coord[1] = (float) (by + my * (y - worldWindow[2]));

        return coord;
    }

    public float[] getPixelCoord(double[] xy) {
        return (getPixelCoord(xy[0], xy[1]));
    }

    public double[] getWorldCoord(float xx, float yy) {
        double[] coord = new double[2];
        coord[0] = worldWindow[0] + (xx - bx) / mx;
        coord[1] = worldWindow[2] + (yy - by) / my;

        return coord;
    }

    public double[] getWorldCood(float[] xy) {
        return getWorldCoord(xy[0], xy[1]);
    }

    public boolean isInside(float xx, float yy) {
        double[] c = getWorldCoord(xx, yy);
        return (c[0] >= worldWindow[0] && c[0] <= worldWindow[1] && c[1] >= worldWindow[2] && c[1] <= worldWindow[3]);
    }

    public boolean isInside(float[] xy) {
        return isInside(xy[0], xy[1]);
    }

    public float[] getBoundingBox() {
        float[] c1 = getPixelCoord(worldWindow[0], worldWindow[2]);
        float[] c2 = getPixelCoord(worldWindow[1], worldWindow[3]);

        return new float[]{c1[0], c2[1], c2[0] - c1[0], c1[1] - c2[1]};
    }

    public float[] getBox(double cx, double cy, double dimx, double dimy) {
        float[] c1 = getPixelCoord(cx, cy);
        float[] c2 = getPixelCoord(cx + dimx, cy + dimy);

        return new float[]{c1[0], c2[1], c2[0] - c1[0], c1[1] - c2[1]};
    }

    public float[] getVectorCoord(double dx, double dy) {
        float[] v = new float[2];
        v[0] = (float) (dx * mx);
        v[1] = (float) (-dy * my);
        return v;
    }

    public float[] getVectorCoord(double[] dxdy) {
        return getVectorCoord(dxdy[0], dxdy[1]);
    }

    public double[] getWorldWindow() {
        return worldWindow;
    }

    public void setWorldWindow(double[] worldWindow) {
        this.worldWindow = worldWindow;
    }

    public float[] getViewport() {
        return viewport;
    }

    public void setViewport(float[] viewport) {
        this.viewport = viewport;
    }
}