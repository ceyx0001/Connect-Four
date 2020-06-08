public class Chip {
    String type;
    int column, row;
    Chip(String t) {
        type = t;
        column = -1;
        row = -1;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setColumn(int c) {
        column = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}