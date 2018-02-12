package com.group1e.tankzone.Components;

public class TargetComponent implements Component {
    public PositionComponent targetPosition;

    public TargetComponent(PositionComponent targetPosition) {
        this.targetPosition = targetPosition;
    }
}
