package snake;

import java.util.ArrayList;
import java.util.Random;

/**
 * A game of Snake
 */
public class Game implements GameInterface {

    /**
     * The snake which represents our snake.
     */
    Snake snake;

    World world;

    /**
     * Create a new game of snake
     * @param sizeX the size of the map, X
     * @param sizeY the size of the map, Y
     * @param foodOdds the odds that a cell will have food (1/ this number)
     */
    public Game(int sizeX, int sizeY, int foodOdds, int rockOdds){
        Random rand = new Random();
        world = new World(sizeX, sizeY);
        for(int i = 0; i < world.worldDimensionX; i++){
            for(int j = 0; j < world.worldDimensionY; j++){
                if(rand.nextInt(foodOdds) == 1) {
                    world.setCell(new FoodCell(i,j));
                }else if (rand.nextInt(rockOdds) == 1){
                    world.setCell(new OsbtacleCell(i,j));

                }
            }
        }

        snake = new Snake(world.worldDimensionX/2, world.worldDimensionY /2, Direction.DOWN, world);
    }

    @Override
    public boolean advance(){
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();
        try {
            return switch (snake.direction) {
                case UP -> snake.eat(x, y -1);
                case DOWN -> snake.eat(x, y + 1);
                case LEFT -> snake.eat(x-1, y);
                case RIGHT -> snake.eat(x + 1,y);
            };
        }catch(IndexOutOfBoundsException ex){
            return false;
        }
    }

    @Override
    public void setDirection(Direction d){
        snake.direction = d;
    }

    @Override
    public int getScore() {
        return snake.getSize();
    }

    @Override
    public int getWorldDimensionX() {
        return world.worldDimensionX;
    }

    @Override
    public int getWorldDimensionY() {
        return world.worldDimensionY;
    }

    @Override
    public Cell getCell(int x, int y) {
        return world.getCell(x,y);
    }

    @Override
    public void setCell(Cell c) {
        world.setCell(c);
    }
}
