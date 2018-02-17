package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.GraphicsComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;

public class GraphicsSystem implements EntitySystem {
    private SpriteBatch batch = new SpriteBatch();

    @Override
    public void update(World world) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (Entity entity : world.getEntities()) {
            GraphicsComponent graphicsComponent = (GraphicsComponent)entity.getComponent(GraphicsComponent.class);
            PositionComponent positionComponent = (PositionComponent)entity.getComponent(PositionComponent.class);
            TargetComponent targetComponent = (TargetComponent)entity.getComponent(TargetComponent.class);
            AngleComponent angleComponent = (AngleComponent)entity.getComponent(AngleComponent.class);

            if (graphicsComponent == null)
                continue;

            Texture texture = graphicsComponent.texture;

            float x = 0;
            float y = 0;
            if (positionComponent != null) {
                x = positionComponent.x;
                y = positionComponent.y;
            } else if (targetComponent != null) {
                x = targetComponent.targetPosition.x;
                y = targetComponent.targetPosition.y;
            } else {
                throw new RuntimeException("GraphicsSystem: Could not get position of the sprite!");
            }

            float angle = 0;
            if (angleComponent != null) {
                angle = angleComponent.angle;
            }

            batch.draw(
                    texture,
                    x - texture.getWidth() / 2f,
                    y - texture.getHeight() / 2f,
                    texture.getWidth() / 2f,
                    texture.getHeight() / 2f,
                    texture.getWidth(),
                    texture.getHeight(),
                    1,
                    1,
                    angle,
                    0,
                    0,
                    texture.getWidth(),
                    texture.getHeight(),
                    false,
                    false
            );

        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
