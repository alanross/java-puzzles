package com.ar.puzzles.tc;

/**
 * SRM 156 DIV 2
 *
 * @author Alan Ross
 */
public class BenfordsLaw
{
	public BenfordsLaw()
	{
		int[] transactions1 = { 5236, 7290, 200, 1907, 3336, 9182, 17, 4209, 8746, 7932, 6375, 909, 2189, 3977, 2389, 2500, 1239, 3448, 6380, 4812 };
		int threshold1 = 2;

		int[] transactions2 = { 1, 10, 100, 2, 20, 200, 2000, 3, 30, 300 };
		int threshold2 = 2;

		int[] transactions3 = { 9, 87, 765, 6543, 54321, 43219, 321987, 21987, 1987, 345, 234, 123 };
		int threshold3 = 2;

		int[] transactions4 = { 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1 };
		int threshold4 = 8;

		int[] transactions5 = { 987, 234, 1234, 234873487, 876, 234562, 17, 7575734, 5555, 4210, 678234, 3999, 8123 };
		int threshold5 = 3;

		System.out.println( "1 " + questionableDigit( transactions1, threshold1 ) + "\n" );
		System.out.println( "2 " + questionableDigit( transactions2, threshold2 ) + "\n" );
		System.out.println( "-1 " + questionableDigit( transactions3, threshold3 ) + "\n" );
		System.out.println( "9 " + questionableDigit( transactions4, threshold4 ) + "\n" );
		System.out.println( "8 " + questionableDigit( transactions5, threshold5 ) + "\n" );
	}

	public int questionableDigit( int[] transactions, int threshold )
	{
		int numTransactions = transactions.length;

		int[] firstDigitCount = new int[ 10 ];

		for( int i = 0; i < numTransactions; ++i )
		{
			firstDigitCount[ getFirstDigit( transactions[ i ] ) ] += 1;
		}

		for( int i = 1; i < 10; ++i )
		{
			int c = firstDigitCount[ i ];

			double expected = Math.log10( 1.0 + 1.0 / i ) * numTransactions;

			if( c > threshold * expected || c < expected / threshold )
			{
				return i;
			}
		}

		return -1;
	}

	private int getFirstDigit( int n )
	{
		while( n < -9 || 9 < n )
		{
			n /= 10;
		}
		return Math.abs( n );
	}
}
