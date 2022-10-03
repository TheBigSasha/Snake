package snake;

public interface GameInterface {
    /**
     * The name of our game
     *
     * @return name of the game
     */
    static String getName() {
        return "\uD83D\uDC0D Sauder School of Business";
    }

    /**
     * Move forward one step in the game. The snake will move in
     * the direction it wants to go in.
     *
     * @return true if the snake still lives
     */
    boolean advance();

    /**
     * Set the direction of our snake
     *
     * @param d the direction we want snakey to go in next time advance is called
     */
    void setDirection(Direction d);

    /**
     * Gets the latest score
     *
     * @return the length of the snake
     */
    int getScore();

    int getWorldDimensionX();

    int getWorldDimensionY();

    Cell getCell(int x, int y);

    void setCell(Cell c);
}
