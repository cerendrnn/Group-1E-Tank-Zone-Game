package com.group1e.tankzone.Systems;

import com.group1e.tankzone.Managers.World;

public interface EntitySystem {
    void update(World world);
    void dispose();
}
