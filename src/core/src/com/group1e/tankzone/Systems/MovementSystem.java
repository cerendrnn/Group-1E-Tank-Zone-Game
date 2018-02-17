package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PlayerComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.VelocityComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;

public class MovementSystem implements EntitySystem {
    @Override
    public void update(World world) {
        for (Entity entity : world.getEntities()) {
            PositionComponent positionComponent = (PositionComponent)entity.getComponent(PositionComponent.class);
            VelocityComponent velocityComponent = (VelocityComponent)entity.getComponent(VelocityComponent.class);
            AngleComponent angleComponent = (AngleComponent)entity.getComponent(AngleComponent.class);

            if (positionComponent == null || angleComponent == null || velocityComponent == null)
                continue;

            float deltaTime = Gdx.graphics.getDeltaTime();

            float delta_x = velocityComponent.velocity * MathUtils.cosDeg(angleComponent.angle);
            float delta_y = velocityComponent.velocity * MathUtils.sinDeg(angleComponent.angle);

            positionComponent.x += delta_x * deltaTime;
            positionComponent.y += delta_y * deltaTime;
        }
    }

    @Override
    public void dispose() {

    }
}
