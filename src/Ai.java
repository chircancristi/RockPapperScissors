import GameComponents.Move;

import java.util.*;

public class Ai {
    public List<Move> playerMoves;
    public List<Move> aiMoves;
    public LinkedList<Move> aiLastSixMoves;
    public List<Move> frequentPattern;

    public int didIWin;
    public int playerRepeatedMoves;
    public int patternLocation;

    Ai() {
        this.aiMoves = new ArrayList<>();
        this.playerMoves = new ArrayList<>();
        this.frequentPattern = new ArrayList<>();
        this.aiLastSixMoves = new LinkedList<>();//player*LastSixMoves
        this.playerRepeatedMoves = 0;
        this.didIWin = 0;
        this.patternLocation = -1;
    }

    public void checkRepeatedMoves(int lastMoveIndex) {
        if (this.playerMoves.get(lastMoveIndex).name.equals(this.playerMoves.get(lastMoveIndex - 1).name)) {
            playerRepeatedMoves++;
        } else {
            playerRepeatedMoves = 1;
        }
    }

    public int pickFromLastRun(int lastMoveIndex) {
        if (this.didIWin == -1) {
            return pickWinerAgains(this.playerMoves.get(lastMoveIndex));
        } else if (this.didIWin == 1) {
            return this.playerMoves.get(lastMoveIndex).numericValue;
        } else {
            return pickLoserAgains(this.playerMoves.get(lastMoveIndex));
        }
    }

    public boolean comparePatterns(LinkedList<Move> posiblePattern, LinkedList<Move> checkPattern) {
        for (int i = 0; i < posiblePattern.size(); i++) {
            if (posiblePattern.get(i).numericValue != checkPattern.get(i).numericValue)
                return false;
        }
        return true;
    }

    public void createPatterns() {
        LinkedList<Move> posiblePattern = new LinkedList<>();
        LinkedList<Move> checkPattern = new LinkedList<>();
        int numberOfRepeatedPatterns = 0;
        int j;
        int k;
        int currentEndIndexOfPatternInLastSixMoves;
        for (int patternLength = 2; patternLength <= 3; patternLength++) {
            posiblePattern.clear();
            checkPattern.clear();

            /*for (j = 0; j < i; j++) {
                posiblePattern.add(this.aiLastSixMoves.get(j));
                //checkPattern.add(this.aiLastSixMoves.get(j));
            }*/

            posiblePattern.addAll(computePattern(patternLength, 0, aiLastSixMoves));

            currentEndIndexOfPatternInLastSixMoves = patternLength - 1;
            int currentPatternFrequency = 1;

            for (j = patternLength; j < this.aiLastSixMoves.size() - patternLength + 1; j++) {

                currentPatternFrequency = 1;

                for (k = j; k < this.aiLastSixMoves.size() - patternLength + 1; k++) {
                    checkPattern.clear();
                    checkPattern.addAll(computePattern(patternLength, k, aiLastSixMoves));
                    if (comparePatterns(checkPattern, posiblePattern)) {
                        currentPatternFrequency++;
                    }

                }
                if (currentPatternFrequency >= numberOfRepeatedPatterns && currentPatternFrequency > 1) {
                    numberOfRepeatedPatterns = currentPatternFrequency;
                    this.frequentPattern.clear();
                    this.frequentPattern.addAll(posiblePattern);
                }

                posiblePattern.clear();
                posiblePattern.addAll(computePattern(patternLength, currentEndIndexOfPatternInLastSixMoves, aiLastSixMoves));
                currentEndIndexOfPatternInLastSixMoves++;
                /*posiblePattern.removeFirst();
                posiblePattern.add(aiLastSixMoves.get(j));*/
            }
        }
    }

    private LinkedList<Move> computePattern(int patternLength, int startPosition, LinkedList<Move> targetList) {
        LinkedList resultedList = new LinkedList<Move>();

        for (int i = 0; i < patternLength; i++) {
            resultedList.add(targetList.get(startPosition + i));
        }

        return resultedList;
    }

    public void checkPatternPosition(int lastMoveIndex) {
        if(this.patternLocation == -1){
            if (this.frequentPattern.size() != 0){
                this.patternLocation = 0;
            }
            if (this.playerMoves.get(lastMoveIndex).numericValue != this.frequentPattern.get(this.patternLocation).numericValue) {
                patternLocation = -1;
                frequentPattern.clear();
            }
        } else {
            if(this.frequentPattern.size() != 0){
                if (patternLocation + 1 == this.frequentPattern.size()){
                    this.patternLocation = 0;
                } else {
                    this.patternLocation++;
                }
                if (this.playerMoves.get(lastMoveIndex).numericValue != this.frequentPattern.get(this.patternLocation).numericValue) {
                    patternLocation = -1;
                    frequentPattern.clear();
                }
            }
        }


        /*if (this.patternLocation != -1) {
            if (this.playerMoves.get(lastMoveIndex).numericValue == this.frequentPattern.get(this.patternLocation).numericValue) {
                if (patternLocation + 1 == this.frequentPattern.size())
                    patternLocation = 0;
                else patternLocation++;
            } else {
                patternLocation = -1;
            }

        } else {
            if (this.playerMoves.get(lastMoveIndex).numericValue == this.frequentPattern.get(0).numericValue)
                this.patternLocation = 0;
        }*/
    }

    public int pickMove() {
        if (this.aiMoves.size() == 0) {
            return pickRandom();
        }

        int lastMoveIndex = this.playerMoves.size() - 1;


        if (this.frequentPattern.size() >= 2)
            this.checkPatternPosition(lastMoveIndex);

        if (patternLocation != -1) {
            int chooseMove;
            if (patternLocation + 1 >= frequentPattern.size())
                chooseMove = 0;
            else chooseMove = patternLocation + 1;
            return pickWinerAgains(this.frequentPattern.get(chooseMove));
        }

        if (this.aiLastSixMoves.size() >= 4)
            createPatterns();

        if (this.playerMoves.size() > 2) {
            checkRepeatedMoves(lastMoveIndex);
        }
        if (playerRepeatedMoves >= 3) {
            return pickWinerAgains(this.playerMoves.get(lastMoveIndex));
        }

        return pickFromLastRun(lastMoveIndex);

    }

    private int pickLoserAgains(Move move) {
        return move.winsAgainst.get(0).numericValue;
    }

    private int pickWinerAgains(Move move) {
        return move.losesAgains.get(0).numericValue;
    }

    private int pickRandom() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}

