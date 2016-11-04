package com.ar.puzzles.fb.userBinCrash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=2
 * <p/>
 * http://www.kelvinjiang.com/2010/10/facebook-puzzles-user-bin-crash.html#more
 * http://www.facebook.com/topic.php?uid=15325934266&topic=5131
 *
 * @author Alan Ross
 */
public class UsrBinCrash
{
	public static String INPUT_ERROR1
			= "Please provide an input file: UsrBinCrash <Inputfile>";
	public static String INPUT_ERROR2
			= "UsrBinCrash only accepts one argument, the name of the input file: UsrBinCrash <Inputfile>";
	public static String INPUT_ERROR3
			= "Please provide a valide input file: UsrBinCrash <Inputfile>";

	public UsrBinCrash()
	{
	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		String[] userInput = readInputFileToString( inputFileName ).split( "\\s+" );

		List<Unit> units = new ArrayList<Unit>();
		int n = userInput.length;

		int minEjected = new Integer( userInput[ 0 ] ).intValue();

		for( int i = 1; i < n; i += 3 )
		{
			units.add(
					new Unit(
							userInput[ i ],
							new Integer( userInput[ i + 1 ] ).intValue(),
							new Integer( userInput[ i + 2 ] ).intValue()
					) );
		}

		// Debug
		//System.out.println( minEjected );
		//Iterator<Unit> it = units.iterator();
		//while (it.hasNext())
		//{
		//	Unit unit = (Unit) it.next();
		//	System.out.println(unit.label + " - " + unit.weight + " - " + unit.cost);
		//}

		System.out.println( getMinimum( minEjected, units ) );
	}

	/**
	 * considering a weight n, the optimal cost of including
	 * one particular fruit in the solution is simply the optimal cost for
	 * "n minus the weight of the fruit" pounds plus the cost of the fruit itself.
	 * <p/>
	 * For example, suppose 10 lbs of fruit are needed, he can take one watermelon
	 * and subtract its weight to get 5 lbs. Assuming that he knows what the optimal
	 * cost for 5 lbs of fruit is, he simply adds the cost of a watermelon, $3.
	 * If he make this calculation for one of every fruit (since he has to add at
	 * least one fruit in order to arrive at weight n), and take the result with
	 * the minimum cost, then he will get an optimal cost!
	 */
	public int getMinimum( int weight, List<Unit> units )
	{
		//costs[k] = minimum cost for sacrificing k lbs of units
		int[] costs = new int[ weight + 1 ];

		costs[ 0 ] = 0;

		for( int i = 1; i <= weight; ++i )
		{
			//For each weight, pick a unit that yields us the lowest cost
			int min = 0;

			Iterator<Unit> it = units.iterator();

			while( it.hasNext() )
			{
				Unit unit = ( Unit ) it.next();

				int diff = i - unit.weight;
				int cost = ( diff <= 0 ? 0 : costs[ diff ] ) + unit.cost;

				if( min == 0 || cost < min )
				{
					min = cost;
				}
			}

			//System.out.println( i + " " + min );

			costs[ i ] = min;
		}

		return costs[ weight ];
	}

	private String readInputFileToString( String inputFileName )
	{
		byte[] buffer = new byte[ ( int ) new File( inputFileName ).length() ];

		BufferedInputStream input = null;
		try
		{
			input = new BufferedInputStream( new FileInputStream( inputFileName ) );
			input.read( buffer );
		}
		catch( IOException e )
		{
			System.err.println( "Error: " + e );
		}
		finally
		{
			if( input != null )
			{
				try
				{
					input.close();
				}
				catch( IOException e )
				{
					System.err.println( "Error: " + e );
				}
			}
		}

		return new String( buffer );
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

		UsrBinCrash puzzleSolver = new UsrBinCrash();
		puzzleSolver.run( args[ 0 ] );
	}
}