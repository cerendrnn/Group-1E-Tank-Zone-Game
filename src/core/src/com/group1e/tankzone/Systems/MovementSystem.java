package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

public class MovementSystem implements EntitySystem {
    private Array<Entity> entitiesWithVelocity = new Array<Entity>();
    private Array<Entity> entitiesWithSize = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
        AngleComponent angleComponent = entity.getComponent(AngleComponent.class);
        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

        if (positionComponent == null || angleComponent == null || velocityComponent == null)
            return;

        if (added) entitiesWithVelocity.add(entity);
        else       entitiesWithVelocity.removeValue(entity, true);

        if (sizeComponent != null) {
            if (added) entitiesWithSize.add(entity);
            else       entitiesWithSize.removeValue(entity, true);
        }
    }

    @Override
    public void update() {
        for (Entity entity : entitiesWithVelocity) {
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
            AngleComponent angleComponent = entity.getComponent(AngleComponent.class);
            SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

            float deltaTime = Gdx.graphics.getDeltaTime();

            float delta_x = velocityComponent.velocity * MathUtils.cosDeg(angleComponent.angle);
            float delta_y = velocityComponent.velocity * MathUtils.sinDeg(angleComponent.angle);

            float old_x = positionComponent.x;
            float old_y = positionComponent.y;

            float new_x = old_x + delta_x * deltaTime;
            float new_y = old_y + delta_y * deltaTime;

            if (sizeComponent != null) {
                if (collidesWithOthers(entity, new_x, new_y, sizeComponent.size)) {
                    velocityComponent.velocity = 0;
                    continue;
                }
            }

            positionComponent.x = new_x;
            positionComponent.y = new_y;
        }
    }

    private boolean collidesWithOthers(Entity entity, float pos_x, float pos_y, float size) {
        DamageComponent damageComponent = entity.getComponent(DamageComponent.class);
        if (damageComponent != null && damageComponent.disposable)
            return false;

        for (Entity e : entitiesWithSize) {
            if (e == entity) continue;
            damageComponent = e.getComponent(DamageComponent.class);
            if (damageComponent != null && damageComponent.disposable)
                continue;

            SizeComponent sizeComponent = e.getComponent(SizeComponent.class);
            PositionComponent positionComponent = e.getComponent(PositionComponent.class);

            float dist = Util.getDistanceBetweenTwoPoints(pos_x, pos_y, positionComponent.x, positionComponent.y);
            if (dist <= size + sizeComponent.size) {
                return true;
            }
        }
        return false;
    }
}
