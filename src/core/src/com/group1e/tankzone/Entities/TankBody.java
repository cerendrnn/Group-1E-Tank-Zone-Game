package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.GraphicsComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.VelocityComponent;

import static com.badlogic.gdx.math.MathUtils.random;

public class TankBody extends Entity {
    public TankBody(String faction, float pos_x, float pos_y, float velocity) {
        Texture texture = new Texture("tankBody_" + faction + ".png");
        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new PositionComponent(pos_x, pos_y));
        this.addComponent(new VelocityComponent(velocity));
        this.addComponent(new AngleComponent(random(0, 360)));
    }
}
