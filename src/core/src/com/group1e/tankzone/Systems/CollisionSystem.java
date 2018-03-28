package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;
import com.sun.glass.ui.Size;
import javafx.geometry.Pos;

public class CollisionSystem implements EntitySystem {
    @Override
    public void update(World world) {
        for (Entity e1 : world.getEntities()) {
            PositionComponent pos1    = e1.getComponent(PositionComponent.class);
            SizeComponent     size1   = e1.getComponent(SizeComponent.class);
            DamageComponent   damage1 = e1.getComponent(DamageComponent.class);

            if (pos1 == null || size1 == null || damage1 == null)
                continue;

            for (int i = 0; i < world.getEntities().size; ++i) {
                Entity e2 = world.getEntities().get(i);

                PositionComponent pos2    = e2.getComponent(PositionComponent.class);
                SizeComponent     size2   = e2.getComponent(SizeComponent.class);
                HealthComponent   health2 = e2.getComponent(HealthComponent.class);

                if (pos2 == null || size2 == null || health2 == null)
                    continue;

                float distance = Util.getDistanceBetweenTwoPoints(pos1.x, pos1.y, pos2.x, pos2.y);

                if (distance <= size1.size + size2.size) {
                    world.getEntities().removeValue(e1, true);

                    health2.health -= damage1.damage;
                }
            }
        }
    }

    @Override
    public void dispose() {

    }
}
