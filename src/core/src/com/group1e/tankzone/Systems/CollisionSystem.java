package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;
import com.sun.glass.ui.Size;
import javafx.geometry.Pos;

public class CollisionSystem implements EntitySystem {

    private Array<Entity> entitiesWithDamage = new Array<Entity>();
    private Array<Entity> entitiesWithHealth = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);
        DamageComponent damageComponent = entity.getComponent(DamageComponent.class);
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);

        if (positionComponent == null || sizeComponent == null) {
            // This entity cannot collide with anything, collision is undefined
            return;
        }

        if (damageComponent != null) {
            if (added) entitiesWithDamage.add(entity);
            else       entitiesWithDamage.removeValue(entity, true);
        }

        if (healthComponent != null) {
            if (added) entitiesWithHealth.add(entity);
            else       entitiesWithHealth.removeValue(entity, true);
        }
    }

    @Override
    public void update() {
        for (Entity damageEntity : entitiesWithDamage) {
            PositionComponent pos1    = damageEntity.getComponent(PositionComponent.class);
            SizeComponent     size1   = damageEntity.getComponent(SizeComponent.class);
            DamageComponent   damage1 = damageEntity.getComponent(DamageComponent.class);

            for (Entity healthEntity : entitiesWithHealth) {
                PositionComponent pos2    = healthEntity.getComponent(PositionComponent.class);
                SizeComponent     size2   = healthEntity.getComponent(SizeComponent.class);
                HealthComponent   health2 = healthEntity.getComponent(HealthComponent.class);

                float distance = Util.getDistanceBetweenTwoPoints(pos1.x, pos1.y, pos2.x, pos2.y);

                if (distance <= size1.size + size2.size) {
                    if (damage1.disposable) {
                        World.getInstance().getEngine().removeEntity(damageEntity);
                    }

                    health2.health -= damage1.damage;
                }
            }
        }
    }
}
