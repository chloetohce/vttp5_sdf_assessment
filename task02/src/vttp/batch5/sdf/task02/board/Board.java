package vttp.batch5.sdf.task02.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vttp.batch5.sdf.task02.GameConstant;

public class Board {
    private final List<Space> board;

    private Board(List<Space> board) {
        this.board = board;
        GameConstant.setPlayer(this.getNextPlayer());
    }

    public static Board of(File f) throws IOException {
        //Get and store all values from input file
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String row = "";
        List<String> boardVal = new ArrayList<>();
        while ((row = reader.readLine()) != null) {
            String[] rowArr = row.split("");
            boardVal.addAll(Arrays.asList(rowArr));
        }
        reader.close();
        
        // Pass the values into the board
        List<Space> board = new ArrayList<>();
        for (int y = 0; y < GameConstant.NUM_ROW; y++) {
            for (int x = 0; x < GameConstant.NUM_COL; x++) {
                int pos = calcIndex(x, y);
                board.add(new Space(x, y, boardVal.get(pos)));
            }
        }
        
        return new Board(board);
    }
    
    public static Board copy(Board other) {
        return new Board(other.board);
    }
    
    private String getNextPlayer() {
        // Checks how many X's and O's are on the board and returns the next player
        // Assumes that X always goes first
        int num1 = 0;
        int num2 = 0;
        for (Space s : board) {
            if (s.getVal().equals(GameConstant.PLAYER1)) {
                num1++;
            } else if (s.getVal().equals(GameConstant.PLAYER2)) {
                num2++;
            }
        }
        return num1 - num2 > 0 ? GameConstant.PLAYER2 : GameConstant.PLAYER1;
    }

    public void printBoard() {
        for (int y = 0; y < GameConstant.NUM_ROW; y++) {
            for (int x = 0; x < GameConstant.NUM_COL; x++) {
                int pos = calcIndex(x, y);
                System.out.print(board.get(pos).getVal());
            }
            System.out.println();
        }
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
        int pos = calcIndex(space.getX(), space.getY());
        board.set(pos, space);
    }

    public void undoMove(Space space) {
        space.setVal(GameConstant.EMPTY);
        int pos = calcIndex(space.getX(), space.getY());
        board.set(pos, space);
    }

    public String getSpaceVal(int index) {
        return board.get(index).getVal();
    }

    private static int calcIndex(int x, int y) {
        return y * GameConstant.NUM_COL + x;
    }
}
