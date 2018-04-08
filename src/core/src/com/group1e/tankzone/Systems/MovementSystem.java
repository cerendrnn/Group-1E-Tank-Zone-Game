package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PlayerComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.VelocityComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;

public class MovementSystem implements EntitySystem {
    private Array<Entity> entitiesWithVelocity = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
        AngleComponent angleComponent = entity.getComponent(AngleComponent.class);

        if (positionComponent == null || angleComponent == null || velocityComponent == null)
            return;

        if (added) entitiesWithVelocity.add(entity);
        else       entitiesWithVelocity.removeValue(entity, true);
    }

    @Override
    public void update() {
        for (Entity entity : entitiesWithVelocity) {
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
            AngleComponent angleComponent = entity.getComponent(AngleComponent.class);

            float deltaTime = Gdx.graphics.getDeltaTime();

            float delta_x = velocityComponent.velocity * MathUtils.cosDeg(angleComponent.angle);
            float delta_y = velocityComponent.velocity * MathUtils.sinDeg(angleComponent.angle);

            positionComponent.x += delta_x * deltaTime;
            positionComponent.y += delta_y * deltaTime;
        }
    }
}
