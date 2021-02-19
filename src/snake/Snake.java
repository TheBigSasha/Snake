package snake;

/**
 * Our cute snake! He is cute!
 */
public class Snake {
    /**
     * The cells which doth represent this snake, which cells of the map are part of its very being
     */
    private Queue<Cell> snake;
    /**
     * The direction in which this snake wants to go
     */
    public Direction direction;

    /**
     * The constructor of our snake. If you were wondering how babies are made, it's this. Our snake grows from
     * just one cell, isn't that crazy?
     *
     * @param c the cell which is the origin of our snake
     * @param d the direction its born to go in. Is this destiny?
     */
    public Snake(Cell c, Direction d){
        c.setType(Type.SNAKE);
        snake = new Queue<Cell>();
        snake.enqueue(c);
        direction = d;
    }

    /**
     * Bring me the head! This is useful so we know where snakey is.
     * @return the head of the snake
     */
    public Cell getHead(){
        return snake.getLast();
    }

    /**
     * Try to eat whatever is in the cell. If the cell is food, good.
     *
     * If the cell is part of the snake, return false. You died.
     * @param c the cell to eat
     * @return true if you're ok, false if you died
     */
    public boolean eat(Cell c){
        switch(c.getType()){
            case SNAKE:
                //End the game, the snake is dead
                return false;

            case FOOD:
                //Grow the snake by 1, with its new head on this spot and its tail unmoved
                c.setType(Type.SNAKE);
                snake.enqueue(c);
                return true;

            case EMPTY:
                //Shift snake forward by 1, with the index 0 now being this new cell and the old last element going back to being of type empty
                c.setType(Type.SNAKE);
                snake.enqueue(c);
                Cell removed = snake.dequeue();
                removed.setType(Type.EMPTY);
                return true;
        }
        return false;
    }

    public int getSize() {
        return snake.size();
    }
}