package com.ar.puzzles.fb.breathalyzer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=17
 * http://www.merriampark.com/ldjava.htm
 * http://www.java2s.com/Code/Java/Data-Type/FindtheLevenshteindistancebetweentwoStrings.htm
 *
 * @author Alan Ross
 */
public class Breathalyzer
{
	public static String INPUT_ERROR1
			= "Please provide an input file: Breathalyzer <Inputfile>";
	public static String INPUT_ERROR2
			= "PuzzleSolver only accepts one argument, the name of the input file: Breathalyzer <Inputfile>";
	public static String INPUT_ERROR3
			= "Please provide a valide input file: Breathalyzer <Inputfile>";

	private String dictFile = "var/tmp/twl06.txt"; //"/var/tmp/twl06.txt";

	public Breathalyzer()
	{
	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		List<String> dict = createDictionary( dictFile );

		String[] userInput = readInputFileToString( inputFileName ).split( "\\s+" );

		int n = userInput.length;
		int result = 0;

		for( int i = 0; i < n; ++i )
		{
			Iterator<String> it = dict.iterator();

			int minLD = Integer.MAX_VALUE;

			while( it.hasNext() )
			{
				String value = ( String ) it.next();

				int d = getLevenshteinDistance( userInput[ i ], value.toLowerCase() );

				if( minLD > d )
				{
					minLD = d;
				}

				if( minLD == 0 )
				{
					break;
				}
			}

			result += minLD;
		}

		System.out.println( result );
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

	private List<String> createDictionary( String inputFileName )
	{
		List<String> result = new ArrayList<String>();

		String textLine;

		try
		{
			BufferedReader br = new BufferedReader( new FileReader( inputFileName ) );

			while( null != ( textLine = br.readLine() ) )
			{
				result.add( textLine.toLowerCase() );
			}
		}
		catch( IOException e )
		{
			System.err.println( "Error: " + e );
		}

		return result;
	}

	/*
		 *Function taken from http://www.merriampark.com/ldjava.htm
		 */
	private int getLevenshteinDistance( String s, String t )
	{
		if( s == null || t == null )
		{
			throw new IllegalArgumentException( "Strings must not be null" );
		}

		int n = s.length(); // length of s
		int m = t.length(); // length of t

		if( n == 0 )
		{
			return m;
		}
		else if( m == 0 )
		{
			return n;
		}

		int p[] = new int[ n + 1 ]; //'previous' cost array, horizontally
		int d[] = new int[ n + 1 ]; // cost array, horizontally
		int _d[]; //placeholder to assist in swapping p and d

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		int cost; // cost

		for( i = 0; i <= n; i++ )
		{
			p[ i ] = i;
		}

		for( j = 1; j <= m; j++ )
		{
			t_j = t.charAt( j - 1 );
			d[ 0 ] = j;

			for( i = 1; i <= n; i++ )
			{
				cost = s.charAt( i - 1 ) == t_j ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left and up +cost
				d[ i ] = Math.min( Math.min( d[ i - 1 ] + 1, p[ i ] + 1 ), p[ i - 1 ] + cost );
			}

			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return p[ n ];
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

		Breathalyzer puzzleSolver = new Breathalyzer();
		puzzleSolver.run( args[ 0 ] );
	}
}
