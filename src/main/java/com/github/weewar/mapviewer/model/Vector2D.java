package com.github.weewar.mapviewer.model;

public class Vector2D<T extends Number> {
    protected T x, y;

    public Vector2D() {
    }

    public Vector2D(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }
}
