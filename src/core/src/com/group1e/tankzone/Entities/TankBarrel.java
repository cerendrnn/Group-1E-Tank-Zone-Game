package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class TankBarrel extends Entity {
    private long lastTimeABulletShot = System.currentTimeMillis();

    public TankBarrel(TankBody tankBody) {
        String faction = tankBody.getComponent(FactionComponent.class).color;
        String factionName = Character.toUpperCase(faction.charAt(0)) + faction.substring(1);

        Texture texture = new Texture("tank" + factionName + "_barrel3_outline.png");
        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new TargetComponent(tankBody));
        this.addComponent(new AngleComponent(random(0, 360)));
        this.addComponent(new FactionComponent(faction));
    }

    public boolean canShoot() {
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - lastTimeABulletShot;

        if (diff > 1000) {
            lastTimeABulletShot = currentTime;
            return true;
        } else {
            return false;
        }
    }
}
