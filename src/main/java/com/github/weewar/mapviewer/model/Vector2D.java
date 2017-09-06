package com.github.weewar.mapviewer.model;

public class Vector2D<T extends Number> {
    protected T x, y;

    public Vector2D() {
    }

    public Vector2D(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public Vector2D.Mutable<T> mutable() {
        return new Vector2D.Mutable<>(x, y);
    }

    public static class Mutable<T extends Number> extends Vector2D<T> {
        public Mutable() {
        }

        public Mutable(T x, T y) {
            super(x, y);
        }

        public Mutable setX(T x) {
            this.x = x;
            return this;
        }

        public Mutable setY(T y) {
            this.y = y;
            return this;
        }

        public Vector2D<T> immutable() {
            return new Vector2D<>(x, y);
        }
    }
}
