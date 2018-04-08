package com.group1e.tankzone.Systems;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;

public interface EntitySystem {
    void update();

    void entityUpdated(Entity entity, boolean added);
}
