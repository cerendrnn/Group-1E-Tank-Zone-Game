package com.group1e.tankzone.Managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Entities.Entity;

public class World {
    private PositionComponent cameraTarget;
    private Engine engine;
    private int[][] map;

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

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
