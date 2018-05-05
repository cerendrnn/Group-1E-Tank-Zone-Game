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
    GameType.Climate gameClimate = GameType.Climate.TEMPERATE;
    GameType.Difficulty gameDifficulty = GameType.Difficulty.EASY;
    GameType.GameMode gameMode = GameType.GameMode.FFA;

    public GameManager(GameType.Climate gameClimate, GameType.Difficulty gameDifficulty, GameType.GameMode gameMode) {
        this.gameClimate = gameClimate;
        this.gameDifficulty = gameDifficulty;
        this.gameMode = gameMode;
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

	    if (gameDifficulty == GameType.Difficulty.EASY) {
            if (gameMode == GameType.GameMode.FFA) {
                for (int i = 0; i < easyInitialTanks; ++i) {
                    EntityFactory.createTank(
                            "red",
                            random(0, 20) * 20,
                            random(0, 20) * 20,
                            random(-20, 20)
                    );
                }
            } else if (gameMode == GameType.GameMode.CTF) {

                if (gameClimate == GameType.Climate.DESERT)
                    EntityFactory.createCastle("blue","desert",0,0,0);
                else if (gameClimate == GameType.Climate.WINTER)
                    EntityFactory.createCastle("blue","winter",0,0,0);
                else if (gameClimate == GameType.Climate.TEMPERATE)
                    EntityFactory.createCastle("blue","temperate",0,0,0);

            }

        } else if (gameDifficulty == GameType.Difficulty.MEDIUM) {
            if (gameMode == GameType.GameMode.FFA) {
                for (int i = 0; i < mediumInitialTanks; ++i) {
                    EntityFactory.createTank(
                            "red",
                            random(0, 20) * 20,
                            random(0, 20) * 20,
                            random(-20, 20)
                    );
                }
            }else if (gameMode == GameType.GameMode.CTF) {

                if (gameClimate == GameType.Climate.DESERT)
                    EntityFactory.createCastle("blue","desert",0,0,0);
                else if (gameClimate == GameType.Climate.WINTER)
                    EntityFactory.createCastle("blue","winter",0,0,0);
                else if (gameClimate == GameType.Climate.TEMPERATE)
                    EntityFactory.createCastle("blue","temperate",0,0,0);

            }

        } else if (gameDifficulty == GameType.Difficulty.HARD) {
            if (gameMode == GameType.GameMode.FFA) {
                for (int i = 0; i < hardInitialTanks; ++i) {
                    EntityFactory.createTank(
                            "red",
                            random(0, 20) * 20,
                            random(0, 20) * 20,
                            random(-20, 20)
                    );
                }
            }
            else if (gameMode == GameType.GameMode.CTF) {

                if (gameClimate == GameType.Climate.DESERT)
                    EntityFactory.createCastle("blue","desert",0,0,0);
                else if (gameClimate == GameType.Climate.WINTER)
                    EntityFactory.createCastle("blue","winter",0,0,0);
                else if (gameClimate == GameType.Climate.TEMPERATE)
                    EntityFactory.createCastle("blue","temperate",0,0,0);

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
