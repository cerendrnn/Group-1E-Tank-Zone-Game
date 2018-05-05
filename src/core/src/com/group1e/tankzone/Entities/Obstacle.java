package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Managers.GameType;

import static com.badlogic.gdx.math.MathUtils.random;

public class Obstacle extends Entity {
    public Obstacle(String type, float pos_x, float pos_y) {
        Texture texture = null;
        if (GameType.climate == GameType.Climate.TEMPERATE) {
            if (type.equals("tree1")) {
                texture = new Texture("Map/mapTile_115.png");
            } else if (type.equals("tree2")) {
                texture = new Texture("Map/mapTile_055.png");
            } else if (type.equals("rock")) {
                texture = new Texture("Map/mapTile_039.png");
            }
        } else if (GameType.climate == GameType.Climate.WINTER) {
            if (type.equals("tree1")) {
                texture = new Texture("Map/mapTile_110.png");
            } else if (type.equals("tree2")) {
                texture = new Texture("Map/mapTile_109.png");
            } else if (type.equals("rock")) {
                texture = new Texture("Map/mapTile_044.png");
            } else if (type.equals("snowman")) {
                texture = new Texture("Map/mapTile_094.png");
            }
        } else {
            if (type.equals("tree1")) {
                texture = new Texture("Map/mapTile_120.png");
            } else if (type.equals("tree2")) {
                texture = new Texture("Map/mapTile_119.png");
            } else if (type.equals("rock")) {
                texture = new Texture("Map/mapTile_049.png");
            }
        }

        float size = (texture.getHeight() + texture.getWidth()) / 4.0f;

        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new SizeComponent(size));
    }
}
