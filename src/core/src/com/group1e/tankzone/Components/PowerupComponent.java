package com.group1e.tankzone.Components;

public class PowerupComponent implements Component {
    public enum Type {
        BLACK_HOLE
    }

    public Type type;
    public PowerupComponent(Type type) {
        this.type = type;
    }
}
