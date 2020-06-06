public class Chip {
    int type, column, row;
    Chip(int t, int c) {
        type = t;
        column = c;
        row = -1;
    }

    public void setRow(int r) {
        row = r;
    }
}