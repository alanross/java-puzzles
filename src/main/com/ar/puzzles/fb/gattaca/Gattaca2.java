package com.ar.puzzles.fb.gattaca;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Gattaca (interval scheduling problem)
 * http://www.simmoril.com/blog/?p=293
 * http://www.kelvinjiang.com/2010/10/facebook-puzzles-Gattaca.html
 * http://en.wikipedia.org/wiki/Interval_scheduling
 * http://pages.cs.wisc.edu/~shuchi/courses/787-F09/scribe-notes/lec3.pdf
 * Scheduling algorithm:
 * http://sist.sysu.edu.cn/~isslxm/DSA/textbook/Skiena.-.TheAlgorithmDesignManual.pdf
 * http://en.wikipedia.org/wiki/Interval_scheduling
 * Weighted interval scheduling
 * http://www.cs.uiuc.edu/class/fa08/cs473/Lectures/lecture12.pdf (!)
 * http://pages.cs.wisc.edu/~shuchi/courses/787-F09/scribe-notes/lec3.pdf
 * http://www.cs.cornell.edu/courses/cs482/2007sp/dynamic.pdf
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 *
 * @author Alan Ross
 */
public class Gattaca2 implements Comparator<Interval>
{
	public static String INPUT_ERROR1
			= "Please provide an input file: Gattaca <Inputfile>";
	public static String INPUT_ERROR2
			= "Gattaca only accepts one argument, the name of the input file: Gattaca <Inputfile>";
	public static String INPUT_ERROR3
			= "Please provide a valide input file: Gattaca <Inputfile>";

	private List<Interval> _intervals;

	public Gattaca2()
	{
	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		try
		{
			Scanner input = new Scanner( new File( inputFileName ) );
			StringBuilder sequence = new StringBuilder();

			int sequencLength = input.nextInt();

			while( sequence.length() < sequencLength )
			{
				sequence.append( input.next() );
			}

			int numIntervals = input.nextInt();

			_intervals = new ArrayList<Interval>( numIntervals );

			for( int i = 0; i < numIntervals; ++i )
			{
				_intervals.add( new Interval(
						input.nextInt(),
						input.nextInt(),
						input.nextInt()
				) );
			}


			// Debug
			/*
			Iterator<Interval> it = _intervals.iterator();
			while( it.hasNext() )
			{
				Interval p = (Interval) it.next();
				System.out.println( p.start + " - "+ p.end + " - "+ p.score );
			}
			*/

			findOptimalScore();
		}
		catch( IOException e )
		{
		}
	}

	private void findOptimalScore()
	{
		//Sort intervals so 'end' value is ascending 
		Collections.sort( _intervals, this );

		List<Interval> optimalIntervals = new ArrayList<Interval>();

		Interval interval;
		int n = _intervals.size();

		//remember results
		boolean[] memoizeIntervals = new boolean[ n ];
		int[] memoizeScore = new int[ n ];
		memoizeScore[ 0 ] = 0;

		// find intervals that have highest score and fit 
		for( int i = 1; i < n; ++i )
		{
			interval = _intervals.get( i );
			int score = interval.score;
			int npi = getIndexOfNextPossibleInterval( i );

			memoizeScore[ i ] = getMax( score + memoizeScore[ npi ], memoizeScore[ i - 1 ] );
			//true if interval is to be kept for optimal score
			memoizeIntervals[ i ] = ( score + memoizeScore[ npi ] > memoizeScore[ i - 1 ] );
		}

		//Add scores to accumulated score
		int optimalScore = 0;

		while( --n > -1 )
		{
			if( memoizeIntervals[ n ] )
			{
				interval = _intervals.get( n );
				optimalScore += interval.score;
				optimalIntervals.add( interval );
			}
		}

		System.out.println( optimalScore );
	}

	/**
	 * Return the larger of both values.
	 */
	private int getMax( int val1, int val2 )
	{
		if( val1 > val2 )
		{
			return val1;
		}

		return val2;
	}

	/**
	 * Returns largest possible index of interval that would
	 * fit in front of given interval index without conflict.
	 */
	private int getIndexOfNextPossibleInterval( int intervalIndex )
	{
		int diff = Integer.MAX_VALUE;
		int result = 0;
		int n = _intervals.size();

		Interval i = _intervals.get( intervalIndex );
		Interval j = null;

		for( int index = 0; index < n; ++index )
		{
			j = _intervals.get( index );

			if( j.end < i.start )
			{
				if( diff > ( i.start - j.end ) )
				{
					diff = i.start - j.end;
					result = index;
				}
			}
		}

		return result;
	}

	@Override
	public int compare( Interval o1, Interval o2 )
	{
		if( o1.end < o2.end )
		{
			return -1;
		}
		if( o1.end > o2.end )
		{
			return 1;
		}
		return 0;
	}

	public static void main( String args[] )
	{
		if( args.length <= 0 )
		{
			System.err.println( INPUT_ERROR1 );
		}
		else if( args.length > 1 )
		{
			System.err.println( INPUT_ERROR2 );
		}

		Gattaca2 puzzleSolver = new Gattaca2();
		puzzleSolver.run( args[ 0 ] );
	}
}