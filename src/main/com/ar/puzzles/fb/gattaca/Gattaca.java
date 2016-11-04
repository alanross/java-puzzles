package com.ar.puzzles.fb.gattaca;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gattaca (interval scheduling problem)
 * http://www.simmoril.com/blog/?p=293
 * http://www.kelvinjiang.com/2010/10/facebook-puzzles-Gattaca.html
 * http://en.wikipedia.org/wiki/Interval_scheduling
 * <p/>
 * http://pages.cs.wisc.edu/~shuchi/courses/787-F09/scribe-notes/lec3.pdf
 * <p/>
 * Scheduling algorithm:
 * http://sist.sysu.edu.cn/~isslxm/DSA/textbook/Skiena.-.TheAlgorithmDesignManual.pdf
 * http://en.wikipedia.org/wiki/Interval_scheduling
 * <p/>
 * Weighted interval scheduling
 * http://www.cs.uiuc.edu/class/fa08/cs473/Lectures/lecture12.pdf (!)
 * http://pages.cs.wisc.edu/~shuchi/courses/787-F09/scribe-notes/lec3.pdf
 * http://www.cs.cornell.edu/courses/cs482/2007sp/dynamic.pdf
 * <p/>
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 *
 * @author Alan Ross
 */
public class Gattaca
{
	public static String INPUT_ERROR1
			= "Please provide an input file: Gattaca <Inputfile>";
	public static String INPUT_ERROR2
			= "Gattaca only accepts one argument, the name of the input file: Gattaca <Inputfile>";
	public static String INPUT_ERROR3
			= "Please provide a valide input file: Gattaca <Inputfile>";

	private int _optimalScore = 0;

	public Gattaca()
	{
	}

	public void run( String inputFileName )
	{
		try
		{
			if( inputFileName.length() < 1 )
			{
				System.err.println( INPUT_ERROR3 );
			}

			Scanner input = new Scanner( new File( inputFileName ) );

			StringBuilder s = new StringBuilder();

			int sequencLength = input.nextInt();

			while( s.length() < sequencLength )
			{
				s.append( input.next() );
			}

			String sequence = s.toString();

			int numPredictions = input.nextInt();

			List<Interval> intervals = new ArrayList<Interval>( numPredictions );

			for( int i = 0; i < numPredictions; ++i )
			{
				intervals.add( new Interval( input.nextInt(), input.nextInt(), input.nextInt() ) );
			}

			/*
			System.out.println(sequencLength);
			System.out.println( sequence );
			System.out.println( numPredictions );
			Iterator<Prediction> it = predictions.iterator();
			
			while( it.hasNext() )
			{
				Prediction p = (Prediction) it.next();
				System.out.println( p.start + " - "+ p.end + " - "+ p.score );
			}
			*/

			findOptimalScore( intervals );

			System.out.println( _optimalScore );
		}
		catch( IOException e )
		{
		}
	}

	private void findOptimalScore( List<Interval> intervals )
	{
		int n = intervals.size();

		if( n == 0 )
		{
			return;
		}

		int minEnd = Integer.MAX_VALUE;
		int index = 0;
		Interval p;

		while( --n > -1 )
		{
			p = intervals.get( n );

			if( minEnd > p.end )
			{
				minEnd = p.end;
				index = intervals.indexOf( p );
			}
		}

		p = intervals.get( index );

		System.out.println( "findOptimalScore: " + p.start + " - " + p.end );

		_optimalScore += p.score;

		n = intervals.size();

		while( --n > -1 )
		{
			p = intervals.get( n );

			if( minEnd >= p.start || minEnd >= p.end )
			{
				System.out.println( "\t\t" + p.start + " - " + p.end );
				intervals.remove( p );
			}
		}

		if( intervals.size() > 0 )
		{
			findOptimalScore( intervals );
		}
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

		Gattaca puzzleSolver = new Gattaca();
		puzzleSolver.run( args[ 0 ] );
	}
}