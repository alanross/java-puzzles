package com.ar.puzzles.tc;

/**
 * SRM 165 DIV2
 *
 * @author Alan Ross
 */
public class ParallelSpeedup
{
	public ParallelSpeedup()
	{
		System.out.println( numProcessors( 12, 1 ) ); //Returns 2
		System.out.println( numProcessors( 50, 3 ) ); //Returns 3
		System.out.println( numProcessors( 9, 10 ) ); //Returns 1
		System.out.println( numProcessors( 3333, 2 ) ); //Returns 12
		System.out.println( numProcessors( 1000000, 4 ) ); //Returns 63
	}

	public int numProcessors( int k, int overhead )
	{
		int minTime = k;
		int processorConnections = 1;

		for( int processors = 2; processors <= k; ++processors )
		{
			int time = ( int ) Math.ceil( ( double ) k / ( double ) processors )
					+ processorConnections * overhead;

			if( time >= minTime )
			{
				return processors - 1;
			}

			minTime = time;

			processorConnections += processors;
		}

		return k;
	}
}