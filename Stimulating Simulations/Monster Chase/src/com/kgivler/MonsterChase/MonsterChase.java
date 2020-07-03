/***
 * Adapted from "Stimulating Simulations" (C) 1977 C. William Engel. (C) 1979 by Hayden Book
 * Adapted from BASIC to Java by Kyle Givler 
 * https://github.com/JoyfulReaper/Java-Mini-Projects
 * 
 * MIT License
 *
 * Copyright (c) 2020 Kyle Givler
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * @author Kyle Givler
 *
 */
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
        showIntro();
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

    /***
     * Determine which direction to move the monster
     * and move the monster.
     */
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

    /**
     * Move the player in the given direction
     * @param dir The direction to move the player (N,S,E,W)
     */
    private static void movePlayer(String dir)
    {   
        // For now lets just skip there turn if input is invalid
        if(dir.length() < 1)
            return;

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

    /**
     * Determine if a new game is desired
     */
    private static void gameOver()
    {
        System.out.print("Play Again? ");
        if(in.nextLine().substring(0,1).toUpperCase().equals("Y")) // No error checking for now
        {
            mRow = 0;
            mCol = 0;
            row = 4;
            col = 4;
            main(null);
        }
        System.exit(0);
    }

    /**
     * Check to see if the player and the monster are at the same coordinates
     */
    private static void checkIfEaten()
    {
        if(row == mRow && col == mCol)
        {
            System.out.println("\nEaten!");
            gameOver();
        }
    }

    /**
     * Draw the playing field
     */
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

    /**
	 * Shows the introduction text
	 */
	private static void showIntro()
	{
		System.out.println("\nMonster Chase\n");
		System.out.println("Adapted from \"Stimulating Simulations\" (C) 1977 C. William Engel. (C) 1979 by Hayden Book");
		System.out.println("Adapted from BASIC into Java by:");
		System.out.println("Kyle Givler July 2020 - github.com/JoyfulReaper\n");
		
		System.out.println("You are locked in a cage with a hungry monster!");
		System.out.println("Don't get eaten!\n");
	}
}