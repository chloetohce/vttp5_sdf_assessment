package vttp.batch5.sdf.task02;

import java.util.LinkedHashMap;
import java.util.Map;

public class UtilityEval {
    private final Board board;
    private final Map<Space, Integer> utility;
    private static final int[][] WIN_INDICES = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},    // Horizontals
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},    // Verticals
        {0, 4, 8}, {2, 4, 6}                // Diagonals
    };

    public UtilityEval(Board board) {
        this.board = board;
        this.utility = evaluate(board);
    }

    private static Map<Space, Integer> evaluate(Board board) {
        Map<Space, Integer> utility = new LinkedHashMap<>();
        //String player = board.getNextPlayer();
        Board newBoard = Board.copy(board);
        for (Space s : board.getEmptySpaces()) {
            newBoard.setMove(s, "X");
            System.out.println(s.getX() + " " + s.getY());
            utility.put(s, calcUtility(newBoard));
            newBoard.undoMove(s);
        }
        return utility;
    }

    private static int calcUtility(Board b) {
        int utilVal = Integer.MIN_VALUE;
        for (int[] combo : WIN_INDICES) {

            // Counting the number of X and O
            int numX = 0;
            int numO = 0;
            for (int pos : combo) {
                if (b.getSpaceVal(pos).equals("X")) {
                    numX++;
                } else if (b.getSpaceVal(pos).equals("O")){
                    numO++;
                }
            }
            //Getting utility
            if (numX == 3) {
                utilVal = Math.max(utilVal, 1);
            } else if (numO == 2 && numX != 1) {
                utilVal = Math.max(utilVal, -1);
            }
        }
        return utilVal > Integer.MIN_VALUE ? utilVal : 0;
    }

    public void printUtility() {
        for (Map.Entry<Space, Integer> e : utility.entrySet()) {
            Space s = e.getKey();
            int utilVal = e.getValue();
            System.out.printf("y=%d, x=%d, utility=%d\n", s.getY(), s.getX(), utilVal);
        }
    }
}
