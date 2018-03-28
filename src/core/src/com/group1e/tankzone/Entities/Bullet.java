package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Utils.Util;
import javafx.geometry.Pos;

import static com.badlogic.gdx.math.MathUtils.random;

public class Bullet extends Entity {
    public Bullet(TankBarrel tankBarrel, float velocity) {
        String faction = tankBarrel.getComponent(FactionComponent.class).color;
        faction = Character.toUpperCase(faction.charAt(0)) + faction.substring(1);

        Texture texture = new Texture("bullet" + faction + "3_outline.png");

        float angle = tankBarrel.getComponent(AngleComponent.class).angle;
        float size = (texture.getHeight() + texture.getWidth()) / 4.0f;

        Entity target = tankBarrel.getComponent(TargetComponent.class).target;
        PositionComponent pos = target.getComponent(PositionComponent.class);

        Texture barrelTexture = tankBarrel.getComponent(GraphicsComponent.class).texture;
        float barrelLength = barrelTexture.getHeight();

        Vector2 addedPosition = Util.createVector(barrelLength, angle);
        Vector2 newPos = new Vector2(pos.x, pos.y).add(addedPosition);

        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new AngleComponent(angle));
        this.addComponent(new VelocityComponent(velocity));
        this.addComponent(new PositionComponent(newPos.x, newPos.y));
        this.addComponent(new DamageComponent(50));
        this.addComponent(new SizeComponent(size));

    }
}
