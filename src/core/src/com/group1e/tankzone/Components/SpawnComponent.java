package com.group1e.tankzone.Components;

public class SpawnComponent implements Component {

    public float spawnFrequency;
    public long lastSpawnTime = 0;

    public SpawnComponent(float spawnFrequency){

        this.spawnFrequency = spawnFrequency;
        lastSpawnTime += spawnFrequency;
    }

    public long getLastSpawnTime(){

        return lastSpawnTime;
    }



}
