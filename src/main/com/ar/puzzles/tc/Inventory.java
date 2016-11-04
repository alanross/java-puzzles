package com.ar.puzzles.tc;

/**
 * SRM 153 DIV 2
 *
 * @author Alan Ross
 */
public class Inventory
{
	public Inventory()
	{
		int[] sales1 = { 5 };
		int[] daysAvailable1 = { 15 };

		int[] sales2 = { 75, 120, 0, 93 };
		int[] daysAvailable2 = { 24, 30, 0, 30 };

		int[] sales3 = { 8773 };
		int[] daysAvailable3 = { 16 };

		int[] sales4 = { 1115, 7264, 3206, 6868, 7301 };
		int[] daysAvailable4 = { 1, 3, 9, 4, 18 };

		System.out.println( "10: " + monthlyOrder( sales1, daysAvailable1 ) + "\n" );
		System.out.println( "103: " + monthlyOrder( sales2, daysAvailable2 ) + "\n" );
		System.out.println( "16450: " + monthlyOrder( sales3, daysAvailable3 ) + "\n" );
		System.out.println( "36091: " + monthlyOrder( sales4, daysAvailable4 ) + "\n" );
	}

	public int monthlyOrder( int[] sales, int[] daysAvailable )
	{
		int months = 0;
		double sum = 0;

		for( int i = 0; i < sales.length; ++i )
		{
			if( daysAvailable[ i ] > 0 )
			{
				sum += ( sales[ i ] * 30.0 ) / daysAvailable[ i ];
				months++;
			}
		}

		return ( months != 0 ) ? ( int ) Math.ceil( ( sum - 1e-9 ) / months ) : 0;
	}
}