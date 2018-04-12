package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.GraphicsComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.MapGenerator;
import com.group1e.tankzone.Managers.World;

import static com.group1e.tankzone.Managers.MapGenerator.Tile;

public class GraphicsSystem implements EntitySystem {
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera camera = new OrthographicCamera(1920, 1080);

    ObjectMap<Tile, Texture> textureMap = new ObjectMap<Tile, Texture>();
    Texture dirt = new Texture("Map/dirt/mapTile_082.png");

    private Array<Entity> entitiesWithTexture = new Array<Entity>();

    public GraphicsSystem() {
        Texture grassTopleft = new Texture("Map/grass/topleft.png");
        Texture grassTop = new Texture("Map/grass/top.png");
        Texture grassTopright = new Texture("Map/grass/topright.png");
        Texture grassRight = new Texture("Map/grass/right.png");
        Texture grassBottomright = new Texture("Map/grass/bottomright.png");
        Texture grassBottom = new Texture("Map/grass/bottom.png");
        Texture grassBottomleft = new Texture("Map/grass/bottomleft.png");
        Texture grassLeft = new Texture("Map/grass/left.png");
        Texture grassCenter = new Texture("Map/grass/center.png");

        textureMap.put(Tile.DIRT, dirt);
        textureMap.put(Tile.GRASS_CENTER, grassCenter);
        textureMap.put(Tile.GRASS_LEFT, grassLeft);
        textureMap.put(Tile.GRASS_RIGHT, grassRight);
        textureMap.put(Tile.GRASS_TOP, grassTop);
        textureMap.put(Tile.GRASS_BOTTOM, grassBottom);
        textureMap.put(Tile.GRASS_TOP_LEFT, grassTopleft);
        textureMap.put(Tile.GRASS_TOP_RIGHT, grassTopright);
        textureMap.put(Tile.GRASS_BOTTOM_LEFT, grassBottomleft);
        textureMap.put(Tile.GRASS_BOTTOM_RIGHT, grassBottomright);

        dirt.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
    }

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
        Tile map[][] = World.getInstance().getMap();
        int width = map.length;
        int height = map[0].length;

        float cam_x = camPos.x;
        float cam_y = camPos.y;

        cam_x = Math.max(cam_x, camera.viewportWidth / 2f);
        cam_y = Math.max(cam_y, camera.viewportHeight / 2f);

        cam_x = Math.min(cam_x, width * 16);
        cam_y = Math.min(cam_y, height * 16);
        

        camera.position.set(cam_x, cam_y, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(dirt, 0,0, 0, 0, width * 32, height * 32);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Texture toDraw = map[x][y] == 0 ? grass : dirt;
                    batch.draw(toDraw, x * 32, y * 32);
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
