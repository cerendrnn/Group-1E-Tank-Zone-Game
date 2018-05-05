package com.group1e.tankzone.Managers;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Systems.EntitySystem;

public class Engine {
    private Array<EntitySystem> systems = new Array<EntitySystem>();
    private Array<Entity> allEntities = new Array<Entity>();


    public Entity getEntity(int index) {
        return allEntities.get(index);
    }

    public Array<Entity> getAllEntities() {
        return allEntities;
    }

    public void addSystem(EntitySystem es) {
        systems.add(es);
    }

    public void update() {
        for (int i = 0; i < systems.size; ++i) {
            systems.get(i).update();
        }
    }

    public void addEntity(Entity e) {
        allEntities.add(e);

        notifySystemsAboutAddedEntity(e);
    }

    private void notifySystemsAboutAddedEntity(Entity addedEntity) {
        for (EntitySystem es : systems) {
            es.entityUpdated(addedEntity, true);
        }
    }

    public void removeEntity(Entity e) {
        allEntities.removeValue(e, true);

        notifySystemsAboutRemovedEntity(e);
    }

    private void notifySystemsAboutRemovedEntity(Entity removedEntity) {
        for (EntitySystem es : systems) {
            es.entityUpdated(removedEntity, false);
        }
    }
}
