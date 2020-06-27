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
package com.kgivler.ArtAuction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class ArtAuction 
{
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		int profit = 0;
		int numberOfPaintings = 5;
		ArrayList<Painting> paintings = createPaintings(numberOfPaintings); 
		
		showIntro();
		buyPaintings(paintings);
		profit = sellPaintings(paintings);
		
		System.out.println("Your profit is: $" + profit);
		System.out.print("Would you like to play again? ");
		if(in.next().toUpperCase().startsWith("Y"))
			main(null);
		
		in.close();
		System.exit(0);
	}

	/**
	 * Buy Paintings at auction
	 * @param paintings The paintings for sale
	 */
	private static void buyPaintings(ArrayList<Painting >paintings) {
		System.out.println("-------- Buying Paintings --------\n");
		
		for(int i = 0; i < paintings.size(); i++ )
		{
			System.out.printf("Bidding on painting #%d\n", i + 1);
			System.out.printf("Price range: (Low / Mean / High) %d %d %d\n",
					paintings.get(i).getPriceRange()[0],
					paintings.get(i).getPriceRange()[1],
					paintings.get(i).getPriceRange()[2]);
			
			// Loop until a valid bid is input
			int bid = -1;
			do {
				System.out.print("What is your bid? ");
				bid = readIntFromConsole();
			} while (bid < 0);
			
			int compBid = Auction.bidOnPainting(bid, paintings.get(i));
			System.out.println("Your opponent has bid $" + compBid);
			if(bid > compBid)
				System.out.println("You won the auction!\n");
			else
				System.out.println("You were outbid!\n");
		}
		
	}

	/**
	 * Sell Paintings at auction
	 * @param paintings The paintings for sale
	 */
	private static int sellPaintings(ArrayList<Painting >paintings) 
	{
		Random random = new Random();
		int numberOfOffers = random.nextInt(6);
		int profit = 0;
		
		System.out.println("-------- Selling Paintings --------");
		
		for(int i = 0; i < paintings.size(); i++)
		{
			if(!paintings.get(i).getOwned())
				continue;
			
			System.out.println("\nSelling Painting #" + (i + 1));
			System.out.println("You bought it for $" + paintings.get(i).getPricePaid());
			System.out.println("The average offer is: $" + (paintings.get(i).getPrice() + 50));
			for(int j = 0; j < numberOfOffers; j++)
			{
				int offer = Auction.getOfferOnPainting(paintings.get(i));
				System.out.println("Offer: #" + (j + 1) + " is " + offer);
				System.out.print("Accept this offer? ");
				if(in.next().toUpperCase().startsWith("Y"))
				{
					paintings.get(i).setOwned(false);
					profit += offer - paintings.get(i).getPricePaid();
					break;
				}
				System.out.println();
			}
		}
		return profit;
	}

	/**
	 * Shows the introduction text
	 */
	private static void showIntro()
	{
		System.out.println("\nArt Auction\n");
		System.out.println("Adapted from \"Stimulating Simulations\" (C) 1977 C. William Engel. (C) 1979 by Hayden Book");
		System.out.println("Adapted from BASIC into Java by:");
		System.out.println("Kyle Givler April 2020 - github.com/JoyfulReaper\n");
		
		System.out.println("Buy paintings at auction and attempt to re-sell them at a higher price.");
		System.out.println("The 3 numbers represent the mean and range of the bids. About 70% of the bids will be in this range.\n");
	}
	
	/**
	 * Create the paintings to be used in this game
	 * @param num The number of paintings to create
	 * @return An ArrayList containing the paintings
	 */
	private static ArrayList<Painting> createPaintings(int num)
	{
		ArrayList<Painting> paintings = new ArrayList<Painting>(); 
		for(int i = 0; i < num; i++)
			paintings.add(new Painting());
		
		return paintings;
	}
	
	/***
	 * Read an int from the console
	 * @return int entered at the console
	 */
	public static int readIntFromConsole()
	{
		int input = -1;
		try {
			input = Integer.parseInt(in.nextLine());
		} catch (InputMismatchException e) {
			in.nextLine();
		} catch (NumberFormatException e)
		{
		}
		return input;
	}
}
