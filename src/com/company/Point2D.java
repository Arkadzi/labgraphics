package com.company;

public class Point2D {
    public float x;
    public float y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return Math.round(x);
    }

    public int getY() {
        return Math.round(y);
    }
}
