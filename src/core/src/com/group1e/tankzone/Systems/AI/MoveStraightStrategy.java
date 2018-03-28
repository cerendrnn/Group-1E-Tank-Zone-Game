package com.group1e.tankzone.Systems.AI;

import com.badlogic.gdx.Gdx;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Components.VelocityComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

public class MoveStraightStrategy implements AIMovementStrategy {
    private static final float VELOCITY = 100;
    private static final float ANGULAR_VELOCITY = 100;

    @Override
    public void moveTo(World world, TankBody ai, TankBody enemy){
        PositionComponent aiPos = ai.getComponent(PositionComponent.class);
        PositionComponent enemyPos = enemy.getComponent(PositionComponent.class);

        float distance2 = Util.getDistance2BetweenTwoPoints(aiPos.x, aiPos.y, enemyPos.x, enemyPos.y);

        float currentAngle = ai.getComponent(AngleComponent.class).angle;
        float targetAngle = Util.getAngleBetweenTwoPoints(enemyPos.x, enemyPos.y, aiPos.x, aiPos.y);
        float newAngle = 0;
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (currentAngle > targetAngle) {
            newAngle = Math.min(currentAngle + ANGULAR_VELOCITY * deltaTime, targetAngle);
        } else {
            newAngle = Math.max(currentAngle - ANGULAR_VELOCITY * deltaTime, targetAngle);
        }

        ai.getComponent(AngleComponent.class).angle = newAngle;

        if (Math.abs(newAngle - targetAngle) > 10 || distance2 < 100000) {
            ai.getComponent(VelocityComponent.class).velocity -= VELOCITY * deltaTime;
        } else {
            ai.getComponent(VelocityComponent.class).velocity += VELOCITY * deltaTime;
        }

    }
}
