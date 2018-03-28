package com.group1e.tankzone.Systems;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.FactionComponent;
import com.group1e.tankzone.Components.PlayerComponent;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Components.TargetComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Systems.AI.AIMovementStrategy;
import com.group1e.tankzone.Systems.AI.AIShootStrategy;
import com.group1e.tankzone.Systems.AI.AITargetStrategy;

public class AISystem implements EntitySystem {
    AITargetStrategy aiTargetStrategy;
    AIShootStrategy aiShootStrategy;
    AIMovementStrategy aiMovementStrategy;

    public AISystem(AITargetStrategy aiTargetStrategy, AIShootStrategy aiShootStrategy, AIMovementStrategy aiMovementStrategy) {
        this.aiTargetStrategy = aiTargetStrategy;
        this.aiShootStrategy = aiShootStrategy;
        this.aiMovementStrategy = aiMovementStrategy;
    }

    @Override
    public void update(World world) {
        for (int i = 0; i < world.getEntities().size; ++i) {
            Entity ai = world.getEntities().get(i);
            PlayerComponent playerComponent = ai.getComponent(PlayerComponent.class);

            if (!(ai instanceof TankBarrel) || playerComponent != null)
                continue;

            TankBarrel aiBarrel = (TankBarrel) ai;
            TankBody aiBody = (TankBody) ai.getComponent(TargetComponent.class).target;

            String faction = ai.getComponent(FactionComponent.class).color;

            Array<TankBody> enemies = getEnemiesFor(world, faction);
            TankBody target = aiTargetStrategy.decideEnemyToAttack(aiBody, enemies);

            aiShootStrategy.shoot(world, aiBarrel, target);

            aiMovementStrategy.moveTo(world, aiBody, target);
        }
    }

    @Override
    public void dispose() {

    }

    private Array<TankBody> getEnemiesFor(World world, String faction) {
        Array<TankBody> enemies = new Array<TankBody>();

        for (Entity e : world.getEntities()) {
            FactionComponent factionComponent = e.getComponent(FactionComponent.class);
            PositionComponent positionComponent = e.getComponent(PositionComponent.class);

            if (factionComponent == null || positionComponent == null)
                continue;

            if (!factionComponent.color.equals(faction))
                enemies.add((TankBody) e);
        }

        return enemies;
    }
}
