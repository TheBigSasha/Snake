package snake;

import java.util.ArrayList;

public class World {
    ArrayList<Cell> world;

    public int worldDimensionX, worldDimensionY;

    public World(int sizeX, int sizeY){
        worldDimensionX = sizeX;
        worldDimensionY = sizeY;
        world = new ArrayList<>();
    }

    public void setCell(Cell c) {
        for (var cell : world) {
            if (cell.getX() == c.getX() && cell.getY() == c.getY()) {
                world.remove(cell);
                break;
            }
        }
        world.add(c);
    }

    public Cell getCell(int x, int y) {
        for (var cell : world) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        var newCell = new WorldCell(x,y);
        world.add(newCell);
        return newCell;
    }


}
