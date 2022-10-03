package snake;

import java.util.ArrayList;
import java.util.Random;

/**
 * A game of Snake
 */
public class Game {

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

    /**
     * The name of our game
     * @return name of the game
     */
    public static String getName() {
        return "\uD83D\uDC0D Sauder School of Business";
    }

    /**
     * Move forward one step in the game. The snake will move in
     * the direction it wants to go in.
     * @return true if the snake still lives
     */
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

    /**
     * Set the direction of our snake
     * @param d the direction we want snakey to go in next time advance is called
     */
    public void setDirection(Direction d){
        snake.direction = d;
    }

    /**
     * Gets the latest score
     * @return the length of the snake
     */
    public int getScore() {
        return snake.getSize();
    }
}
