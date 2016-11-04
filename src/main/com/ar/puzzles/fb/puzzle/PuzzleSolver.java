package com.ar.puzzles.fb.puzzle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * dynamic programming, graph algorithms, and complete search methods Facebook
 * Puzzles: http://www.facebook.com/careers/puzzles.php
 * http://www.kelvinjiang.com/ http://www.davideisenstat.com/fbpfaq/
 * http://luckytoilet.wordpress.com/tag/facebook-puzzle/
 * http://en.wikipedia.org/wiki/Dynamic_programming
 * http://www.polygenelubricants.com/2010/01/facebook-puzzles.html Submitting
 * Puzzles
 * http://thetwistedcables.com/maths-and-puzzles/facebook-puzzles/facebook
 * -puzzles-Hoppity/ Java File reading:
 * http://www.javapractices.com/topic/TopicAction.do?Id=42
 * http://snippets.dzone.com/posts/show/1335 Liar, Liar
 * http://www.dreamincode.net
 * /forums/blog/1478/entry-3088-facebook-engineering-puzzle-liarliar/
 * http://www.kelvinjiang.com/2010/09/facebook-puzzles-and-liar-liar.html
 * http://de.crypt.in/threads/101-Facebook-Puzzle-Liar-Liar
 * http://en.wikipedia.org/wiki/Bipartite_graph
 * http://en.wikipedia.org/wiki/Graph_coloring
 * http://en.wikipedia.org/wiki/Adjacency_matrix Dance Battle (Domino)
 * http://www.kelvinjiang.com/2010/10/facebook-puzzles-dance-battle.html#more
 * http://en.wikipedia.org/wiki/Minimax User Bin Crash (Knapsack problem )
 * http://en.wikipedia.org/wiki/Knapsack_problem
 * http://luckytoilet.wordpress.com/tag/facebook-puzzle/ Permutation &
 * Combination
 * http://www.mathsisfun.com/combinatorics/combinations-permutations.html
 * Graphs: http://20bits.com/articles/graph-theory-part-i-introduction/ Shortest
 * path problem http://en.wikipedia.org/wiki/Shortest_path_problem traveling
 * salesman problem http://de.wikipedia.org/wiki/Problem_des_Handlungsreisenden
 * http://www-i1.informatik.rwth-aachen.de/~algorithmus/algo40.php
 * http://en.wikipedia.org/wiki/Tree_traversal
 *
 * @author Alan Ross
 */
public class PuzzleSolver
{
	public static String INPUT_ERROR1 = "Please provide an input file: PuzzleSolver <Inputfile>";
	public static String INPUT_ERROR2 = "PuzzleSolver only accepts one argument, the name of the input file: PuzzleSover <Inputfile>";
	public static String INPUT_ERROR3 = "Please provide a valid input file: PuzzleSolver <Inputfile>";

	public PuzzleSolver()
	{
	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		readInputFile( inputFileName );
	}

	public String readFileAsString1( String filePath ) throws java.io.IOException
	{
		StringBuffer fileData = new StringBuffer( 1000 );
		BufferedReader reader = new BufferedReader( new FileReader( filePath ) );
		char[] buf = new char[ 1024 ];
		int numRead = 0;

		while( ( numRead = reader.read( buf ) ) != -1 )
		{
			fileData.append( buf, 0, numRead );
		}

		reader.close();

		return fileData.toString();
	}

	public String readFileAsString2( String filePath ) throws java.io.IOException
	{
		byte[] buffer = new byte[ ( int ) new File( filePath ).length() ];

		BufferedInputStream f = null;
		try
		{
			f = new BufferedInputStream( new FileInputStream( filePath ) );
			f.read( buffer );
		}
		finally
		{
			if( f != null )
			{
				try
				{
					f.close();
				}
				catch( IOException e )
				{
					System.err.println( "Error: " + e );
				}
			}
		}

		return new String( buffer );
	}

	private void readInputFile( String inputFileName )
	{
		String textLine;

		// Open file for reading
		try
		{
			BufferedReader br = new BufferedReader( new FileReader( inputFileName ) );

			while( null != ( textLine = br.readLine() ) )
			{
				processTextLine( textLine );
			}
		}
		catch( IOException e )
		{
			System.err.println( "Error: " + e );
		}
	}

	public void processTextLine( String line )
	{
		System.out.println( line );
	}


	public static void main( String args[] )
	{
		// // Note ">>>" instead of ">>" for unsigned shift
		// int intEmail = 0xFACEB00C >>> 2;
		// System.out.println("\nEmail Id: " + intEmail + "@fb.com");
		//
		// System.out.println(GetRomanValue("V"));

		// if (args.length <= 0)
		// System.err.println(INPUT_ERROR1);
		// else if (args.length > 1)
		// System.err.println(INPUT_ERROR2);
		//
		// PuzzleSolver puzzleSolver = new PuzzleSolver();
		// puzzleSolver.run(args[0]);
	}
}
