package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;

public class PhysicsSystem implements EntitySystem {
    private static final float GRAVITATIONAL_CONSTANT = 6e2f;

    @Override
    public void update(World world) {
        for (Entity entity1 : world.getEntities()) {
            PositionComponent positionComponent1 = entity1.getComponent(PositionComponent.class);
            MassComponent massComponent1 = entity1.getComponent(MassComponent.class);

            if (positionComponent1 == null || massComponent1 == null)
                continue;

            for (int i = 0; i < world.getEntities().size; ++i) {
                Entity entity2 = world.getEntities().get(i);

                PositionComponent positionComponent2 = entity2.getComponent(PositionComponent.class);
                MassComponent massComponent2 = entity2.getComponent(MassComponent.class);
                VelocityComponent velocityComponent = entity2.getComponent(VelocityComponent.class);
                AngleComponent angleComponent = entity2.getComponent(AngleComponent.class);

                if (positionComponent2 == null || massComponent2 == null || velocityComponent == null || angleComponent == null)
                    continue;

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

                Vector2 accVector = new Vector2(1, 0); // unit vector
                accVector.setLength(acceleration);
                accVector.setAngle(angle);

                Vector2 velVector = new Vector2(1, 0);
                velVector.setLength(velocityComponent.velocity);
                velVector.setAngle(angleComponent.angle);

                Vector2 newVelVector = velVector.sub(accVector);

                velocityComponent.velocity = newVelVector.len();
                angleComponent.angle = newVelVector.angle();
            }
        }
    }

    @Override
    public void dispose() {

    }
}
