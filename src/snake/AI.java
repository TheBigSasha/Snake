package snake;

public class AI implements Player{
    public AI(int depth){
        maxDepth = depth;
    }
    static int maxDepth = 10;
    private Game g;
    @Override
    public Direction makeMove(Game g) {
        //This is the base case.
        this.g = g;

        Direction out = Direction.UP;
        Direction[] directions = Direction.values();
        for(Direction d : directions){
            if(tryMove(out) < tryMove(d)){
                out = d;
            }
        }

        return out;
    }

    public int tryMove(Direction d) {
        int points = 0;
        int depth = 0;
        Cell toEat = new Cell(0,0,Type.SNAKE);
        Cell[][] board = g.board;
        int x = g.snake.getHead().getX();
        int y = g.snake.getHead().getY();
        try {
            switch (d) {
                case UP -> toEat = (board[x][y - 1]);
                case DOWN -> toEat = (board[x][y + 1]);
                case LEFT -> toEat = (board[x - 1][y]);
                case RIGHT -> toEat = (board[x + 1][y]);
            }
        }catch(ArrayIndexOutOfBoundsException ignored){
        }
        switch (toEat.getType()) {
            case FOOD -> points += 100;
            case SNAKE -> points = Integer.MIN_VALUE;
            case EMPTY -> points += distanceFromCenter(x,y,board);
        }
        int baseScore = Integer.MIN_VALUE;
        for(Direction dir : Direction.values()){
            int newScore = tryMoveRecursive(dir, toEat,points,depth);
            System.out.println("points for " + dir.name() +" is " + newScore );
            if(baseScore < newScore){
                baseScore = newScore;
            }
        }
        return baseScore;
    }

    private int tryMoveRecursive(Direction d, Cell position, int score, int depth){
        if(depth > maxDepth){
            return score;
        }
        depth++;
        Cell toEat;
        Cell[][] board = g.board;
        int x = position.getX();
        int y = position.getY();
        try {
            switch (d) {
                case UP -> toEat = (board[x][y - 1]);
                case DOWN -> toEat = (board[x][y + 1]);
                case LEFT -> toEat = (board[x - 1][y]);
                case RIGHT -> toEat = (board[x + 1][y]);
                default -> toEat = board[0][0];
            }
        }catch(IndexOutOfBoundsException ex){
            return Integer.MIN_VALUE;
        }
        switch (toEat.getType()) {
            case FOOD -> score += 10;
            case SNAKE -> score = Integer.MIN_VALUE;
        }
        int baseScore = Integer.MIN_VALUE;
        for(Direction dir : Direction.values()){
            int newScore = tryMoveRecursive(dir, toEat,score,depth);
            if(baseScore < newScore){
                baseScore = newScore;
            }
        }
       return score + baseScore;
    }
    private static int distanceFromCenter(int x, int y, Cell[][] board){
        double boardSizeX = board.length;
        double boardSizeY = board[0].length;
        double diffX = Math.abs((double) x - boardSizeX / 2.);
        double diffY = Math.abs((double) y - boardSizeY/ 2.);
        double distance = Math.sqrt(Math.pow(diffX, 2.) + Math.pow(diffY,2.));
        return (int) Math.round(distance);
    }

}
