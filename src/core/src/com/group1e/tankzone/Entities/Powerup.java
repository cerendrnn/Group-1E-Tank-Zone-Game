package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;
public class Powerup {

    public enum Type {
        AMMO_PACK,
        HEALTH_PACK,
        MIND_CONTROL,
        TIME_FREEZER,
        LASER
    }
    
    public Powerup(Type type, float pos_x, float pos_y, float spawnFrequency){

        //bir tek health-pack için bulabildim resim :( sitedeki öbür resimler çok alakasız

        Texture texture = new Texture("type_" + type + ".png");
        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new SpawnComponent(spawnFrequency));


    }
}
