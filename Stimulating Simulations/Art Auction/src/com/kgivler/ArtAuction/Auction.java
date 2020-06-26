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

import java.util.Objects;
import java.util.Random;

public final class Auction 
{
	private static Random random = new Random();
	
	private Auction() {}
	
	/**
	 * I have no idea how this works. I translated it as accurately as
	 * I could from BASIC to Java
	 * 
	 * It determines how much the computer bids on paintings
	 * 
	 * @param price The price of the painting
	 * @param range The price range of the painting
	 * @return the bid
	 */
	private static int normalDistribution(int price, int priceVariance)
	{
		double dividend = 0;
		double number = (int)(65536 * random.nextDouble());
		for(int i = 1; i <= 16; i++)
		{
			double quotient = (int)number / 2;
			dividend += 2 * (number / 2 - quotient);
			number = quotient;
		}
		double output = price + priceVariance * (dividend - 8) / 8;
		output += 20 * random.nextDouble();
		return (int)output;
	}
	
	/**
	 * Place a bid on a painting.
	 * If the bid is successful marks the painting as owned and records
	 * the amount paid for the painting
	 * 
	 * @param bid Amount to bid on the painting
	 * @param painting The painting to bid on
	 * @return The amount that the opponent bid on the painting
	 */
	public static int bidOnPainting(int bid, Painting painting)
	{
		Objects.requireNonNull(painting, "Painting cannot be null");
		
		int computerBid = normalDistribution(painting.getPrice(), painting.getPriceVariance());
		
		if(bid > computerBid)
		{
			painting.setOwned(true);
			painting.setPricePaid(bid);
		}
			
		return computerBid;
	}
	
	public static int getOfferOnPainting(Painting painting)
	{
		return normalDistribution(painting.getPrice(), painting.getPriceVariance()) + 100 * (int)random.nextDouble();
	}
}
