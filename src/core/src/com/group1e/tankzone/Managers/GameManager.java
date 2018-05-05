package com.group1e.tankzone.Managers;

import com.badlogic.gdx.ApplicationAdapter;
import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Entities.Blackhole;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Systems.*;
import com.group1e.tankzone.Systems.AI.MoveStraightStrategy;
import com.group1e.tankzone.Systems.AI.ShootStraightStategy;
import com.group1e.tankzone.Systems.AI.TargetClosestStrategy;

import static com.badlogic.gdx.math.MathUtils.random;

public class GameManager extends ApplicationAdapter {

    public GameManager(GameType.Climate gameClimate, GameType.Difficulty gameDifficulty, GameType.GameMode gameMode) {
        GameType.climate = gameClimate;
        GameType.difficulty = gameDifficulty;
        GameType.gameMode = gameMode;
    }

    public int easyInitialTanks = 5;
    public int mediumInitialTanks = 10;
    public int hardInitialTanks = 15;
    //private String playerName = "  ";
    public int scoreOfPlayer = 0;
	@Override
	public void create () {
	    Engine engine = new Engine();
	    World.getInstance().setEngine(engine);

        engine.addSystem(new GraphicsSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new InputSystem());
        engine.addSystem(new GravitationalSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new DeathSystem());
        String[] factions = new String[] {"red", "blue"};
        engine.addSystem(new AISystem(factions, new TargetClosestStrategy(), new ShootStraightStategy(), new MoveStraightStrategy()));

        EntityFactory.createPlayer(
                "blue",
                600,
                400,
                0
        );

        World world = World.getInstance();

        world.setCameraTarget(engine.getEntity(0).getComponent(PositionComponent.class));
        MapGenerator mapGenerator = new MapGenerator();
        world.setMap(mapGenerator.getMap());

        for (Entity e : mapGenerator.getGeneratedObstacles()) {
            engine.addEntity(e);
        }

        if (GameType.gameMode == GameType.GameMode.CTF) {

            if (GameType.climate == GameType.Climate.DESERT)
                EntityFactory.createCastle("blue","desert",0,0,0);
            else if (GameType.climate == GameType.Climate.WINTER)
                EntityFactory.createCastle("blue","winter",0,0,0);
            else if (GameType.climate == GameType.Climate.TEMPERATE)
                EntityFactory.createCastle("blue","temperate",0,0,0);

        } else if (GameType.gameMode == GameType.GameMode.FFA) {
            int limit;
            if (GameType.difficulty == GameType.Difficulty.EASY)
                limit = easyInitialTanks;
            else if (GameType.difficulty == GameType.Difficulty.MEDIUM)
                limit = mediumInitialTanks;
            else
                limit = hardInitialTanks;

            for (int i = 0; i < limit; ++i) {
                EntityFactory.createTank(
                        "red",
                        random(0, 20) * 20,
                        random(0, 20) * 20,
                        random(-20, 20)
                );
            }
        }
    }

	@Override
	public void render () {
		World.getInstance().getEngine().update();
	}
	
	@Override
	public void dispose () {

	}

	public void addEntity(Entity e) {

    }
}
