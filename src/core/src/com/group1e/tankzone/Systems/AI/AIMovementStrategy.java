package com.group1e.tankzone.Systems.AI;

import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;

public interface AIMovementStrategy {
    void moveTo(World world, TankBody ai, TankBody enemy);
}
