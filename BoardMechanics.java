import java.util.ArrayList;
import java.util.LinkedList;

public class BoardMechanics {
    LinkedList<Chip> connectedFour = new LinkedList<Chip>();
    int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    final int R = 6;
    final int C = 7;
    Chip[][] board;
    /*int[][] boardRed;
    int[][] boardYellow;
    long TOP = 0b0000000001000000100000010000001000000000000000000L;
    private static long[] bitBoard = new long[2];
    private static int[] height = {0, 0, 0, 0, 0, 0, 0};*/

    public BoardMechanics() {
        board = new Chip[R][C];
        /*boardRed = new int[R][C];
        boardYellow = new int[R][C];*/
    }


    public void insertCoin(Chip chip, int row, int column, int turn) {
        board[row][column] = chip;
        /*long move = 1L << height[column]++;
        bitBoard[turn-1] ^= move; 
        if(isWin(bitBoard[turn-1])) {
            System.out.println(true);
        }*/
    }

    /*public boolean isWin(long bitboard) {
        int[] directions = { 1, 7, 6, 8 };
        long bb;
        for (int direction : directions) {
            bb = bitboard & (bitboard >> direction);
            if ((bb & (bb >> (2 * direction))) != 0)
                return true;
        }
        return false;
    }*/

    public boolean checkWin(int initialX, int initialY, int turn) {
        ArrayList<Integer>horizontal= new ArrayList<Integer>();
        ArrayList<Integer>vertical= new ArrayList<Integer>();
        ArrayList<Integer>slopeDown= new ArrayList<Integer>();
        ArrayList<Integer>slopeUp= new ArrayList<Integer>();
        
        horizontal.add(board[initialY][initialX].getType());
        vertical.add(board[initialY][initialX].getType());
        slopeDown.add(board[initialY][initialX].getType());
        slopeUp.add(board[initialY][initialX].getType());

        for (int directions = 0; directions < 8; directions++) {
            int xDir = initialX + x[directions];
            int yDir = initialY + y[directions];
            for (int k = 1; k < 4; k++) {
                if (xDir >= C || xDir < 0 || yDir >= R || yDir < 0) {
                    break;
                }
                if (board[yDir][xDir] != null) {
                    if (turn != board[yDir][xDir].getType()) {
                        break;
                    }

                    if (directions == 0 || directions == 7) {
                        slopeDown.add(board[yDir][xDir].getType());
                    } else if (directions == 1 || directions == 6) {
                        horizontal.add(board[yDir][xDir].getType());
                    } else if (directions == 2 || directions == 5) {
                        slopeUp.add(board[initialY][initialX].getType());
                    } else if (directions == 3 || directions == 4) {
                        vertical.add(board[initialY][initialX].getType());
                    }
                    
                    if (horizontal.size() == 4|| vertical.size() == 4 || slopeUp.size() == 4 || slopeDown.size() == 4) {
                        horizontal.clear();
                        vertical.clear();
                        slopeDown.clear();
                        slopeUp.clear();
                        for (int r = 0; r < R; r++) {
                            for (int c = 0; c < C; c++) {
                                board[r][c] = null;
                            }
                        }
                        return true;
                    }

                    xDir += x[directions];
                    yDir += y[directions];
                }
            }
        }
        return false;
    }
}