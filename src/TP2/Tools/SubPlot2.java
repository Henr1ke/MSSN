package TP2.Tools;

import processing.core.PVector;

public class SubPlot2 {
    private final float VP_W, VP_H, CANVAS_W, CANVAS_H;
    private final PVector VP_CENTER;
    private float windowW, windowH, mx, my, bx, by;
    private PVector windowCenter;

    public SubPlot2(float canvasW, float canvasH, float windowW, float windowH) {
        this.CANVAS_W = canvasW;
        this.CANVAS_H = canvasH;
        this.windowW = windowW;
        this.windowH = windowH;
        this.VP_W = 1;
        this.VP_H = 1;

        this.VP_CENTER = new PVector(.5f, .5f);
        this.windowCenter = new PVector(0f, 0f);

        updateMB();
    }

    public SubPlot2(float canvasW, float canvasH, float windowW, float windowH, PVector windowCenter) {
        this.CANVAS_W = canvasW;
        this.CANVAS_H = canvasH;
        this.windowW = windowW;
        this.windowH = windowH;
        this.VP_W = 1;
        this.VP_H = 1;

        this.VP_CENTER = new PVector(.5f, .5f);
        this.windowCenter = windowCenter;

        updateMB();
    }

    public SubPlot2(float canvasW, float canvasH, float windowW, float windowH, float vpW, float vpH, PVector vpTopLeftCorner) {
        this.CANVAS_W = canvasW;
        this.CANVAS_H = canvasH;
        this.windowW = windowW;
        this.windowH = windowH;
        this.VP_W = 1;
        this.VP_H = 1;

        float vpX = vpTopLeftCorner.x + Math.min(vpW, 1 - vpTopLeftCorner.x) / 2;
        float vpY = vpTopLeftCorner.y + Math.min(vpH, 1 - vpTopLeftCorner.y) / 2;
        this.VP_CENTER = new PVector(vpX, vpY);
        this.windowCenter = new PVector(0f, 0f);

        updateMB();
    }

    public SubPlot2(float canvasW, float canvasH, float windowW, float windowH, PVector windowCenter, float vpW, float vpH, PVector vpTopLeftCorner) {
        this.CANVAS_W = canvasW;
        this.CANVAS_H = canvasH;
        this.windowW = windowW;
        this.windowH = windowH;
        this.VP_W = 1;
        this.VP_H = 1;

        float vpX = vpTopLeftCorner.x + Math.min(vpW, 1 - vpTopLeftCorner.x) / 2;
        float vpY = vpTopLeftCorner.y + Math.min(vpH, 1 - vpTopLeftCorner.y) / 2;
        this.VP_CENTER = new PVector(vpX, vpY);
        this.windowCenter = windowCenter;

        updateMB();
    }

    public PVector getWindowCenter() {
        return new PVector(windowCenter.x, windowCenter.y);
    }

    public float[] getWindowWH() {
        return new float[]{windowW, windowH};
    }

    public PVector getViewportCenter() {
        return new PVector(VP_CENTER.x, VP_CENTER.y);
    }

    public float[] getViewportWH() {
        return new float[]{VP_W, VP_H};
    }

    public void resetWindowCenter() {
        windowCenter = new PVector(0f, 0f);
    }

    private float[] getWindowLimitsX() {
        return new float[]{windowCenter.x - windowW / 2, windowCenter.x + windowW / 2};
    }

    private float[] getWindowLimitsY() {
        return new float[]{windowCenter.y - windowH / 2, windowCenter.y + windowH / 2};
    }

    private void updateMB() {
        mx = VP_W * CANVAS_W / windowW;
        my = VP_H * CANVAS_H / windowH;
        bx = CANVAS_W * VP_CENTER.x;
        by = CANVAS_H * VP_CENTER.y;
    }

    public void zoom(float zoomFactor) {
        windowW /= (1 + zoomFactor);
        windowH /= (1 + zoomFactor);
        updateMB();
    }

    public void shift(PVector sVector) {
        PVector vpVector = viewport2window(sVector);
        windowCenter = new PVector(vpVector.x, vpVector.y);
    }

    public PVector viewport2window(float vpX, float vpY) {
        float x = (vpX - bx) / mx + windowCenter.x;
        float y = (vpY - by) / my + windowCenter.y;

        return new PVector(x, y);
    }

    public PVector viewport2window(PVector vpVector) {
        return viewport2window(vpVector.x, vpVector.y);
    }

    public PVector window2viewport(float windowX, float windowY) {
        float x = (windowX - windowCenter.x) * mx + bx;
        float y = (windowY - windowCenter.y) * my + by;

        return new PVector(x, y);
    }

    public PVector window2viewport(PVector wVector) {
        return window2viewport(wVector.x, wVector.y);
    }

    public PVector vectorInWindow2viewport(float x, float y) {
        return new PVector(x * mx, y * my);
    }

    public PVector vectorInWindow2viewport(PVector vpVector) {
        return vectorInWindow2viewport(vpVector.x, vpVector.y);
    }

    public boolean isInside(float x, float y) {
        PVector v = viewport2window(x, y);
        float[] xLims = getWindowLimitsX();
        float[] yLims = getWindowLimitsY();
        return (v.x >= xLims[0] && v.x <= xLims[1] && v.y >= yLims[0] && v.y <= yLims[1]);
    }

    public boolean isInside(PVector vpVector) {
        PVector v = viewport2window(vpVector);
        float[] xLims = getWindowLimitsX();
        float[] yLims = getWindowLimitsY();
        return (v.x >= xLims[0] && v.x <= xLims[1] && v.y >= yLims[0] && v.y <= yLims[1]);
    }

    public float[] getViewportBox() {
        float[] xLims = getWindowLimitsX();
        float[] yLims = getWindowLimitsY();
        PVector v1 = window2viewport(xLims[0], yLims[0]);
        PVector v2 = window2viewport(xLims[1], yLims[1]);
        return new float[]{v1.x, v2.y, v2.x - v1.x, v1.y - v2.y};
    }
}
