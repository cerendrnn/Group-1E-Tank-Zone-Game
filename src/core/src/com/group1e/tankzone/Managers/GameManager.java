package com.group1e.tankzone.Managers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Blackhole;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Systems.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class GameManager extends ApplicationAdapter {
    private Array<EntitySystem> systems = new Array<EntitySystem>();
    private World world = new World();

	@Override
	public void create () {
	    for (int i = 0; i < 10; ++i) {
            EntityFactory.createTank(
                    world,
                    "red",
                    random(10, 20) * 10,
                    random(10, 20) * 10,
                    random(-10, 10)
            );
        }

        EntityFactory.createPlayer(
                world,
                "blue",
                200,
                100,
                0
        );

	    //world.getEntities().add(new Blackhole(100, 100, 1000));

        systems.add(new GraphicsSystem());
        systems.add(new MovementSystem());
        systems.add(new InputSystem());
        systems.add(new PhysicsSystem());
        systems.add(new CollisionSystem());
        systems.add(new DeathSystem());
	}

	@Override
	public void render () {
		for (EntitySystem entitySystem : systems) {
		    entitySystem.update(world);
        }
	}
	
	@Override
	public void dispose () {
        for (EntitySystem entitySystem : systems) {
	        entitySystem.dispose();
        }
	}
}
