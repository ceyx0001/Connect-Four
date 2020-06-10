public class Chip {
    int type, column, row;

    public Chip(int t, int r, int c) {
        type = t;
        column = c;
        row = r;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getType() {
        return type;
    }
}