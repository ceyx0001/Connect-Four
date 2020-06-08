import java.util.HashMap;
import java.util.LinkedList;

public class BoardMechanics {
    HashMap<Integer, LinkedList<Chip>> board = new HashMap<Integer, LinkedList<Chip>>();
    LinkedList<Chip> vertical;
    LinkedList<Chip> horizontal;
    LinkedList<Chip> slopeDown;
    LinkedList<Chip> slopeUp;

    BoardMechanics() {
        for (int i = 0; i < 7; i++) {
            board.put(i, new LinkedList<Chip>());
        }
    }

    public String insertCoin(Chip chip, int column, String turn) {
        if (board.get(column).size() == 6) {
            System.out.println("No spaces");
            return turn;
        } else {
            chip.setRow(board.get(column).size());
            board.get(column).add(chip);
            if (turn.equals("Red")) {
                turn = "Yellow";
            } else if (turn.equals("Yellow")) {
                turn = "Red";
            }
        }
        return turn;
    }

    public void connectChips(Chip chip) {
        int row = chip.getRow();
        int column = chip.getColumn();

        vertical = board.get(column);
        horizontal = new LinkedList<Chip>();
        slopeDown = new LinkedList<Chip>();
        slopeUp = new LinkedList<Chip>();

        addHorizontal(horizontal, column, row);
        addSlope(slopeUp, row, column, 1);
    }

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