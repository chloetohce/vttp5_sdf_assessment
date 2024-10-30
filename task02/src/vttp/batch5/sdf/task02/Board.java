package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int NUM_ROW = 3;
    private static final int NUM_COL = 3;
    private static final int NUM_SPACES = NUM_COL * NUM_ROW;
    private final List<Space> board;

    private Board(List<Space> board) {
        this.board = board;
    }

    public static Board of(File f) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String row = "";
        List<String> boardVal = new ArrayList<>();
        while ((row = reader.readLine()) != null) {
            String[] rowArr = row.split("");
            for (int i = 0; i < rowArr.length; i++) {
                boardVal.add(rowArr[i]);
            }
        }
        reader.close();

        List<Space> board = new ArrayList<>();
        for (int y = 0; y < NUM_ROW; y++) {
            for (int x = 0; x < NUM_COL; x++) {
                int pos = y * NUM_COL + x;
                board.add(new Space(x, y, boardVal.get(pos)));
            }
        }

        return new Board(board);
    }

    public static Board copy(Board other) {
        return new Board(other.board);
    }

    public void printBoard() {
        for (int y = 0; y < NUM_ROW; y++) {
            for (int x = 0; x < NUM_COL; x++) {
                int pos = y * NUM_COL + x;
                System.out.print(board.get(pos).getVal());
            }
            System.out.println();
        }
    }

    public String getNextPlayer() {
        // Checks how many X's and O's are on the board and returns the next player
        // Assumes that X always goes first
        int numX = 0;
        int numO = 0;
        for (Space s : board) {
            if (s.getVal().equals("X")) {
                numX++;
            } else if (s.getVal().equals("O")) {
                numO++;
            }
        }
        return numX - numO >= 0 ? "X" : "O";
    }

    public List<Space> getEmptySpaces() {
        List<Space> emptySpaces = new ArrayList<>();
        for (Space s : board) {
            if (s.isEmpty()) {
                emptySpaces.add(s);
            }
        }
        return emptySpaces;
    }

    public void setMove(Space space, String value) {
        space.setVal(value);
        int pos = space.getY() * NUM_COL + space.getX();
        board.set(pos, space);
    }

    public void undoMove(Space space) {
        space.setVal(".");
        int pos = space.getY() * NUM_COL + space.getX();
        board.set(pos, space);
    }

    public String getSpaceVal(int index) {
        return board.get(index).getVal();
    }
}
