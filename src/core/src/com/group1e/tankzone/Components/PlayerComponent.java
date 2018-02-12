package com.group1e.tankzone.Components;

public class PlayerComponent implements Component {
    public enum Type {
        STEERABLE,
        ROTATABLE
    }

    public Type type;
    public PlayerComponent(Type type) {
        this.type = type;
    }
}
