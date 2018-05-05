package com.group1e.tankzone.Systems.AI;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;

public interface AIShootStrategy {
    void shoot(TankBarrel ai, TankBody enemy, Array<TankBarrel> friendlies);
}
