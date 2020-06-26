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

import java.util.Random;

/***
 * Represent a painting
 * @author me
 *
 */
public class Painting {
	public static final Random random = new Random();
	private int price;
	private int priceVariance;
	private int pricePaid;
	private int[] priceRange = new int[3];
	private Boolean owned;
	private String name;
	
	/**
	 * Construct a painting
	 * Sets the paintings price, price variance and price range
	 */
	public Painting()
	{
		owned = false;
		price = 100 + (int)(900 * random.nextDouble());
		priceVariance = (int)(price * random.nextDouble());
		if(price < 500)
			priceVariance = (int)(price * 0.7 * random.nextDouble());
		
		priceRange[0] = (int)(price - 0.5 * priceVariance); // Low
		priceRange[1] = price; // Median
		priceRange[2] = (int)(price + 0.5 * priceVariance); // High
	}
	
	/**
	 * Controls if the painting is owned or not
	 * @param owned True if owned, false if not owned
	 */
	public void setOwned(Boolean owned)
	{
		this.owned = owned;
	}
	
	/**
	 * Determine if the painting is owned
	 * @return Wether or not the painting is owned
	 */
	public Boolean getOwned()
	{
		return this.owned;
	}
	
	/**
	 * Set the amount paid for the painting
	 * @param pricePaid The amount paid
	 */
	public void setPricePaid(int pricePaid)
	{
		if(price < 0)
			throw new IllegalArgumentException("pricePaid can not be negative");
		this.pricePaid = pricePaid;
	}
	
	/**
	 * Get the base price of the painting
	 * @return The base price of the painting
	 */
	public int getPrice()
	{
		return price;
	}
	
	/**
	 * 
	 * @return Amount paid for the painting
	 */
	public int getPricePaid()
	{
		return pricePaid;
	}
	
	/**
	 * 
	 * @return price variance
	 */
	public int getPriceVariance()
	{
		return priceVariance;
	}
	
	/**
	 * Array consiting of the price range for this painting:
	 * (70% of the time the price will be in this range)
	 * 
	 * index 0: Lower price range of the painting 
	 * index 1: Median price
	 * index 2: Higher price range of the painting
	 * @return The price range
	 */
	public int[] getPriceRange()
	{
		return priceRange;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}