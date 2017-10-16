package com.company;

public class Point3D {
    public float x;
    public float y;
    public float z;

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return Math.round(x);
    }

    public int getY() {
        return Math.round(y);
    }

    public int getZ() {
        return Math.round(z);
    }

}
