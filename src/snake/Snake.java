package snake;
public class Snake {
    private Cell[] snake;
    public Direction direction;

    public Snake(Cell c, Direction d){
        c.setType(Type.SNAKE);
        snake = new Cell[]{c};
        direction = d;
    }

    public Cell getHead(){
        return snake[0];
    }

    /**
     * Try to eat whatever is in the cell. If the cell is food, good.
     *
     * If the cell is part of the snake, return false. You died.
     * @param c
     * @return
     */
    public boolean eat(Cell c){
        Cell[] newSnake;
        switch(c.getType()){
            case SNAKE:
                //End the game, the snake is dead
                return false;

            case FOOD:
                //Grow the snake by 1, with its new head on this spot and its tail unmoved
                newSnake = new Cell[snake.length + 1];
                newSnake[0] = c;
                c.setType(Type.SNAKE);
                for(int i = 0; i < snake.length; i++){
                    newSnake[i + 1] = snake[i];
                }
                snake = newSnake;
                return true;

            case EMPTY:
                //Shift snake forward by 1, with the index 0 now being this new cell and the old last element going back to being of type empty
                newSnake = new Cell[snake.length];
                newSnake[0] = c;
                c.setType(Type.SNAKE);
                for(int i = 1; i < snake.length; i++){
                     newSnake[i] = snake[i-1];
                }

                snake[snake.length-1].setType(Type.EMPTY);
                snake = newSnake;
                return true;
        }
        return false;
    }

    public int getSize() {
        return snake.length;
    }
}