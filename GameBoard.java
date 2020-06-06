import java.util.HashMap;
import java.util.LinkedList;

public class GameBoard {
    HashMap<Integer, LinkedList<Chip>> board = new HashMap<Integer, LinkedList<Chip>>();
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
}