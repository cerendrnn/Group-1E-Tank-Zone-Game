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

public class DeathSystem implements EntitySystem {
    private Array<Entity> entitiesWithHealth = new Array<Entity>();
    private Array<Entity> entitiesWithTargetHealth = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        if (healthComponent != null) {
            if (added) entitiesWithHealth.add(entity);
            else       entitiesWithHealth.removeValue(entity, true);
            return;
        }

        TargetComponent targetComponent = entity.getComponent(TargetComponent.class);
        if (targetComponent != null) {
            Entity targetEntity = targetComponent.target;
            HealthComponent targetHealthComponent = targetEntity.getComponent(HealthComponent.class);
            if (targetHealthComponent != null) {
                if (added) entitiesWithTargetHealth.add(entity);
                else       entitiesWithTargetHealth.removeValue(entity, true);
            }
        }
    }

    @Override
    public void update() {
        for (Entity e : entitiesWithHealth) {
            HealthComponent healthC = e.getComponent(HealthComponent.class);
            TargetComponent targetC = e.getComponent(TargetComponent.class);

            // destroy entities with no health
            if (healthC.health <= 0) {
                World.getInstance().getEngine().removeEntity(e);
            }
        }
        for (Entity e : entitiesWithTargetHealth) {
            // destroy entities with no target alive
            TargetComponent targetComponent = e.getComponent(TargetComponent.class);
            HealthComponent targetHealthComponent = targetComponent.target.getComponent(HealthComponent.class);

            if (targetHealthComponent.health <= 0) {
                World.getInstance().getEngine().removeEntity(e);
            }
        }
    }

}
