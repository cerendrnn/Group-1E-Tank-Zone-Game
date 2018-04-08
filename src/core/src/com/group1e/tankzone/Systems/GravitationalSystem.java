package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

import java.util.Iterator;

public class GravitationalSystem implements EntitySystem {
    private static final float GRAVITATIONAL_CONSTANT = 6e2f;

    private Array<Entity> entitiesWithMass = new Array<Entity>();
    private Array<Entity> entitiesWithMassAndVelocity = new Array<Entity>();

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        MassComponent massComponent = entity.getComponent(MassComponent.class);
        VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);

        if (massComponent != null && velocityComponent != null) {
            if (added) entitiesWithMassAndVelocity.add(entity);
            else       entitiesWithMassAndVelocity.removeValue(entity, true);
        }

        if (massComponent != null) {
            if (added) entitiesWithMass.add(entity);
            else       entitiesWithMass.removeValue(entity, true);
        }
    }

    @Override
    public void update(){
        for (Entity entity1 : entitiesWithMass) {
            PositionComponent positionComponent1 = entity1.getComponent(PositionComponent.class);
            MassComponent massComponent1 = entity1.getComponent(MassComponent.class);

            for (Entity entity2 : entitiesWithMassAndVelocity) {
                PositionComponent positionComponent2 = entity2.getComponent(PositionComponent.class);
                MassComponent massComponent2 = entity2.getComponent(MassComponent.class);
                VelocityComponent velocityComponent = entity2.getComponent(VelocityComponent.class);
                AngleComponent angleComponent = entity2.getComponent(AngleComponent.class);

                float angle = Util.getAngleBetweenTwoPoints(
                        positionComponent2.x,
                        positionComponent2.y,
                        positionComponent1.x,
                        positionComponent1.y
                );
                float distance2 = Util.getDistance2BetweenTwoPoints(
                        positionComponent2.x,
                        positionComponent2.y,
                        positionComponent1.x,
                        positionComponent1.y
                );

                if (distance2 < 1000)
                    continue;

                float acceleration = GRAVITATIONAL_CONSTANT * massComponent1.mass * Gdx.graphics.getDeltaTime();
                acceleration /= distance2;

                Vector2 accVector = Util.createVector(acceleration, angle);
                Vector2 velVector = Util.createVector(velocityComponent.velocity, angleComponent.angle);

                Vector2 newVelVector = velVector.sub(accVector);

                velocityComponent.velocity = newVelVector.len();
                angleComponent.angle = newVelVector.angle();
            }
        }
    }
}
