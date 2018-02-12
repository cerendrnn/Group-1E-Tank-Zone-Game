package com.group1e.tankzone.Components;

public class AngleComponent implements Component {
    public float angle;

    public AngleComponent(float angle) {
        this.angle = angle;
    }

    public AngleComponent() {
        this(0);
    }
}
