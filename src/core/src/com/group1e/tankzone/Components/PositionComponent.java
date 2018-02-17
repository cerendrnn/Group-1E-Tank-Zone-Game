package com.group1e.tankzone.Components;

public class PositionComponent implements Component {
    public float x;
    public float y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PositionComponent() {
        this(0, 0);
    }
}
