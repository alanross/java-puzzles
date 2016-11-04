package com.ar.puzzles.fb.danceBattle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=12
 * <p/>
 * The Min-Max algorithm is applied in two player games, such as tic-tac-toe, checkers, chess, go, and so on.
 * <p/>
 * http://www.jgaunt.com/masters/minimax.html
 * http://en.wikipedia.org/wiki/Minimax
 * http://ai-depot.com/articles/minimax-explained/
 * http://www.jgaunt.com/masters/minimax.html
 * http://students.cs.byu.edu/~cs670ta/Lectures/Minimax.html
 * <p/>
 * http://en.wikipedia.org/wiki/Alpha-beta_pruning
 * http://www.emunix.emich.edu/~evett/AI/AlphaBeta_movie/index_movie.htm
 * <p/>
 * http://www.progtools.org/games/tutorials/ai_contest/minmax_contest.pdf
 * http://www.kelvinjiang.com/2010/10/facebook-puzzles-dance-battle.html
 * Undirected Edge Geography (UEG)
 * http://www.davideisenstat.com/fbpfaq/DanceBattle-reduction.pdf
 * http://www.koders.com/java/fidAD33710566F99548B3F548D9DC3F6340FA4B24EA.aspx?s=games#L5
 * http://www.koders.com/java/fid58B4E984994365DA3CF2264ADBA7DC8112A95C73.aspx?s=minimax#L6
 * <p/>
 * Permutation & combination
 * http://mathenexus.zum.de/html/stochastik/kombinatorik/Komb_4_kPerm.htm
 *
 * @author Alan Ross
 */
public class DanceBattle
{
	public static String INPUT_ERROR1 = "Please provide an input file: DanceBattle <Inputfile>";
	public static String INPUT_ERROR2 = "DanceBattle only accepts one argument, the name of the input file: DanceBattle <Inputfile>";
	public static String INPUT_ERROR3 = "Please provide a valide input file: DanceBattle <Inputfile>";

	public DanceBattle()
	{
	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		String[] userInput = readInputFileToString( inputFileName ).split( "\\s+" );

		int n = userInput.length;

		int numMovesTotal = new Integer( userInput[ 0 ] ).intValue();
		int numMovesPast = new Integer( userInput[ 1 ] ).intValue();
		System.out.println( numMovesTotal );
		System.out.println( numMovesPast );

		List<Turn> turns = new ArrayList<Turn>();
		Hashtable<Turn, Boolean> played = new Hashtable<Turn, Boolean>();

		for( int i = 2; i < n; i += 2 )
		{
			Turn t = new Turn( Integer.parseInt( userInput[ i ] ), Integer.parseInt( userInput[ i + 1 ] ) );
			turns.add( t );
			played.put( t, true );
			System.out.println( t.a + " " + t.b );
		}

		Turn last = turns.get( turns.size() - 1 );
		Boolean max = ( turns.size() % 2 == 0 );
		int result = minimaxAlphaBeta( numMovesTotal, max, played, last, -2, 2 );

		System.out.print( ( ( result == 1 ) ? "Win" : "Lose" ) );
	}

	private List<Turn> validTurns( int n, Hashtable<Turn, Boolean> played, Turn last )
	{
		List<Turn> valid = new ArrayList<Turn>();

		// If no tiles have been played, all is fair game
		if( last == null )
		{
			for( int a = 0; a < n; ++a )
			{
				for( int b = 0; b < n; ++b )
				{
					valid.add( new Turn( a, b ) );
				}
			}
		}
		// Valid tiles must have a value equal to the tail value of the last tile
		else
		{
			for( int i = 0; i < n; ++i )
			{
				Turn t = new Turn( last.b, i );

				if( exists( played, t ) == false )
				{
					valid.add( t );
				}
			}
		}

		return valid;
	}

	private Boolean exists( Hashtable<Turn, Boolean> played, Turn turn )
	{
		Enumeration<Turn> keys = played.keys();

		while( keys.hasMoreElements() )
		{
			Turn t = keys.nextElement();

			if( t.a == turn.a && t.b == turn.b )
			{
				return true;
			}
		}

		return false;
	}

	private int minimaxAlphaBeta( int n, Boolean max, Hashtable<Turn, Boolean> played, Turn last, int alpha, int beta )
	{
		List<Turn> turns = validTurns( n, played, last );

		if( turns.size() == 0 )
		{
			return ( max ? -1 : 1 );
		}

		int best = ( max ? alpha : beta );

		Iterator<Turn> it = turns.iterator();

		while( it.hasNext() )
		{
			//Mark tile (and reverse, equivalent tile) as played
			Turn t = ( Turn ) it.next();
			Turn rt = new Turn( t.b, t.a );

			played.put( t, true );
			played.put( rt, true );

			int reply = minimaxAlphaBeta( n, !max, played, t, alpha, beta );

			played.remove( t );
			played.remove( rt );

			//We can stop once we've found the best possible outcome
			if( max )
			{
				if( reply > best )
				{
					best = alpha = reply;
				}

				if( best >= 1 )
				{
					return best;
				}
			}
			else
			{
				if( reply < best )
				{
					best = beta = reply;
				}

				if( best <= -1 )
				{
					return best;
				}
			}

			//Can't do better than the best outcome so far
			if( alpha >= beta )
			{
				return best;
			}
		}

		return best;
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

		DanceBattle puzzleSolver = new DanceBattle();
		puzzleSolver.run( args[ 0 ] );
	}
}