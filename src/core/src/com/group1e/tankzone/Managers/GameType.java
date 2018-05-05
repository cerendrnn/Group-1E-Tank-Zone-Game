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

    public static Climate climate;
    public static Difficulty difficulty;
    public static GameMode gameMode;



}
