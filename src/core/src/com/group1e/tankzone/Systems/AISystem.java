package com.group1e.tankzone.Systems;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.TankBarrel;
import com.group1e.tankzone.Entities.TankBody;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Systems.AI.AIMovementStrategy;
import com.group1e.tankzone.Systems.AI.AIShootStrategy;
import com.group1e.tankzone.Systems.AI.AITargetStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class AISystem implements EntitySystem {
    private static final float MAX_VELOCITY = 100 * 3;
    private AITargetStrategy aiTargetStrategy;
    private AIShootStrategy aiShootStrategy;
    private AIMovementStrategy aiMovementStrategy;

    private Entity player = null;
    private ObjectMap<String, Array<TankBarrel>> friendlyMap = new ObjectMap<String, Array<TankBarrel>>();
    private ObjectMap<String, Array<TankBody>> enemyMap = new ObjectMap<String, Array<TankBody>>();

    public AISystem(String[] factions, AITargetStrategy aiTargetStrategy, AIShootStrategy aiShootStrategy, AIMovementStrategy aiMovementStrategy) {
        this.aiTargetStrategy = aiTargetStrategy;
        this.aiShootStrategy = aiShootStrategy;
        this.aiMovementStrategy = aiMovementStrategy;

        for (String faction : factions) {
            friendlyMap.put(faction, new Array<TankBarrel>());
            enemyMap.put(faction, new Array<TankBody>());
        }
    }

    @Override
    public void entityUpdated(Entity entity, boolean added) {
        if (!(entity instanceof TankBarrel))
            return;

        PlayerComponent playerComponent = entity.getComponent(PlayerComponent.class);
        if (playerComponent != null) {
            player = entity;
        }

        FactionComponent factionComponent = entity.getComponent(FactionComponent.class);
        if (factionComponent == null) {
            return;
        }

        String faction = factionComponent.color;

        Array<TankBarrel> friendlyArray = friendlyMap.get(faction);
        if (added) friendlyArray.add((TankBarrel) entity);
        else       friendlyArray.removeValue((TankBarrel) entity, true);

        for (String enemyFaction : friendlyMap.keys()) {
            if (enemyFaction.equals(faction))
                continue;

            Array<TankBody> enemyArray = enemyMap.get(enemyFaction);
            TankBody entityBody = (TankBody) entity.getComponent(TargetComponent.class).target;

            if (added) enemyArray.add(entityBody);
            else       enemyArray.removeValue(entityBody, true);
        }
    }

    @Override
    public void update() {
        for (ObjectMap.Entry<String, Array<TankBarrel>> entry : friendlyMap) {
            String aiFaction = entry.key;
            Array<TankBarrel> aiList = entry.value;

            for (int i = 0; i < aiList.size; ++i) {
                TankBarrel aiBarrel = aiList.get(i);

                if (aiBarrel == player) continue;

                TankBody aiBody = (TankBody) aiBarrel.getComponent(TargetComponent.class).target;
                Array<TankBody> aiEnemyList = enemyMap.get(aiFaction);

                TankBody target = aiTargetStrategy.decideEnemyToAttack(aiBody, aiEnemyList);
                aiShootStrategy.shoot(aiBarrel, target, aiList);

                aiMovementStrategy.moveTo(aiBody, target);

                VelocityComponent aiVelocity = aiBody.getComponent(VelocityComponent.class);
                aiVelocity.velocity = Math.min(MAX_VELOCITY, aiVelocity.velocity);
            }
        }
    }
}
