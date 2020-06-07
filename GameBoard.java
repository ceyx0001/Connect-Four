import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GameBoard {
    private HashMap<Integer, LinkedList<Chip>> board = new HashMap<Integer, LinkedList<Chip>>();
    private LinkedList<LinkedList<Chip>> queue = new LinkedList<LinkedList<Chip>>();
    LinkedList<Chip> vertical;
    LinkedList<Chip> horizontal;
    LinkedList<Chip> slopeDown;
    LinkedList<Chip> slopeUp;

    public void insertCoin(Chip chip, int column) {
        LinkedList<Chip> temp = board.get(column);
        if (temp.size() == 6) {
            System.out.println("No spaces");
        } else {
            temp.add(chip);
            chip.setRow(temp.size());
            board.put(column, temp);
        }
    }

    public void connectChips(Chip chip) {
        int row = chip.getRow();
        int column = chip.getColumn();
        int left = column - 3;
        int right = column + 3;

        vertical = board.get(column);
        horizontal = new LinkedList<Chip>();
        slopeDown = new LinkedList<Chip>();
        slopeUp = new LinkedList<Chip>();

        addHorizontal(horizontal, column, row);
        addSlope(slopeUp, row, column, 1);
    }

    // add entire row, start at col - 3...

    public void addHorizontal(LinkedList<Chip> horizontal, int column, int row) {
        int c = 0;
        horizontal.add(board.get(c++).get(row));
    }

    public void addSlope(LinkedList<Chip> slope, int row, int column, int dir) {
        int r = row;
        int c = column;
        for (int i = 0; i < 4; i++) {
            if (r < 6 || c < 0) {
                break;
            }
            LinkedList<Chip> temp = board.get(c--);
            slope.add(temp.get(r + dir));
        }

        r = row;
        c = column;
        for (int i = 0; i < 4; i++) {
            if (r < 6 || c > 7) {
                break;
            }
            LinkedList<Chip> temp = board.get(c++);
            slope.add(temp.get(r + dir));
        }
        addSlope(slopeDown, row, column, -1);
    }
}