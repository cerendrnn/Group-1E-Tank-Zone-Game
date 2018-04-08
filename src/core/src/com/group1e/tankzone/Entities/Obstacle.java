package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class Obstacle extends Entity {
    public Obstacle(String type, float pos_x, float pos_y) {
        Texture texture = null;
        if (type.equals("tree1")) {
            texture = new Texture("Map/mapTile_115.png");
        } else if (type.equals("tree2")) {
            texture = new Texture("Map/mapTile_055.png");
        } else if (type.equals("rock")) {
            texture = new Texture("Map/mapTile_039.png");
        }

        float size = (texture.getHeight() + texture.getWidth()) / 4.0f;

        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new SizeComponent(size));
    }
}
