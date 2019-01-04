package GameComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
    public int numericValue;
    public String name;
    public List<Move> winsAgainst;
    public List<Move> losesAgains;

    public Move(int numericValue, String name) {
        this.numericValue = numericValue;
        this.name = name;

        this.winsAgainst = new ArrayList<>();
        this.losesAgains = new ArrayList<>();
    }

    public void addWinsAgainst(Move ... moves) {
        this.winsAgainst.addAll(Arrays.asList(moves));
    }

    public void addLosesAgainst(Move ... moves) {
        this.losesAgains.addAll(Arrays.asList(moves));
    }
}
