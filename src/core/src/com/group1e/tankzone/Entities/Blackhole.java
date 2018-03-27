package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class Blackhole extends Entity {
    public Blackhole(float pos_x, float pos_y, float mass) {
        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new MassComponent(mass));
        this.addComponent(new PowerupComponent(PowerupComponent.Type.BLACK_HOLE));
    }
}
