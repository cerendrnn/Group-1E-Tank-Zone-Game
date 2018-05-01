package com.group1e.tankzone.Managers;

public class GameType {

    public enum Climate{
        TEMPERATE,
        WINTER,
        DESERT
    }

    public enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }

    public enum GameMode{
        CTF,
        FFA
    }

    public Climate climate;
    public Difficulty difficulty;
    public GameMode game_mode;

    GameType(Climate climate, Difficulty difficulty, GameMode game_mode)
    {
        this.climate = climate;
        this.difficulty = difficulty;
        this.game_mode = game_mode;
    }


}
