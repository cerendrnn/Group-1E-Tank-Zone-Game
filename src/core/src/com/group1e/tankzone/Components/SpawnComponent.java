package com.group1e.tankzone.Components;

public class SpawnComponent implements Component {

    public float spawnFrequency;
    public long lastSpawnTime;

    public SpawnComponent(float spawnFrequency){

        this.spawnFrequency = spawnFrequency;
    }

}
