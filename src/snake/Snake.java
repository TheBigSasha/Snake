package snake;

/**
 * Our cute snake! He is cute!
 */
public class Snake {
    /**
     * The cells which doth represent this snake, which cells of the map are part of its very being
     */
    private SnakeCell[] snake;
    /**
     * The direction in which this snake wants to go
     */
    public Direction direction;

    private final World world;

    /**
     * The constructor of our snake. If you were wondering how babies are made, it's this. Our snake grows from
     * just one cell, isn't that crazy?
     */
    public Snake(int x, int y, Direction d, World w){
        world = w;
        SnakeCell snakeCell = new SnakeCell(x,y);
        world.setCell(snakeCell);
        snake = new SnakeCell[]{snakeCell};
        direction = d;
    }

    /**
     * Bring me the head! This is useful so we know where snakey is.
     * @return the head of the snake
     */
    public Cell getHead(){
        return snake[0];
    }

    public boolean eat(int x, int y){
        return eat(world.getCell(x,y));
    }

    /**
     * Try to eat whatever is in the cell. If the cell is food, good.
     *
     * If the cell is part of the snake, return false. You died.
     * @param c the cell to eat
     * @return true if you're ok, false if you died
     */
    public boolean eat(Cell c){
        SnakeCell[] newSnake;


        int eatResult = c.eat();

        if(eatResult < 0){
            return false;
        }

        if(eatResult > 0){
            newSnake = new SnakeCell[snake.length + 1];
            newSnake[0] = new SnakeCell(c.getX(),c.getY());
            world.setCell(newSnake[0]);

            for(int i = 1; i < snake.length + 1; i++){
                newSnake[i] = snake[i-1];
            }

            snake = newSnake;
            return true;
        }

                //Shift snake forward by 1, with the index 0 now being this new cell and the old last element going back to being of type empty
                newSnake = new SnakeCell[snake.length];
                newSnake[0] = new SnakeCell(c.getX(),c.getY());
                world.setCell(newSnake[0]);
                for(int i = 1; i < snake.length; i++){
                     newSnake[i] = snake[i-1];
                }

                world.setCell(new WorldCell(snake[snake.length-1].getX(), snake[snake.length -1].getY()));
                snake = newSnake;
                return true;
        }


    public int getSize() {
        return snake.length;
    }
}
