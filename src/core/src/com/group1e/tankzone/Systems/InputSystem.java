package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;
import javafx.geometry.Pos;


public class InputSystem implements EntitySystem {
    private static final float VELOCITY = 100;
    private static final float ANGULAR_VELOCITY = 100;
    private static final float BULLET_VELOCITY = 500;
    private static final float MAX_VELOCITY = VELOCITY * 3;

    private Array<Entity> entitiesWithPlayer = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        PlayerComponent playerComponent = entity.getComponent(PlayerComponent.class);
        if (playerComponent != null) {
            if (added) entitiesWithPlayer.add(entity);
            else       entitiesWithPlayer.removeValue(entity, true);
        }
    }

    @Override
    public void update() {
        for (Entity entity : entitiesWithPlayer) {
            PlayerComponent playerComponent = entity.getComponent(PlayerComponent.class);
            AngleComponent angleComponent = entity.getComponent(AngleComponent.class);
            VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);

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
                    velocityComponent.velocity = Math.min(MAX_VELOCITY, velocityComponent.velocity);

                    break;
                case ROTATABLE:
                    float mouse_x = Gdx.input.getX();
                    // For some reason LibGDX has different coordinate system for cursor and the game
                    // We have to convert it to the game's version by subtracting it from the height
                    float mouse_y = Gdx.graphics.getHeight() - Gdx.input.getY();

                    float center_x = 1920 / 2f;
                    float center_y = 1080 / 2f;

                    angleComponent.angle = Util.getAngleBetweenTwoPoints(mouse_x, mouse_y, center_x, center_y);
                    TankBarrel barrel = (TankBarrel)entity;

                    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && barrel.canShoot()) {
                        EntityFactory.createBullet(barrel, BULLET_VELOCITY);
                        System.out.println(Gdx.graphics.getFramesPerSecond());
                    }

                    break;
            }
        }
    }
}
