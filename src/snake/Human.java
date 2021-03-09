package snake;

public class Human implements Player{

    @Override
    public Direction makeMove(Game g) {
        AI.maxDepth = 0;
        return SnakeApp.g.snake.direction;
    }
}
