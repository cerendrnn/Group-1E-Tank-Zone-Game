package com.group1e.tankzone.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.group1e.tankzone.Components.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class TankBarrel extends Entity {
    public TankBarrel(TankBody tankBody) {
        String faction = tankBody.getComponent(FactionComponent.class).color;
        faction = Character.toUpperCase(faction.charAt(0)) + faction.substring(1);

        Texture texture = new Texture("tank" + faction + "_barrel3_outline.png");
        this.addComponent(new GraphicsComponent(texture));
        this.addComponent(new TargetComponent(tankBody));
        this.addComponent(new AngleComponent(random(0, 360)));
        this.addComponent(new FactionComponent(faction));
    }
}
