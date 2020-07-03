package com.kgivler.MonsterChase;

import java.util.Scanner;
import java.util.Random;

public class MonsterChase
{
    private static int mRow = 0; // Monster row
    private static int mCol = 0; // Monster column
    private static int row = 4; // your row
    private static int col = 4; // Your column
    private static Scanner in = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args)
    {
        for(int i = 0; i < 10; i++)
        {
            drawGrid();
            System.out.println("Move " + (i + 1));
            System.out.print("Direction? ");
            movePlayer(in.nextLine());
            moveMonster();
        }

        System.out.println("\nYou survivied!");
        gameOver();
    }

    private static void moveMonster()
    {
        int dir = -1;
        if (mRow == row && mCol < col)
            dir = 1;
        if (mRow > row && mCol < col)
            dir = 2;
        if (mRow > row && mCol == col)
            dir = 3;
        if (mRow > row && mCol > col)
            dir = 4;
        if (mRow == row && mCol > col)
            dir = 5;
        if (mRow < row && mCol > col)
            dir = 6;
        if (mRow < row && mCol == col)
            dir = 7;
        if (mRow < row && mCol < col)
            dir = 8;

        dir += (int)(3*random.nextDouble() - 1);
        if (dir == 0)
            dir = 8;
        if(dir == 9)
            dir = 1;
        if (dir > 1 && dir < 5)
            mRow--;
        if (dir > 5)
            mRow++;
        if(dir > 3 && dir < 7)
            mCol--;
        if(dir <3 || dir == 8)
            mCol++;

        // Monster stays in bounds   
        if(mRow == -1)
            mRow++;
        if(mCol == -1)
            mCol++;
        if(mRow == 5)
            mRow--;
        if(mCol == 5)
            mCol--;

        checkIfEaten();
    }

    private static void movePlayer(String dir)
    {   
        dir = dir.substring(0,1).toUpperCase();
        switch (dir)
        {
            case "N":
                row--;
                break;
            case "E":
                col++;
                break;
            case "S":
                row++;
                break;
            case "W":
                col--;
                break;
        }
        if(row > 4 || row < 0 || col > 4 || col < 0)
        {
            System.out.println("\nOut of bounds!");
            gameOver();
        }
        checkIfEaten();
    }

    private static void gameOver()
    {
        System.out.print("Play Again? ");
        if(in.nextLine().substring(0,1).toUpperCase().equals("Y"))
        {
            mRow = 0;
            mCol = 0;
            row = 4;
            col = 4;
            main(null);
        }
        System.exit(0);
    }

    private static void checkIfEaten()
    {
        if(row == mRow && col == mCol)
        {
            System.out.println("\nEaten!");
            gameOver();
        }
    }

    private static void drawGrid()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(i == mRow && j == mCol) 
                {
                    System.out.print("M");
                    continue;
                }
                if(i == row && j == col) 
                {
                    System.out.print("Y");
                    continue;
                }
                System.out.print(".");
            }
            System.out.println();
        }
    }
}