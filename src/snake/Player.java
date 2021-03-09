package snake;

public interface Player {
    /**
     * We choose the move that the player takes, taking all game conditions into account.. But if we don't use recursion we only think one move forward in time.
     * Can we make a good move if we only see one step into the future?
     * Or can we use recursion to boost the intelligence?
     * @param g the game that is being played
     * @return  The optimal direction, hopefully
     */
    public Direction makeMove(Game g);
}