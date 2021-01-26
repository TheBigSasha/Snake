package snake;

/**
 * A class to represent a cell, with coordinates and type
 */
public class Cell {
    /**
     * What's here
     */
    private Type type;

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
     * Get what's in this cell
     */
    public Type getType() {
        return type;
    }

    /**
     * Change what is in this cell
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Create a cell of a specified type
     */
    public Cell(int x, int y,Type t){
        this.type = t;
        this.x = x;
        this.y = y;
    }
}