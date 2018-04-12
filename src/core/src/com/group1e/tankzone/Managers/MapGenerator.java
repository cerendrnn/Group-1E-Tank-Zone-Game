package com.group1e.tankzone.Managers;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.Obstacle;

import java.util.*;

// Procedural map generator with Cellular Automata
public class MapGenerator {
    public enum Tile {
        DIRT,
        GRASS_TOP_LEFT,
        GRASS_TOP,
        GRASS_TOP_RIGHT,
        GRASS_RIGHT,
        GRASS_BOTTOM_RIGHT,
        GRASS_BOTTOM,
        GRASS_BOTTOM_LEFT,
        GRASS_LEFT,
        GRASS_CENTER
    }

    private static final int WIDTH = 1920 / 4;
    private static final int HEIGHT = 1080 / 4;
    private static final double FILL_RATIO = 0.6425;
    private static final double OBSTACLE_GEN_RATIO = 0.8;
    private static final float CUT_TIE_LENGTH = 10*10;

    private Random random;
    private Tile[][] map;
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
        map = new Tile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                map[x][y] = Tile.GRASS_CENTER;
            }
        }

        randomlyPaintMap();
        for (int i = 0; i < 3; ++i) smoothMap();
        randomlyPaintMap();
        for (int i = 0; i < 4; ++i) smoothMap();

        generateObstacles();

        placeTilesForGrassBoundries();

        //smoothMap();
    }

    private void placeTilesForGrassBoundries() {
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                if (map[x][y] == Tile.DIRT)
                    continue;

                boolean bLeft   = x - 1 >= 0     && map[x - 1][y] == Tile.DIRT;
                boolean bRight  = x + 1 < WIDTH  && map[x + 1][y] == Tile.DIRT;
                boolean bBottom = y - 1 >= 0     && map[x][y - 1] == Tile.DIRT;
                boolean bTop    = y + 1 < HEIGHT && map[x][y + 1] == Tile.DIRT;

                if (!bTop && !bBottom && bLeft)
                    map[x][y] = Tile.GRASS_LEFT;
                else if (!bTop && !bBottom && bRight)
                    map[x][y] = Tile.GRASS_RIGHT;
                else if (!bLeft && !bRight && bTop)
                    map[x][y] = Tile.GRASS_TOP;
                else if (!bLeft && !bRight && bBottom)
                    map[x][y] = Tile.GRASS_BOTTOM;
                else if (bTop && bLeft)
                    map[x][y] = Tile.GRASS_TOP_LEFT;
                else if (bTop && bRight)
                    map[x][y] = Tile.GRASS_TOP_RIGHT;
                else if (bBottom && bLeft)
                    map[x][y] = Tile.GRASS_BOTTOM_LEFT;
                else if (bBottom && bRight)
                    map[x][y] = Tile.GRASS_BOTTOM_RIGHT;
            }
        }
    }

    private void randomlyPaintMap() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Fill boundries always
                if (x == 0 || y == 0 || x == WIDTH - 1 || y == HEIGHT - 1) {
                    map[x][y] = Tile.DIRT;
                } else {
                    double randomRatio = random.nextDouble();
                    if (randomRatio < FILL_RATIO)
                        map[x][y] = Tile.DIRT;
                }
            }
        }
    }

    private void smoothMap() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);

                map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
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
            if (p.x < 0 || p.y < 0 || p.x >= WIDTH || p.y >= HEIGHT || map[p.x][p.y] == Tile.DIRT) {
                count++;
            }
        }

        return count;
    }

    private void generateObstacles() {
        // TODO move this to constants
        final String obstacleTypes[] = {"tree1", "tree2", "rock"};

        for (int x = 0; x < WIDTH; x += 2) {
            for (int y = 0; y < HEIGHT; y += 2) {
                int wallCount = neighbouringWallsCountFor(x, y);

                if (wallCount == 0) {
                    double randomChance = random.nextDouble();
                    if (randomChance < OBSTACLE_GEN_RATIO) {
                        String randObstacleType = obstacleTypes[random.nextInt(obstacleTypes.length)];
                        generatedObstacles.add(new Obstacle(randObstacleType, x * 32, y * 32));
                    }
                }
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public Array<Entity> getGeneratedObstacles() {
        return generatedObstacles;
    }
}
