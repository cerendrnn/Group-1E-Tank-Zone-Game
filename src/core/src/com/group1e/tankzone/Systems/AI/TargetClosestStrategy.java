package com.group1e.tankzone.Systems.AI;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Utils.Util;

public class TargetClosestStrategy implements AITargetStrategy {
    @Override
    public TankBody decideEnemyToAttack(TankBody ai, Array<TankBody> enemies) {
        PositionComponent aiPos = ai.getComponent(PositionComponent.class);

        TankBody closest = null;
        float minDistance = Float.MAX_VALUE;
        for (TankBody enemy : enemies) {
            PositionComponent enemyPos = enemy.getComponent(PositionComponent.class);

            float distance = Util.getDistance2BetweenTwoPoints(aiPos.x, aiPos.y, enemyPos.x, enemyPos.y);
            if (distance < minDistance) {
                closest = enemy;
                minDistance = distance;
            }
        }

        return closest;
    }
}
