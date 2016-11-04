package com.ar.puzzles.tc;

/**
 * SRM 156 DIV 2
 *
 * @author Alan Ross
 */
public class BombSweeper
{

	public BombSweeper()
	{
	}

	public double winPercentage( String[] board )
	{
		int losses = 0;
		int wins = 0;

		for( int i = 0; i < board.length; ++i )
		{
			String row = board[ i ];

			for( int e = 0; e < row.length(); ++e )
			{
				if( isBomb( board, i, e ) )
				{
					losses++;
					continue;
				}


				//check all neighbours
				if( isBomb( board, i - 1, e - 1 ) )
				{
					continue;
				}
				if( isBomb( board, i - 1, e ) )
				{
					continue;
				}
				if( isBomb( board, i - 1, e + 1 ) )
				{
					continue;
				}

				if( isBomb( board, i, e - 1 ) )
				{
					continue;
				}
				if( isBomb( board, i, e + 1 ) )
				{
					continue;
				}

				if( isBomb( board, i + 1, e - 1 ) )
				{
					continue;
				}
				if( isBomb( board, i + 1, e ) )
				{
					continue;
				}
				if( isBomb( board, i + 1, e + 1 ) )
				{
					continue;
				}

				wins++;
			}
		}

		return ( ( double ) wins / ( ( double ) wins + ( double ) losses ) ) * 100.0;
	}

	private Boolean isBomb( String[] board, int row, int col )
	{
		if( row < 0 )
		{
			return false;
		}

		if( col < 0 )
		{
			return false;
		}

		if( row >= board.length )
		{
			return false;
		}

		if( col >= board[ row ].length() )
		{
			return false;
		}

		return ( board[ row ].charAt( col ) == 'B' );


	}
}