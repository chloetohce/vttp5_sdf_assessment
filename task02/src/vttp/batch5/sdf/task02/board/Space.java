package vttp.batch5.sdf.task02.board;

import vttp.batch5.sdf.task02.GameConstant;

public class Space {
    private final int x;
    private final int y;
    private String val;

    public Space(int x, int y, String val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getVal() {
        return val;
    }

    public boolean isEmpty() {
        return val.equals(GameConstant.EMPTY);
    }

    public void setVal(String val) {
        this.val = val;
    }

}
