package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.GameType;
import com.group1e.tankzone.Managers.MapGenerator;
import com.group1e.tankzone.Managers.World;

import static com.group1e.tankzone.Managers.MapGenerator.Tile;

public class GraphicsSystem implements EntitySystem {
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private OrthographicCamera camera = new OrthographicCamera(1920, 1080);

    ObjectMap<Tile, Texture> textureMap = new ObjectMap<Tile, Texture>();
    Texture dirt = new Texture("Map/dirt/mapTile_082.png");
    Texture snow = new Texture("Map/mapTile_188.png");
    Texture sand = new Texture("Map/sand/mapTile_017.png");

    private Array<Entity> entitiesWithTexture = new Array<Entity>();

    public GraphicsSystem() {

        if (GameType.climate == GameType.Climate.WINTER) {
            Texture snowTopleft = new Texture("Map/snow/mapTile_061.png");
            Texture snowTop = new Texture("Map/snow/mapTile_062.png");
            Texture snowTopright = new Texture("Map/snow/mapTile_063.png");
            Texture snowRight = new Texture("Map/snow/mapTile_078.png");
            Texture snowBottomright = new Texture("Map/snow/mapTile_093.png");
            Texture snowBottom = new Texture("Map/snow/mapTile_092.png");
            Texture snowBottomleft = new Texture("Map/snow/mapTile_091.png");
            Texture snowLeft = new Texture("Map/snow/mapTile_076.png");
            Texture snowCenter = new Texture("Map/snow/mapTile_077.png");

            textureMap.put(Tile.DIRT, snow);
            textureMap.put(Tile.GRASS_CENTER, snowCenter);
            textureMap.put(Tile.GRASS_LEFT, snowLeft);
            textureMap.put(Tile.GRASS_RIGHT, snowRight);
            textureMap.put(Tile.GRASS_TOP, snowTop);
            textureMap.put(Tile.GRASS_BOTTOM, snowBottom);
            textureMap.put(Tile.GRASS_TOP_LEFT, snowTopleft);
            textureMap.put(Tile.GRASS_TOP_RIGHT, snowTopright);
            textureMap.put(Tile.GRASS_BOTTOM_LEFT, snowBottomleft);
            textureMap.put(Tile.GRASS_BOTTOM_RIGHT, snowBottomright);

            snow.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);
        } else if (GameType.climate == GameType.Climate.DESERT) {

            Texture sandTopleft = new Texture("Map/sand/mapTile_001.png");
            Texture sandTop = new Texture("Map/sand/mapTile_002.png");
            Texture sandTopright = new Texture("Map/sand/mapTile_003.png");
            Texture sandRight = new Texture("Map/sand/mapTile_018.png");
            Texture sandBottomright = new Texture("Map/sand/mapTile_048.png");
            Texture sandBottom = new Texture("Map/sand/mapTile_047.png");
            Texture sandBottomleft = new Texture("Map/sand/mapTile_046.png");
            Texture sandLeft = new Texture("Map/sand/mapTile_016.png");
            Texture sandCenter = new Texture("Map/sand/mapTile_017.png");

            textureMap.put(Tile.DIRT, sand);
            textureMap.put(Tile.GRASS_CENTER, sandCenter);
            textureMap.put(Tile.GRASS_LEFT, sandLeft);
            textureMap.put(Tile.GRASS_RIGHT, sandRight);
            textureMap.put(Tile.GRASS_TOP, sandTop);
            textureMap.put(Tile.GRASS_BOTTOM, sandBottom);
            textureMap.put(Tile.GRASS_TOP_LEFT, sandTopleft);
            textureMap.put(Tile.GRASS_TOP_RIGHT, sandTopright);
            textureMap.put(Tile.GRASS_BOTTOM_LEFT, sandBottomleft);
            textureMap.put(Tile.GRASS_BOTTOM_RIGHT, sandBottomright);

            sand.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);

        } else if (GameType.climate == GameType.Climate.TEMPERATE) {

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

            dirt.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);
        }

        shapeRenderer.setColor(Color.GREEN);
    }

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        GraphicsComponent graphicsComponent = entity.getComponent(GraphicsComponent.class);
        if (graphicsComponent != null) {
            if (added) entitiesWithTexture.add(entity);
            else entitiesWithTexture.removeValue(entity, true);
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

        cam_x = Math.min(cam_x, width * 32 - camera.viewportWidth / 2f);
        cam_y = Math.min(cam_y, height * 32 - camera.viewportHeight / 2f);

        camera.position.set(cam_x, cam_y, 0);
        camera.update();

        float cam_minx = cam_x - camera.viewportWidth / 2f;
        float cam_miny = cam_y - camera.viewportHeight / 2f;
        float cam_maxx = cam_x + camera.viewportWidth / 2f;
        float cam_maxy = cam_y + camera.viewportHeight / 2f;

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();

        if (GameType.climate == GameType.Climate.TEMPERATE)
            batch.draw(dirt, 0, 0, 0, 0, width * 32, height * 32);
        else if (GameType.climate == GameType.Climate.WINTER)
            batch.draw(snow, 0, 0, 0, 0, width * 32, height * 32);
        else if (GameType.climate == GameType.Climate.DESERT)
            batch.draw(sand, 0, 0, 0, 0, width * 32, height * 32);

        // Only draw visible parts within the camera
        int tile_minx = (int) cam_minx / 32;
        int tile_miny = (int) cam_miny / 32;
        int tile_maxx = (int) Math.ceil(cam_maxx) / 32;
        int tile_maxy = (int) Math.ceil(cam_maxy) / 32;

        tile_maxx = Math.min(tile_maxx, width - 1);
        tile_maxy = Math.min(tile_maxy, height - 1);
        for (int x = tile_minx; x <= tile_maxx; x++) {
            for (int y = tile_miny; y <= tile_maxy; y++) {

                if (map[x][y] != Tile.DIRT) {
                    Texture toDraw = textureMap.get(map[x][y]);
                    batch.draw(toDraw, x * 32, y * 32);
                }
            }
        }

        for (Entity entity : entitiesWithTexture) {
            GraphicsComponent graphicsComponent = entity.getComponent(GraphicsComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            TargetComponent targetComponent = entity.getComponent(TargetComponent.class);
            AngleComponent angleComponent = entity.getComponent(AngleComponent.class);

            Texture texture = graphicsComponent.texture;

            float x;
            float y;
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

            // Only draw things visible inside the camera
            if (x + texture.getWidth() / 2f < cam_minx
                    || y + texture.getHeight() / 2f < cam_miny
                    || x - texture.getWidth() / 2f > cam_maxx
                    || y - texture.getHeight() / 2f > cam_maxy)
                continue;

            float angle = 90; // default angle is 90 degrees
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
                    angle - 90, // In LibGDX, 0 degrees means right, but we want it to be up
                    0,
                    0,
                    texture.getWidth(),
                    texture.getHeight(),
                    false,
                    false
            );



        }

        batch.end();


        for (Entity entity : entitiesWithTexture) {
            HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            Texture texture = entity.getComponent(GraphicsComponent.class).texture;

            if (healthComponent != null) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(positionComponent.x - 20, positionComponent.y - texture.getHeight() / 2f - 7, healthComponent.health, 2);
                shapeRenderer.end();
            }
        }

    }

}

