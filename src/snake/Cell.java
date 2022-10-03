package snake;

import java.awt.*;

/**
 * A class to represent a cell, with coordinates and type
 */
public abstract class Cell {
    /**
     * What's here
     */
    /**
     * The x and y coordinates of this cell
     */
    private int x, y;

    /**
     * @return the x coordinate of this cell on the grid
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate of this cell on the grid
     */
    public int getY() {
        return y;
    }

    /**
     * return + number for score or
     * - number for kill
     * or 0 for regular cell
     * @return
     */
    public abstract int eat();

    public abstract Color getColor();

    /**
     * Create a cell of a specified type
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
}
