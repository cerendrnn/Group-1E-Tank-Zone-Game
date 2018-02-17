package com.group1e.tankzone.Managers;

import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;

public class World {
    private Array<Entity> entities = new Array<Entity>();

    public Array<Entity> getEntities() {
        return entities;
    }
}
