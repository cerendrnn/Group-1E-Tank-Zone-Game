package com.group1e.tankzone.Managers;

public class GameType {

    public enum Climate{
        temperate,
        winter,
        desert
    }

    public enum Difficulty{
        easy,
        medium,
        hard
    }

    public enum GameMode{
        CTF,
        FFA
    }

    public Climate climate;
    public Difficulty difficulty;
    public GameMode game_mode;



}
