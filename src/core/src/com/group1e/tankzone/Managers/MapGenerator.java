package com.group1e.tankzone.Managers;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.Obstacle;
import com.group1e.tankzone.Utils.Util;

import java.util.*;

// Procedural map generator with Cellular Automata
public class MapGenerator {
    private static final int WIDTH = 1920 / 4;
    private static final int HEIGHT = 1080 / 4;
    private static final double FILL_RATIO = 0.6425;
    private static final double OBSTACLE_GEN_RATIO = 0.8;
    private static final float CUT_TIE_LENGTH = 10*10;

    private Random random;
    private int[][] map;
    private Array<Entity> generatedObstacles = new Array<Entity>();

    public MapGenerator() {
        this.random = new Random();
        generateMap();
    }

    public MapGenerator(long seed) {
        this.random = new Random(seed);
        generateMap();
    }

    public void generateMap() {
        map = new int[WIDTH][HEIGHT];

        randomlyPaintMap();
        for (int i = 0; i < 3; ++i) smoothMap();
        randomlyPaintMap();
        for (int i = 0; i < 4; ++i) smoothMap();

        generateObstacles();

        //smoothMap();
    }

    private void randomlyPaintMap() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Fill boundries always
                if (x == 0 || y == 0 || x == WIDTH - 1 || y == HEIGHT - 1) {
                    map[x][y] = 1;
                } else {
                    double randomRatio = random.nextDouble();
                    if (randomRatio < FILL_RATIO)
                        map[x][y] = 1;
                }
            }
        }
    }

    private void smoothMap() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);

                map[x][y] = wallCount > 4 ? 1 : 0;
            }
        }
    }


    private int neighbouringWallsCountFor(int x, int y) {
        int count = 0;

        GridPoint2 positionsToCheck[] = new GridPoint2[] {
                new GridPoint2(x, y - 1),
                new GridPoint2(x, y + 1),
                new GridPoint2(x - 1, y),
                new GridPoint2(x - 1, y - 1),
                new GridPoint2(x - 1, y + 1),
                new GridPoint2(x + 1, y),
                new GridPoint2(x + 1, y - 1),
                new GridPoint2(x + 1, y + 1)
        };

        for (GridPoint2 p : positionsToCheck) {
            if (p.x < 0 || p.y < 0 || p.x >= WIDTH || p.y >= HEIGHT || map[p.x][p.y] == 1) {
                count++;
            }
        }

        return count;
    }

    private void generateObstacles() {
        // TODO move this to constants
        final String obstacleTypes[] = {"tree1", "tree2", "rock"};

        for (int x = 0; x < WIDTH; x += 4) {
            for (int y = 0; y < HEIGHT; y += 4) {
                int wallCount = neighbouringWallsCountFor(x, y);

                if (wallCount == 0) {
                    double randomChance = random.nextDouble();
                    if (randomChance < OBSTACLE_GEN_RATIO) {
                        String randObstacleType = obstacleTypes[random.nextInt(obstacleTypes.length)];
                        generatedObstacles.add(new Obstacle(randObstacleType, x * 16, y * 16));
                    }
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public Array<Entity> getGeneratedObstacles() {
        return generatedObstacles;
    }
}
