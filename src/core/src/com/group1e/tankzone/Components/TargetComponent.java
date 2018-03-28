package com.group1e.tankzone.Components;

import com.group1e.tankzone.Entities.Entity;

public class TargetComponent implements Component {
    public Entity target;

    public TargetComponent(Entity target) {
        this.target = target;
    }
}
