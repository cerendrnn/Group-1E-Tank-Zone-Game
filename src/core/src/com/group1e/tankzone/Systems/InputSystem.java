package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;


public class InputSystem implements EntitySystem {
    private static final float VELOCITY = 100;
    private static final float ANGULAR_VELOCITY = 100;

    @Override
    public void update(World world) {
        for (Entity entity : world.getEntities()) {
            PlayerComponent playerComponent = (PlayerComponent)entity.getComponent(PlayerComponent.class);
            AngleComponent angleComponent = (AngleComponent)entity.getComponent(AngleComponent.class);
            VelocityComponent velocityComponent = (VelocityComponent)entity.getComponent(VelocityComponent.class);
            TargetComponent targetComponent = (TargetComponent)entity.getComponent(TargetComponent.class);

            if (playerComponent == null || angleComponent == null)
                continue;

            float deltaTime = Gdx.graphics.getDeltaTime();

            switch (playerComponent.type) {
                case STEERABLE:
                    if (velocityComponent == null) {
                        throw new RuntimeException("InputSystem: Entity should have a VelocityComponent!");
                    }

                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        angleComponent.angle += ANGULAR_VELOCITY * deltaTime;
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        angleComponent.angle -= ANGULAR_VELOCITY * deltaTime;
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        velocityComponent.velocity += VELOCITY * deltaTime;
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                        velocityComponent.velocity -= VELOCITY * deltaTime;
                    }
                    break;
                case ROTATABLE:
                    float origin_x = Gdx.input.getX();
                    // For some reason LibGDX has different coordinate system for cursor and the game
                    // We have to convert it to the game's version by subtracting it from the height
                    float origin_y = Gdx.graphics.getHeight() - Gdx.input.getY();

                    float target_x = targetComponent.targetPosition.x;
                    float target_y = targetComponent.targetPosition.y;

                    angleComponent.angle = Util.getAngleBetweenTwoPoints(origin_x, origin_y, target_x, target_y);
                    break;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
