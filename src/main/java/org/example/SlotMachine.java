package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class SlotMachine {
    private int numOfReels;
    private int selectionsPerReels;
    private int costPerReels;
    private int balance;
    private int viewBox;


    public SlotMachine(int reels, int selectionsPerReels, int costPerReels, int balance, int viewBox) {
        this.numOfReels = reels;
        this.selectionsPerReels = selectionsPerReels;
        this.costPerReels = costPerReels;
        this.balance = balance;
        this.viewBox = viewBox;
        boolean canPlay = true;
        if (this.numOfReels < 3) {
            System.out.println("Need to select more Reels");
            canPlay = false;
        }
        if (this.selectionsPerReels < 2) {
            System.out.println("Need to increase number Selection");
            canPlay = false;
        }
        if (this.viewBox % 2 == 0) {
            System.out.println("Need to have an odd number of lines in the view book");
            canPlay = false;
        }
        System.out.println(intro());
        while (canPlay) {
            spin();
        }
    }

    public static void main(String[] args) {
        SlotMachine slotMachine = new SlotMachine(3, 3, 1, 100, 3);

    }

    private String intro() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to SlotMachine\n");
        builder.append("Number of reels: " + this.numOfReels + "\n");
        builder.append("Sections per reels: " + this.selectionsPerReels + "\n");
        builder.append("Cost per Pay Line: " + this.costPerReels * this.numOfReels + "\n");
        builder.append("Your balance: " + this.balance + "\n");
        return builder.toString();
    }

    private void spin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a number of pay lines and press enter to play");
        int spinCost = scanner.nextInt() * this.costPerReels;
        if (spinCost <= this.balance) {
            int[][] spinResult = getSpinResult();
            for (int i = 0; i < this.viewBox; i++) {
                System.out.println(Arrays.toString(spinResult[i]));
            }
            this.balance -= spinCost;
            if (checkWinners(spinResult)) {
                System.out.println("!!!YOU WIN!!!");
                System.out.println("Your balance is: " + balance + " + " + spinCost * 5);
                this.balance = balance + costPerReels * 5;
                System.out.println("Your balance is: " + balance);
            } else {
                System.out.println("Your balance is: " + balance);
            }

        } else {
            System.out.println("Please, pay money");
        }
    }

    private boolean checkWinners(int[][] spinResults) {
        if (payLineWinner(spinResults)) {

            return true;
        }
        return false;
    }

    private boolean payLineWinner(int[][] spinResults) {
        int value;
        boolean isPayLine = false;
        for (int i = 0; i < spinResults.length; i++) {
            value = spinResults[i][0];
            for (int j = 1; j < spinResults.length; j++) {
                if (value == spinResults[i][j]) {
                    isPayLine = true;
                } else {
                    isPayLine = false;
                    break;
                }
            }
            if (isPayLine == true) {
                return true;
            }
        }

        return isPayLine;
    }


    private int[][] getSpinResult() {
        int[][] spinResults = new int[this.viewBox][this.numOfReels];
        for (int j = 0; j < spinResults[0].length; j++) {
            spinResults[0][j] = (int) (Math.floor(Math.random() * 10) % this.selectionsPerReels);

            for (int i = 1; i < spinResults.length; i++) {
                spinResults[i][j] = (spinResults[i - 1][j] + 1) % this.selectionsPerReels;

            }
        }
        return spinResults;
    }
}
