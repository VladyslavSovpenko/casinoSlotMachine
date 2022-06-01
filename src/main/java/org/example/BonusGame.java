package org.example;

public class BonusGame {

    public static void main(String[] args) {
        BonusGame bonusGame = new BonusGame();
        System.out.println(bonusGame.isBonusGame(5));
    }

    //    возвращает сумму символов на барабане
    int countOnReel(int reelId, int symbolId) {
        return 1;
    }

    //  возвращает сумму символов в строке
    int countOnRow(int rowId, int symbolId) {
        return 3;
    }

    //  возвращает сумму символов на экране
    int countOnScreen(int symbolId) {
        return 4;
    }

    public boolean isBonusGame(int symbolId) {
        if (atLeastFiveSymbols(symbolId)) {
            return true;
        } else if (reelBonus(symbolId)) {
            return true;
        } else if (threeSymbols(symbolId)) {
            return true;
        } else return false;
    }

    public boolean atLeastFiveSymbols(int symbolId) {
        int sum = 0;
        sum = countOnScreen(symbolId);
        if (sum >= 5) {
            return true;
        }
        return false;
    }

    public boolean reelBonus(int symbolId) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum = countOnReel(i, symbolId);
            if (sum == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean threeSymbols(int symbolId) {
        int sumHorizon = countOnRow(1, symbolId);
        int sumVert = 0;
        boolean vertCorrect = false;
        for (int i = 0; i < 5; i = i + 2) {
            sumVert = countOnReel(i, symbolId);
            if (sumVert == 0) {
                vertCorrect = false;
            } else {
                vertCorrect = true;
            }
        }
        if (sumHorizon == 3 && vertCorrect) {
            return true;
        }
        return false;
    }
}
