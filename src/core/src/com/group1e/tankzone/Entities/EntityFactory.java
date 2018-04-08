package com.group1e.tankzone.Entities;

import com.badlogic.gdx.Gdx;
import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.HealthComponent;
import com.group1e.tankzone.Components.PlayerComponent;
import com.group1e.tankzone.Components.VelocityComponent;
import com.group1e.tankzone.Managers.Engine;
import com.group1e.tankzone.Managers.World;

public class EntityFactory {
    private static Engine getEngine() {
        return World.getInstance().getEngine();
    }

    public static void createTank(String faction, float pos_x, float pos_y, float velocity) {
        TankBody tankBody = new TankBody(faction, pos_x, pos_y, velocity);
        TankBarrel tankBarrel = new TankBarrel(tankBody);

        getEngine().addEntity(tankBody);
        getEngine().addEntity(tankBarrel);
    }

    public static void createPlayer(String faction, float pos_x, float pos_y, float velocity) {
        TankBody tankBody = new TankBody(faction, pos_x, pos_y, velocity);
        tankBody.getComponent(HealthComponent.class).health += 500;

        TankBarrel tankBarrel = new TankBarrel(tankBody);

        tankBody.addComponent(new PlayerComponent(PlayerComponent.Type.STEERABLE));
        tankBarrel.addComponent(new PlayerComponent(PlayerComponent.Type.ROTATABLE));

        getEngine().addEntity(tankBody);
        getEngine().addEntity(tankBarrel);
    }

    public static void createBullet(TankBarrel tankBarrel, float velocity) {
        Bullet bullet = new Bullet(tankBarrel, velocity);

        getEngine().addEntity(bullet);
    }

}
