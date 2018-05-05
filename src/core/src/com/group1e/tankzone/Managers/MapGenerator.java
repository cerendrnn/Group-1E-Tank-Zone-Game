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
        GRASS_CENTER,
        SNOW,
        SNOW_CENTER,
        SNOW_LEFT,
        SNOW_RIGHT,
        SNOW_TOP,
        SNOW_BOTTOM,
        SNOW_TOP_LEFT,
        SNOW_TOP_RIGHT,
        SNOW_BOTTOM_LEFT,
        SNOW_BOTTOM_RIGHT,
        SAND,
        SAND_CENTER,
        SAND_LEFT,
        SAND_RIGHT,
        SAND_TOP,
        SAND_BOTTOM,
        SAND_TOP_LEFT,
        SAND_TOP_RIGHT,
        SAND_BOTTOM_LEFT,
        SAND_BOTTOM_RIGHT,
    }

    private static final int WIDTH = 1920 / 4;
    private static final int HEIGHT = 1080 / 4;
    private static final double FILL_RATIO = 0.6509;
    private static final double OBSTACLE_GEN_RATIO = 0.8;

    private Random random;
    private Tile[][] map;
    private Array<Entity> generatedObstacles = new Array<Entity>();
    //private Array<Entity> generatedObstacles2 = new Array<Entity>();
    //private Array<Entity> generatedObstacles3 = new Array<Entity>();
    public GameType.Climate climate;

    public MapGenerator() {
        this.random = new Random();
        //this.climate = climate;
        generateMap();
    }

    public MapGenerator(long seed) {
        this.random = new Random(seed);
        //climate = GameType.Climate.temperate;//BY DEFAULT
        generateMap();
    }

    public void generateMap() {
        map = new Tile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                if(climate == GameType.Climate.temperate)
                    map[x][y] = Tile.GRASS_CENTER;
                else if(climate == GameType.Climate.winter)
                    map[x][y] = Tile.SNOW_CENTER;
                else if(climate == GameType.Climate.desert)
                    map[x][y] = Tile.SAND_CENTER;
            }
        }

        randomlyPaintMap();
        for (int i = 0; i < 12; ++i) {
            if      (i % 4 == 0) smoothMapBottomLeft();
            else if (i % 4 == 1) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

        randomlyPaintMap();
        for (int i = 0; i < 8; ++i) {
            if      (i % 4 == 1) smoothMapBottomLeft();
            else if (i % 4 == 3) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

        randomlyPaintMap();
        for (int i = 0; i < 4; ++i) {
            if      (i % 4 == 3) smoothMapBottomLeft();
            else if (i % 4 == 0) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

        generateObstacles(climate);

        placeTilesForGrassBoundries();

        //smoothMap();
    }

    private void placeTilesForGrassBoundries() {
        if(climate == GameType.Climate.temperate) {
            for (int x = 0; x < WIDTH; ++x) {
                for (int y = 0; y < HEIGHT; ++y) {
                    if (map[x][y] == Tile.DIRT)
                        continue;

                    boolean bLeft = x - 1 >= 0 && map[x - 1][y] == Tile.DIRT;
                    boolean bRight = x + 1 < WIDTH && map[x + 1][y] == Tile.DIRT;
                    boolean bBottom = y - 1 >= 0 && map[x][y - 1] == Tile.DIRT;
                    boolean bTop = y + 1 < HEIGHT && map[x][y + 1] == Tile.DIRT;

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
        else if(climate == GameType.Climate.winter) {
            for (int x = 0; x < WIDTH; ++x) {
                for (int y = 0; y < HEIGHT; ++y) {
                    if (map[x][y] == Tile.SNOW)
                        continue;

                    boolean bLeft = x - 1 >= 0 && map[x - 1][y] == Tile.SNOW;
                    boolean bRight = x + 1 < WIDTH && map[x + 1][y] == Tile.SNOW;
                    boolean bBottom = y - 1 >= 0 && map[x][y - 1] == Tile.SNOW;
                    boolean bTop = y + 1 < HEIGHT && map[x][y + 1] == Tile.SNOW;

                    if (!bTop && !bBottom && bLeft)
                        map[x][y] = Tile.SNOW_LEFT;
                    else if (!bTop && !bBottom && bRight)
                        map[x][y] = Tile.SNOW_RIGHT;
                    else if (!bLeft && !bRight && bTop)
                        map[x][y] = Tile.SNOW_TOP;
                    else if (!bLeft && !bRight && bBottom)
                        map[x][y] = Tile.SNOW_BOTTOM;
                    else if (bTop && bLeft)
                        map[x][y] = Tile.SNOW_TOP_LEFT;
                    else if (bTop && bRight)
                        map[x][y] = Tile.SNOW_TOP_RIGHT;
                    else if (bBottom && bLeft)
                        map[x][y] = Tile.SNOW_BOTTOM_LEFT;
                    else if (bBottom && bRight)
                        map[x][y] = Tile.SNOW_BOTTOM_RIGHT;
                }
            }
        }
        else if(climate == GameType.Climate.desert) {
            for (int x = 0; x < WIDTH; ++x) {
                for (int y = 0; y < HEIGHT; ++y) {
                    if (map[x][y] == Tile.SAND)
                        continue;

                    boolean bLeft = x - 1 >= 0 && map[x - 1][y] == Tile.SAND;
                    boolean bRight = x + 1 < WIDTH && map[x + 1][y] == Tile.SAND;
                    boolean bBottom = y - 1 >= 0 && map[x][y - 1] == Tile.SAND;
                    boolean bTop = y + 1 < HEIGHT && map[x][y + 1] == Tile.SAND;

                    if (!bTop && !bBottom && bLeft)
                        map[x][y] = Tile.SAND_LEFT;
                    else if (!bTop && !bBottom && bRight)
                        map[x][y] = Tile.SAND_RIGHT;
                    else if (!bLeft && !bRight && bTop)
                        map[x][y] = Tile.SAND_TOP;
                    else if (!bLeft && !bRight && bBottom)
                        map[x][y] = Tile.SAND_BOTTOM;
                    else if (bTop && bLeft)
                        map[x][y] = Tile.SAND_TOP_LEFT;
                    else if (bTop && bRight)
                        map[x][y] = Tile.SAND_TOP_RIGHT;
                    else if (bBottom && bLeft)
                        map[x][y] = Tile.SAND_BOTTOM_LEFT;
                    else if (bBottom && bRight)
                        map[x][y] = Tile.SAND_BOTTOM_RIGHT;
                }
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
                    if (randomRatio < FILL_RATIO) {
                        if (climate == GameType.Climate.temperate)
                            map[x][y] = Tile.DIRT;
                        else if(climate == GameType.Climate.winter)
                            map[x][y] = Tile.SNOW;
                        else if(climate == GameType.Climate.desert)
                            map[x][y] = Tile.SAND;
                    }

                }
            }
        }
    }

    private void smoothMapBottomLeft() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);
                if (climate == GameType.Climate.temperate)
                    map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
                else if(climate == GameType.Climate.winter)
                    map[x][y] = wallCount > 4 ? Tile.SNOW : Tile.SNOW_CENTER;
                else if(climate == GameType.Climate.desert)
                    map[x][y] = wallCount > 4 ? Tile.SAND : Tile.SAND_CENTER;
            }
        }
    }

    private void smoothMapBottomRight() {
        for (int x = WIDTH - 1; x >= 0; x--) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);
                if (climate == GameType.Climate.temperate)
                    map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
                else if(climate == GameType.Climate.winter)
                    map[x][y] = wallCount > 4 ? Tile.SNOW : Tile.SNOW_CENTER;
                else if(climate == GameType.Climate.desert)
                    map[x][y] = wallCount > 4 ? Tile.SAND : Tile.SAND_CENTER;
            }
        }
    }

    private void smoothMapTopLeft() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = HEIGHT - 1; y >= 0; y--) {
                int wallCount = neighbouringWallsCountFor(x, y);
                if (climate == GameType.Climate.temperate)
                    map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
                else if(climate == GameType.Climate.winter)
                    map[x][y] = wallCount > 4 ? Tile.SNOW : Tile.SNOW_CENTER;
                else if(climate == GameType.Climate.desert)
                    map[x][y] = wallCount > 4 ? Tile.SAND : Tile.SAND_CENTER;
            }
        }
    }

    private void smoothMapTopRight() {
        for (int x = WIDTH - 1; x >= 0; x--) {
            for (int y = HEIGHT - 1; y >= 0; y--) {
                int wallCount = neighbouringWallsCountFor(x, y);
                if (climate == GameType.Climate.temperate)
                    map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
                else if(climate == GameType.Climate.winter)
                    map[x][y] = wallCount > 4 ? Tile.SNOW : Tile.SNOW_CENTER;
                else if(climate == GameType.Climate.desert)
                    map[x][y] = wallCount > 4 ? Tile.SAND : Tile.SAND_CENTER;

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
            if (climate == GameType.Climate.temperate) {
                if (p.x < 0 || p.y < 0 || p.x >= WIDTH || p.y >= HEIGHT || map[p.x][p.y] == Tile.DIRT) {
                    count++;
                }
            }
            if (climate == GameType.Climate.winter) {
                if (p.x < 0 || p.y < 0 || p.x >= WIDTH || p.y >= HEIGHT || map[p.x][p.y] == Tile.SNOW) {
                    count++;
                }
            }
            if (climate == GameType.Climate.desert) {
                if (p.x < 0 || p.y < 0 || p.x >= WIDTH || p.y >= HEIGHT || map[p.x][p.y] == Tile.SAND) {
                    count++;
                }
            }
        }

        return count;
    }

   private void generateObstacles(GameType.Climate climate) {
        // TODO move this to constants
        final String obstacleTypes1[] = {"tree1", "tree2", "rock"};
        final String obstacleTypes2[] = {"snowtree1","snowtree2", "snowman"};
        final String obstacleTypes3[] = {"cactus", "browntree", "brownrock"};

        if(climate == GameType.Climate.temperate) {
            //final String obstacleTypes1[] = {"tree1", "tree2", "rock"};
            for (int x = 0; x < WIDTH; x += 2) {
                for (int y = 0; y < HEIGHT; y += 2) {
                    int wallCount = neighbouringWallsCountFor(x, y);

                    if (wallCount == 0) {
                        double randomChance = random.nextDouble();
                        if (randomChance < OBSTACLE_GEN_RATIO) {
                            String randObstacleType = obstacleTypes1[random.nextInt(obstacleTypes1.length)];
                            generatedObstacles.add(new Obstacle(randObstacleType, x * 32, y * 32));
                        }
                    }
                }
            }
        }
        else if(climate == GameType.Climate.winter) {
            //final String obstacleTypes2[] = {"snowtree1", "snowtree2", "snowman"};
            for (int x = 0; x < WIDTH; x += 2) {
                for (int y = 0; y < HEIGHT; y += 2) {
                    int wallCount = neighbouringWallsCountFor(x, y);

                    if (wallCount == 0) {
                        double randomChance = random.nextDouble();
                        if (randomChance < OBSTACLE_GEN_RATIO) {
                            String randObstacleType = obstacleTypes2[random.nextInt(obstacleTypes2.length)];
                            generatedObstacles.add(new Obstacle(randObstacleType, x * 32, y * 32));
                        }
                    }
                }
            }
        }
        else if(climate == GameType.Climate.desert) {
            //final String obstacleTypes3[] = {"cactus", "browntree", "brownrock"};
            for (int x = 0; x < WIDTH; x += 2) {
                for (int y = 0; y < HEIGHT; y += 2) {
                    int wallCount = neighbouringWallsCountFor(x, y);

                    if (wallCount == 0) {
                        double randomChance = random.nextDouble();
                        if (randomChance < OBSTACLE_GEN_RATIO) {
                            String randObstacleType = obstacleTypes3[random.nextInt(obstacleTypes3.length)];
                            generatedObstacles.add(new Obstacle(randObstacleType, x * 32, y * 32));
                        }
                    }
                }
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setClimate(GameType.Climate climate){this.climate = climate;}

    public GameType.Climate getClimate(){return climate;}

    public Array<Entity> getGeneratedObstacles() {

        return generatedObstacles;
    }


}
/*public class MapGenerator {
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
    private static final double FILL_RATIO = 0.6509;
    private static final double OBSTACLE_GEN_RATIO = 0.8;

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
        for (int i = 0; i < 12; ++i) {
            if      (i % 4 == 0) smoothMapBottomLeft();
            else if (i % 4 == 1) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

        randomlyPaintMap();
        for (int i = 0; i < 8; ++i) {
            if      (i % 4 == 1) smoothMapBottomLeft();
            else if (i % 4 == 3) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

        randomlyPaintMap();
        for (int i = 0; i < 4; ++i) {
            if      (i % 4 == 3) smoothMapBottomLeft();
            else if (i % 4 == 0) smoothMapBottomRight();
            else if (i % 4 == 2) smoothMapTopLeft();
            else                 smoothMapTopRight();
        }

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

    private void smoothMapBottomLeft() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);

                map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
            }
        }
    }

    private void smoothMapBottomRight() {
        for (int x = WIDTH - 1; x >= 0; x--) {
            for (int y = 0; y < HEIGHT; y++) {
                int wallCount = neighbouringWallsCountFor(x, y);

                map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
            }
        }
    }

    private void smoothMapTopLeft() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = HEIGHT - 1; y >= 0; y--) {
                int wallCount = neighbouringWallsCountFor(x, y);

                map[x][y] = wallCount > 4 ? Tile.DIRT : Tile.GRASS_CENTER;
            }
        }
    }

    private void smoothMapTopRight() {
        for (int x = WIDTH - 1; x >= 0; x--) {
            for (int y = HEIGHT - 1; y >= 0; y--) {
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
}*/
