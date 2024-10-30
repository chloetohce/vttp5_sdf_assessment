package vttp.batch5.sdf.task02;

import java.util.LinkedHashMap;
import java.util.Map;
import vttp.batch5.sdf.task02.board.*;

public class UtilityEval {
    private final Map<Space, Integer> utility;
    private static final int[][] WIN_INDICES = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},    // Horizontals
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},    // Verticals
        {0, 4, 8}, {2, 4, 6}                // Diagonals
    };

    public UtilityEval(Board board) {
        this.utility = evaluate(board);
    }

    private static Map<Space, Integer> evaluate(Board board) {
        Map<Space, Integer> utility = new LinkedHashMap<>();
        Board newBoard = Board.copy(board);

        for (Space s : board.getEmptySpaces()) {
            newBoard.setMove(s, GameConstant.player);
            utility.put(s, calcUtility(newBoard));
            newBoard.undoMove(s);
        }
        return utility;
    }

    private static int calcUtility(Board b) {
        int utilVal = Integer.MIN_VALUE;
        for (int[] combo : WIN_INDICES) {

            // Counting the number of player vs opp
            int numPlayer = 0;
            int numOpp = 0;
            for (int pos : combo) {
                if (b.getSpaceVal(pos).equals(GameConstant.player)) {
                    numPlayer++;
                } else if (b.getSpaceVal(pos).equals(GameConstant.opp)){
                    numOpp++;
                }
            }
            //Getting utility
            if (numPlayer == 3) {
                utilVal = Math.max(utilVal, 1);
            } else if (numOpp == 2 && numPlayer != 1) {
                utilVal = Math.max(utilVal, -1);
            }
        }
        return utilVal > Integer.MIN_VALUE ? utilVal : 0;
    }

    public void printUtility() {
        if (GameConstant.player.equals(GameConstant.PLAYER2)) {
            System.out.println("Next player's move is: " + GameConstant.player);
            System.out.println("Showing utility for " + GameConstant.player);
            System.out.println("------------------------------------------------");
        }
        for (Map.Entry<Space, Integer> e : utility.entrySet()) {
            Space s = e.getKey();
            int utilVal = e.getValue();
            System.out.printf("%s, utility=%d\n", s.toString(), utilVal);
        }
    }
}
