package com.ar.puzzles.tc;

import java.util.ArrayList;

/**
 * SRM 170 DIV2
 *
 * @author Alan Ross
 */
public class RecurrenceRelation
{
	public RecurrenceRelation()
	{
		int[] c0 = { 2, 1 };    //0
		int[] i0 = { 9, 7 };
		int n0 = 6;

		int[] c1 = { 1, 1 };    //1
		int[] i1 = { 0, 1 };
		int n1 = 9;

		int[] c2 = { 2 };        //2
		int[] i2 = { 1 };
		int n2 = 20;

		int[] c3 = { 2 };        //3
		int[] i3 = { 1 };
		int n3 = 64;

		int[] c4 = { 25, 143 };//4
		int[] i4 = { 0, 0 };
		int n4 = 100000;

		int[] c5 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };        //5
		int[] i5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int n5 = 654;

		int[] c6 = { 901, 492, 100 };    //6
		int[] i6 = { -6, -15, -39 };
		int n6 = 0;

		System.out.println( moduloTen( c0, i0, n0 ) + " -> 5" ); // Returns: 5
		System.out.println( moduloTen( c1, i1, n1 ) + " -> 4" ); // Returns: 4
		System.out.println( moduloTen( c2, i2, n2 ) + " -> 6" ); // Returns: 6
		System.out.println( moduloTen( c3, i3, n3 ) + " -> 6" ); // Returns: 6
		System.out.println( moduloTen( c4, i4, n4 ) + " -> 0" ); // Returns: 0
		System.out.println( moduloTen( c5, i5, n5 ) + " -> 5" ); // Returns: 5
		System.out.println( moduloTen( c6, i6, n6 ) + " -> 4" ); // Returns: 4
	}


	public int moduloTen( int[] coefficients, int[] initial, int N )
	{
		int k = initial.length;

		ArrayList<Integer> in = new ArrayList<Integer>();

		for( int i = 0; i < k; ++i )
		{
			in.add( modulo( initial[ i ] ) );
		}

		for( int i = k; i <= N; ++i )
		{
			int x = 0;

			for( int e = 0; e < k; ++e )
			{
				x += coefficients[ e ] * in.get( i - k + e );
			}

			in.add( modulo( x ) );
		}

		return in.get( N );
	}

	private int modulo( int value )
	{
		if( value >= 0 )
		{
			return value % 10;
		}

		return ( ( 10 - ( ( -value ) % 10 ) ) % 10 );
	}
}