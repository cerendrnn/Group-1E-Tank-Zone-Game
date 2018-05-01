package com.group1e.tankzone.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.*;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Utils.Util;


public class SpawnSystem implements EntitySystem {

    private Array<Entity> entitiesWithSpawn = new Array<Entity>();
    private Array<Entity> entitiesWithSize = new Array<Entity>();
    private Array<Entity> entitiesWithFaction = new Array<Entity>();

    public void entityUpdated(Entity entity,boolean added){
        SpawnComponent spawnComponent = entity.getComponent(SpawnComponent.class);
        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);
        FactionComponent factionComponent = entity.getComponent(FactionComponent.class);
        if(spawnComponent == null)
            return;

        if(added) entitiesWithSpawn.add(entity);
        else       entitiesWithSpawn.removeValue(entity, true);

        if (sizeComponent != null) {
            if (added) entitiesWithSize.add(entity);
            else       entitiesWithSize.removeValue(entity, true);
        }
        if (factionComponent != null) {
            if (added) entitiesWithFaction.add(entity);
            else       entitiesWithFaction.removeValue(entity, true);
        }

    }

    public void update(){

        for (Entity entity : entitiesWithSpawn) {
            FactionComponent factionComponent = entity.getComponent(FactionComponent.class);
            SpawnComponent spawnComponent = entity.getComponent(SpawnComponent.class);
            SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

           ///??? for(int i=0; i)
        }
    }

}
