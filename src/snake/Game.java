package snake;

import java.util.Random;

public class Game {
    Cell[][] board;
    Snake snake;

    public Game(int sizeX, int sizeY, int foodOdds){
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

    public static String getName() {
        return "\uD83D\uDC0D Sauder School of Business";
    }

    public boolean advance(){
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

    public int getScore() {
        return snake.getSize();
    }
}
