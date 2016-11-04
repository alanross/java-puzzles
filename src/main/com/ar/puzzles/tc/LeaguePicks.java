package com.ar.puzzles.tc;
/**
 * SRM 152 DIV 2
 *
 * @author Alan Ross
 */

import java.util.Stack;

public class LeaguePicks
{
	public LeaguePicks()
	{
		returnPicks( 3, 6, 15 );
		System.out.println( "{ 3,  10,  15 }\n" );

		returnPicks( 1, 1, 10 );
		System.out.println( "{ 1,  2,  3,  4,  5,  6,  7,  8,  9,  10 }\n" );

		returnPicks( 1, 2, 39 );
		System.out.println( "{ 1,  4,  5,  8,  9,  12,  13,  16,  17,  20,  21,  24,  25,  28,  29,  32,  33,  36,  37 }\n" );

		returnPicks( 5, 11, 100 );
		System.out.println( "{ 5,  18,  27,  40,  49,  62,  71,  84,  93 }\n" );
	}

	public int[] returnPicks( int position, int friends, int picks )
	{
		Stack<Integer> stack = new Stack<Integer>();

		int i = 1;
		int friendIndex = 1;
		int dir = 1;

		for(; i <= picks; ++i )
		{
			if( position == friendIndex )
			{
				stack.push( i );
			}

			int tmp = friendIndex + dir;

			if( tmp > 0 && tmp <= friends )
			{
				friendIndex = tmp;
			}
			else
			{
				dir *= -1;
			}
		}

		int[] turns = new int[ stack.size() ];

		i = stack.size();

		while( stack.size() > 0 )
		{
			turns[ --i ] = ( int ) stack.pop();
		}

		System.out.print( "\n{ " );
		for( i = 0; i < turns.length; ++i )
		{
			System.out.print( turns[ i ] + ",  " );
		}
		System.out.print( "}\n" );

		return turns;
	}
}