package com.group1e.tankzone.Systems.AI;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;

public interface AITargetStrategy {
    TankBody decideEnemyToAttack(TankBody ai, Array<TankBody> enemies);
}
