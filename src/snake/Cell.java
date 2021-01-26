package snake;

public class Cell {
    private Type type;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x, y;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Cell(int x, int y,Type t){
        this.type = t;
        this.x = x;
        this.y = y;
    }
}