package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.GraphicsComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import javafx.geometry.Pos;

public class GraphicsSystem implements EntitySystem {
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera camera = new OrthographicCamera(1920, 1080);
    Texture grass = new Texture("Map/grass/mapTile_022.png");
    Texture dirt = new Texture("Map/dirt/mapTile_082.png");

    private Array<Entity> entitiesWithTexture = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        GraphicsComponent graphicsComponent = entity.getComponent(GraphicsComponent.class);
        if (graphicsComponent != null) {
            if (added) entitiesWithTexture.add(entity);
            else       entitiesWithTexture.removeValue(entity, true);
        }
    }

    @Override
    public void update() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        PositionComponent camPos = World.getInstance().getCameraTarget();

        camera.position.set(camPos.x, camPos.y, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        int map[][] = World.getInstance().getMap();
        int width = map.length;
        int height = map[0].length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Texture toDraw = map[x][y] == 0 ? grass : dirt;
                batch.draw(toDraw, x * 16, y * 16);
            }
        }

        for (Entity entity : entitiesWithTexture) {
            GraphicsComponent graphicsComponent = entity.getComponent(GraphicsComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            TargetComponent targetComponent = entity.getComponent(TargetComponent.class);
            AngleComponent angleComponent = entity.getComponent(AngleComponent.class);

            Texture texture = graphicsComponent.texture;

            float x = 0;
            float y = 0;
            if (positionComponent != null) {
                x = positionComponent.x;
                y = positionComponent.y;
            } else if (targetComponent != null) {
                PositionComponent targetPos = targetComponent.target.getComponent(PositionComponent.class);
                x = targetPos.x;
                y = targetPos.y;
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
                    angle + 270, // rotate textures so that textures in assets folder will look upwards
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

}
