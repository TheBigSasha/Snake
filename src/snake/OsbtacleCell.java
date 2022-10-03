package snake;

import java.awt.*;

public class OsbtacleCell extends Cell{

    /**
     * Create a cell of a specified type
     *
     * @param x
     * @param y
     */
    public OsbtacleCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int eat() {
        return -1;
    }

    @Override
    public Color getColor() {
        return Color.gray;
    }
}
