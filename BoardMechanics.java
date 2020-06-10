import java.util.ArrayList;

public class BoardMechanics {
    /**
	 *  arrays for the 8 directions to search the board
	 */
    int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    final int R = 6;
    final int C = 7;
    int moves = 42;
    Chip[][] board;

    public BoardMechanics() {
        board = new Chip[R][C];

    }

    public void insertCoin(Chip chip, int row, int column, int turn) {
        moves--;
        board[row][column] = chip; // store a chip 
    }

    /**
	 *  check if there is a draw
	 */
    public boolean checkDraw() {
        System.out.println("Moves left: " + moves); // display the number of moves left
        if (moves == 0) { // when moves reach 0, there is a draw
            return true;
        }
        return false;
    }

    /**
	 *  method to check win
     * 
     *  checks the board based on the inserted chip's location
     *  thus, initialX & initialY
	 */

    public boolean checkWin(int initialX, int initialY, int turn) {
        // store chips in arraylist if they fall in one of the 4 directions
        ArrayList<Integer> horizontal = new ArrayList<Integer>();
        ArrayList<Integer> vertical = new ArrayList<Integer>();
        ArrayList<Integer> slopeDown = new ArrayList<Integer>();
        ArrayList<Integer> slopeUp = new ArrayList<Integer>();

        // add the first chip's location to each arraylist of directions
        horizontal.add(board[initialY][initialX].getType());
        vertical.add(board[initialY][initialX].getType());
        slopeDown.add(board[initialY][initialX].getType());
        slopeUp.add(board[initialY][initialX].getType());

        // go through 8 directions
        for (int directions = 0; directions < 8; directions++) {
            // xDir = the new x value after going to a certain direction
            // yDir = the new y value after going to a certain direction
            int xDir = initialX + x[directions];
            int yDir = initialY + y[directions];
            // only need to check 3 chips for win
            for (int k = 1; k < 4; k++) {
                // if going to a direction is out of bounds, then break and check another direction
                if (xDir >= C || xDir < 0 || yDir >= R || yDir < 0) {
                    break;
                }
                // null = there is no chip there
                // if the slot in said direction has a chip in it, then get its location
                if (board[yDir][xDir] != null) {
                    if (turn != board[yDir][xDir].getType()) { // check if its the same type as the first chip
                        break; // otherwise check another direction
                    }

                    /**
	                *   add chip to corresponding arraylists
	                */
                    if (directions == 0 || directions == 7) {
                        slopeDown.add(board[yDir][xDir].getType());
                    } else if (directions == 1 || directions == 6) {
                        horizontal.add(board[yDir][xDir].getType());
                    } else if (directions == 2 || directions == 5) {
                        slopeUp.add(board[initialY][initialX].getType());
                    } else if (directions == 3 || directions == 4) {
                        vertical.add(board[initialY][initialX].getType());
                    }

                    // if any of the arraylists has a size of 4, then that means there is a 4 in a row
                    // game will end
                    if (horizontal.size() == 4 || vertical.size() == 4 || slopeUp.size() == 4
                            || slopeDown.size() == 4) {
                        // clear each arraylists for next game
                        horizontal.clear();
                        vertical.clear();
                        slopeDown.clear();
                        slopeUp.clear();
                        // clear the board for next game
                        for (int r = 0; r < R; r++) {
                            for (int c = 0; c < C; c++) {
                                board[r][c] = null;
                            }
                        }
                        // reset the number of moves
                        moves = 42;
                        return true;
                    }
                    // change directions
                    xDir += x[directions];
                    yDir += y[directions];
                }
            }
        }
        return false;
    }
}