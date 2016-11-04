package com.ar.puzzles.tc;

/**
 * SRM 146 DIV 2
 *
 * @author Alan Ross
 */
public class RectangularGrid
{
	public RectangularGrid()
	{
		System.out.println( " 3x3 >> " + countRectangles( 3, 3 ) + " expected: 22" );
		System.out.println( " 5x2 >> " + countRectangles( 5, 2 ) + " expected: 31" );
		System.out.println( " 10x10 >> " + countRectangles( 10, 10 ) + " expected: 2640" );
		System.out.println( " 1x1 >> " + countRectangles( 1, 1 ) + " expected: 0" );
		System.out.println( " 592x964 >> " + countRectangles( 592, 964 ) + " expected: 81508708664" );
	}

	/**
	 * Given the width and height of a rectangular grid, return the
	 * total number of rectangles (NOT counting squares) that can be found on this grid.
	 */
	public long countRectangles( int width, int height )
	{
		long result = 0;

		for( int i = 1; i <= width; ++i )
		{
			int tw = ( width - i ) + 1;

			for( int e = 1; e <= height; ++e )
			{
				if( e != i )
				{
					int th = ( height - e ) + 1;
					result += ( tw * th );
				}
			}
		}

		return result;
	}
}