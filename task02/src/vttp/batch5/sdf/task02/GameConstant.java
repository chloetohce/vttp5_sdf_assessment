package vttp.batch5.sdf.task02;

public class GameConstant {
    public static final int NUM_ROW = 3;
    public static final int NUM_COL = 3;
    public static final String EMPTY = ".";
    public static final String PLAYER1 = "X";
    public static final String PLAYER2 = "O";
    public static String player;
    public static String opp;

    public static void setPlayer(String player) {
        GameConstant.player = player;
        GameConstant.opp = player.equals("X") ? "O" : "X";
    }

    public static int calcIndex(int x, int y) {
        return y * NUM_COL + x;
    }
}
