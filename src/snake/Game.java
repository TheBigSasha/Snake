package snake;

import java.util.Random;

/**
 * A game of Snake
 */
public class Game {
    /**
     * The board which represents our map
     */
    Cell[][] board;
    /**
     * The snake which represents our snake.
     */
    Snake snake;

    Player p = SnakeApp.useAI.getState() ? new AI(Integer.parseInt(SnakeApp.depth.getText())) :new Human();

    /**
     * Create a new game of snake
     * @param sizeX the size of the map, X
     * @param sizeY the size of the map, Y
     * @param foodOdds the odds that a cell will have food (1/ this number)
     */
    public Game(int sizeX, int sizeY, int foodOdds, Player p){
        Random rand = new Random();
        board = new Cell[sizeX][sizeY];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(rand.nextInt(foodOdds) == 1){
                    board[i][j] = new Cell(i,j, Type.FOOD);
                }else{
                    board[i][j] = new Cell(i,j,Type.EMPTY);
                }
            }
        }

        snake = new Snake(board[board.length/2][board[0].length /2], Direction.UP);
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
        snake.direction = p.makeMove(this);
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();
        try {
            return switch (snake.direction) {
                case UP -> snake.eat(board[x][y - 1]);
                case DOWN -> snake.eat(board[x][y + 1]);
                case LEFT -> snake.eat(board[x - 1][y]);
                case RIGHT -> snake.eat(board[x + 1][y]);
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
