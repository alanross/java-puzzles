package com.ar.puzzles.tc;

/**
 * @author Alan Ross
 */
public class BigBurger
{
	public BigBurger()
	{
		int[] arrival0 = { 3, 3, 9 };
		int[] service0 = { 2, 15, 14 };

		int[] arrival1 = { 182 };
		int[] service1 = { 11 };

		int[] arrival2 = { 2, 10, 11 };
		int[] service2 = { 3, 4, 3 };

		int[] arrival3 = { 2, 10, 12 };
		int[] service3 = { 15, 1, 15 };

		System.out.println( "11 " + maxWait( arrival0, service0 ) + "\n" );
		System.out.println( "0 " + maxWait( arrival1, service1 ) + "\n" );
		System.out.println( "3 " + maxWait( arrival2, service2 ) + "\n" );
		System.out.println( "7 " + maxWait( arrival3, service3 ) + "\n" );
	}

	public int maxWait( int[] arrival, int[] service )
	{
		int waitTime = 0;
		int maxWaitTime = 0;

		int leaveTime = 0;
		for( int i = 0; i < arrival.length; ++i )
		{
			if( i > 0 && leaveTime > arrival[ i ] )
			{
				waitTime = leaveTime - arrival[ i ];
			}
			else
			{
				waitTime = 0;
			}

			if( maxWaitTime < waitTime )
			{
				maxWaitTime = waitTime;
			}

			leaveTime = arrival[ i ] + service[ i ] + waitTime;
		}

		return maxWaitTime;
	}
}