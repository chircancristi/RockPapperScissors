package GameComponents;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    public List<Move> rules;

    public Rules(){
        this.rules = new ArrayList<>();

        Move rock = new  Move(1, "rock");
        Move paper = new Move(2, "paper");
        Move scissors = new Move(3, "scissors");

        rock.addWinsAgainst(scissors);
        rock.addLosesAgainst(paper);

        paper.addWinsAgainst(rock);
        paper.addLosesAgainst(scissors);

        scissors.addWinsAgainst(paper);
        scissors.addLosesAgainst(rock);

        this.rules.add(rock);
        this.rules.add(paper);
        this.rules.add(scissors);
    }

    public int joust (Move move1, Move move2) {
        if (move1.winsAgainst.contains(move2)){
            return 1;
        } else if (move1.losesAgains.contains(move2)) {
            return -1;
        } else {
            return 0;
        }
    }

    public Move getMoveWithNumericValue (int numeric) {
        for (Move move: this.rules) {
            if (move.numericValue == numeric){
                return move;
            }
        }

        return null;
    }

    public Move getMoveWithName (String name) {
        for (Move move: this.rules) {
            if (move.name.equals(name)){
                return move;
            }
        }
        return null;
    }
}
