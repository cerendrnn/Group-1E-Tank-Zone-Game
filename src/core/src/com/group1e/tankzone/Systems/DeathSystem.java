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

public class DeathSystem implements EntitySystem {
    @Override
    public void update(World world) {
        for (Entity e : world.getEntities()) {
            HealthComponent healthC = e.getComponent(HealthComponent.class);
            TargetComponent targetC = e.getComponent(TargetComponent.class);

            // destroy entities with no health
            if (healthC != null && healthC.health <= 0) {
                world.getEntities().removeValue(e, true);
            }
            // destroy entities with no target alive
            if (targetC != null) {
                HealthComponent targetHealthC = targetC.target.getComponent(HealthComponent.class);

                if (targetHealthC.health <= 0) {
                    world.getEntities().removeValue(e, true);
                }
            }
        }
    }

    @Override
    public void dispose() {

    }


}
