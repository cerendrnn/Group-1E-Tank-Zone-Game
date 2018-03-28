package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class Bullet extends Entity {
    public Bullet(TankBarrel tankBarrel, float velocity) {
        String faction = tankBarrel.getComponent(FactionComponent.class).color;
        faction = Character.toUpperCase(faction.charAt(0)) + faction.substring(1);

        Texture texture = new Texture("bullet" + faction + "3_outline.png");

        float angle = tankBarrel.getComponent(AngleComponent.class).angle;
        Entity target = tankBarrel.getComponent(TargetComponent.class).target;
        PositionComponent pos = target.getComponent(PositionComponent.class);

        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new AngleComponent(angle));
        this.addComponent(new VelocityComponent(velocity));
        this.addComponent(new PositionComponent(pos.x, pos.y));
    }
}
