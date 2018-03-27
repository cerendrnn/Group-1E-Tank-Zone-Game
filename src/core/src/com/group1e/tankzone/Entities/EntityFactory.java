package com.group1e.tankzone.Entities;

import com.group1e.tankzone.Components.AngleComponent;
import com.group1e.tankzone.Components.PlayerComponent;
import com.group1e.tankzone.Components.VelocityComponent;
import com.group1e.tankzone.Managers.World;

public class EntityFactory {

    public static void createTank(World world, String faction, float pos_x, float pos_y, float velocity) {
        TankBody tankBody = new TankBody(faction, pos_x, pos_y, velocity);
        TankBarrel tankBarrel = new TankBarrel(tankBody);

        world.getEntities().add(tankBody);
        world.getEntities().add(tankBarrel);
    }

    public static void createPlayer(World world, String faction, float pos_x, float pos_y, float velocity) {
        TankBody tankBody = new TankBody(faction, pos_x, pos_y, velocity);
        TankBarrel tankBarrel = new TankBarrel(tankBody);

        tankBody.addComponent(new PlayerComponent(PlayerComponent.Type.STEERABLE));
        tankBarrel.addComponent(new PlayerComponent(PlayerComponent.Type.ROTATABLE));

        world.getEntities().add(tankBody);
        world.getEntities().add(tankBarrel);

    }
}
