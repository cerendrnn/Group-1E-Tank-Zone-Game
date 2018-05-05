package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class Castle extends Entity{

    public Castle(String faction, String climate, float pos_x, float pos_y,float spawnfrequency){

        Texture texture = new Texture("Map/castles/"+ climate + ".png");
        float size = (texture.getHeight() + texture.getWidth()) / 4.0f;

        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new SizeComponent(size));
        this.addComponent(new HealthComponent(500));
        this.addComponent(new SpawnComponent(spawnfrequency));
        this.addComponent(new FactionComponent(faction));
    }
}
