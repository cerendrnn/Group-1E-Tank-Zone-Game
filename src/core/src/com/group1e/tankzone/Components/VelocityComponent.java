package com.group1e.tankzone.Components;

public class VelocityComponent implements Component {
    public float velocity;

    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }

    public VelocityComponent() {
        this(0);
    }
}
