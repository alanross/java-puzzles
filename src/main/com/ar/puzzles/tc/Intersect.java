package com.ar.puzzles.tc;

/**
 * SRM 160 DIV 2
 *
 * @author Alan Ross
 */
public class Intersect
{

	public Intersect()
	{
		int[] x0 = { 0, 2, 3, -4 };
		int[] y0 = { 17, 1000, 18, 6 };
		System.out.println( area( x0, y0 ) ); // 2

		int[] x1 = { 10000, -10000 };
		int[] y1 = { -10000, 10000 };
		System.out.println( area( x1, y1 ) ); // 400000000

		int[] x2 = { 3, 8, 6, 12, 10, 15 };
		int[] y2 = { 7, 17, 7, 17, 7, 17 };
		System.out.println( area( x2, y2 ) ); // 0

		int[] x3 = { 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] y3 = { 5, 5, 5, 5, 5, 5, 5, 5 };
		System.out.println( area( x3, y3 ) ); // 0

		int[] x4 = { 2, 100, 5, 32, 1000, -20, 47, 12 };
		int[] y4 = { 29, -1000, -800, -200, -900, 300, -600, -650 };
		System.out.println( area( x4, y4 ) ); // 1000

		int[] x5 = { 1, 7, 12, 3, 16, 8, 3, 12 };
		int[] y5 = { -90, -60, -70, 3, -60, -90, -80, -100 };
		System.out.println( area( x5, y5 ) ); // 0
	}

	public int area( int[] x, int[] y )
	{
		int l = Integer.MIN_VALUE;
		int r = Integer.MAX_VALUE;
		int t = Integer.MIN_VALUE;
		int b = Integer.MAX_VALUE;

		for( int i = 0; i < x.length; i += 2 )
		{
			if( x[ i ] > x[ i + 1 ] )
			{
				swap( x, i, i + 1 );
			}
			if( y[ i ] > y[ i + 1 ] )
			{
				swap( y, i, i + 1 );
			}

			if( l < x[ i ] )
			{
				l = x[ i ];
			}
			if( r > x[ i + 1 ] )
			{
				r = x[ i + 1 ];
			}
			if( t < y[ i ] )
			{
				t = y[ i ];
			}
			if( b > y[ i + 1 ] )
			{
				b = y[ i + 1 ];
			}
		}

		if( ( r - l ) > 0 && ( b - t ) > 0 )
		{
			return ( r - l ) * ( b - t );
		}

		return 0;
	}

	private void swap( int[] array, int i, int e )
	{
		int tmp = array[ i ];
		array[ i ] = array[ e ];
		array[ e ] = tmp;
	}
}