package com.group1e.tankzone.Managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Entities.Entity;

import static com.group1e.tankzone.Managers.MapGenerator.Tile;

public class World {
    private PositionComponent cameraTarget;
    private Engine engine;
    private Tile[][] map;

    private static final World instance = new World();
    private World() { }
    public static World getInstance() {
        return instance;
    }

    public PositionComponent getCameraTarget() {
        return cameraTarget;
    }

    public void setCameraTarget(PositionComponent cameraTarget) {
        this.cameraTarget = cameraTarget;
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
