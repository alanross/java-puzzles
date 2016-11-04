package com.ar.puzzles.tc;

import java.util.ArrayList;

/**
 * http://www.cs.umd.edu/class/sum2003/cmsc311/Notes/Data/toBaseK.html
 * <p/>
 * SRM 150 DIV 2
 * <p/>
 * The digits 3 and 9 share an interesting property. If you take any multiple of 3
 * and sum its digits, you get another multiple of 3. For example, 118*3 = 354 and 3+5+4 = 12,
 * which is a multiple of 3. Similarly, if you take any multiple of 9 and sum its digits, you
 * get another multiple of 9. For example, 75*9 = 675 and 6+7+5 = 18, which is a multiple of 9.
 * Call any digit for which this property holds interesting, except for 0 and 1, for which the
 * property holds trivially.
 * A digit that is interesting in one base is not necessarily interesting in another base. For example,
 * 3 is interesting in base 10 but uninteresting in base 5. Given an int base, your task is to return
 * all the interesting digits for that base in increasing order. To determine whether a particular
 * digit is interesting or not, you need not consider all multiples of the digit.
 * You can be certain that, if the property holds for all multiples of the digit
 * with fewer than four digits, then it also holds for multiples with more digits.
 * For example, in base 10, you would not need to consider any multiples greater than 999.
 *
 * @author Alan Ross
 */
public class InterestingDigits
{
	public InterestingDigits()
	{
		System.out.println( "{3,9} " + digits( 10 ) + "\n" );
		System.out.println( "{2} " + digits( 3 ) + "\n" );
		System.out.println( "{2,4,8} " + digits( 9 ) + "\n" );
		System.out.println( "{5, 25} " + digits( 26 ) + "\n" );
	}

	public int[] digits( int base )
	{
		ArrayList<Integer> magic = new ArrayList<Integer>();

		int i;

		// for all single digits in the base
		for( i = 2; i <= base; ++i )
		{
			boolean interesting = true;

			// for all three digits of the base
			for( int j = 0; j < base * base * base; ++j )
			{
				int num = i * j;
				int d2 = num / ( base * base ); // int division 123 -> 1
				int d1 = ( num % ( base * base ) ) / base;     // 123 -> 2
				int d0 = ( num % ( base * base ) ) % base;    // 123 -> 3

				int sum = d2 + d1 + d0;

				if( sum % i != 0 )
				{
					interesting = false;
					break;
				}
			}

			if( interesting )
			{
				magic.add( new Integer( i ) );
			}
		}

		int[] result = new int[ magic.size() ];

		for( i = 0; i < magic.size(); ++i )
		{
			result[ i ] = ( int ) magic.get( i );
		}

		return result;
	}

	private int[] convertFromBase10ToBaseK( int num, int base )
	{
		int[] digits = new int[ 255 ];
		int index = -1;
		int remainder = 0;

		while( num != 0 )
		{
			remainder = num % base;      // assume base > 1
			num = num / base;          // integer division
			digits[ ++index ] = remainder;
		}

		return digits;
	}
}