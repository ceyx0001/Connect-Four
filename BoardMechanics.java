import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class BoardMechanics {
    LinkedList<Chip> connectedFour = new LinkedList<Chip>();
    int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    final int R = 6;
    final int C = 7;
    Chip[][] board;

    public BoardMechanics() {
        board = new Chip[R][C];
    }

    public void insertCoin(Chip chip, int row, int column, int turn) {
        board[row][column] = chip;
        winner(column, row, turn);
    }

    public void winner(int initialX, int initialY, int turn) {
        connectedFour.add(board[initialY][initialX]);

        for (int directions = 0; directions < 8; directions++) {
            int k;
            int xDir = initialX + x[directions];
            int yDir = initialY + y[directions];
            for (k = 1; k < 4; k++) {
                if (xDir >= C || xDir < 0 || yDir >= R || yDir < 0) {
                    break;
                }
                if (board[yDir][xDir] != null) {
                    System.out.println(yDir + " " + xDir);
                    if (turn != board[yDir][xDir].getType()) {
                        break;
                    }
                    if (directions == 0 || directions == 7) {
                        connectedFour.add(board[yDir][xDir]);
                    } else if (directions == 1 || directions == 6) {
                        connectedFour.add(board[yDir][xDir]);
                    } else if (directions == 2 || directions == 5) {
                        connectedFour.add(board[yDir][xDir]);
                    } else if (directions == 3 || directions == 4) {
                        connectedFour.add(board[yDir][xDir]);
                    }

                    xDir += x[directions];
                    yDir += y[directions];
                }
            }
        }
        connectedFour.clear();
    }

}