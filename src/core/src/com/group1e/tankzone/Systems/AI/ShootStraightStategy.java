package com.group1e.tankzone.Systems.AI;

import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

public class ShootStraightStategy implements AIShootStrategy {

    @Override
    public void shoot(World world, TankBarrel ai, TankBody enemy) {
        if (!ai.canShoot())
            return;

        Entity target = ai.getComponent(TargetComponent.class).target;
        PositionComponent aiPos = target.getComponent(PositionComponent.class);
        PositionComponent enemyPos = enemy.getComponent(PositionComponent.class);

        float shootAngle = Util.getAngleBetweenTwoPoints(enemyPos.x, enemyPos.y, aiPos.x, aiPos.y);
        ai.getComponent(AngleComponent.class).angle = shootAngle;

        EntityFactory.createBullet(world, ai, 500);
    }
}
