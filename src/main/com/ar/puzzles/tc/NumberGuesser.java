package com.ar.puzzles.tc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * SRM 168 DIV2
 *
 * @author Alan Ross
 */
public class NumberGuesser
{

	public NumberGuesser()
	{
		System.out.println( guess( "087" ) ); //Returns: 3
		System.out.println( guess( "099" ) ); //Returns: 9
		System.out.println( guess( "191" ) ); //Returns: 7
		System.out.println( guess( "689" ) ); //Returns: 4
	}

	public int guess( String leftOver )
	{
		int number = Integer.parseInt( leftOver );
		int factor = 1;
		if( number < 999 )
		{
			while( number >= factor )
			{
				factor *= 10;
			}
		}

		//4 nested loops. can not be right. yet seems to work. fck.
		for( int i = 1; i < 10; ++i )
		{
			int a = number + ( i * factor );

			for( int p = 9998; p >= 1000; --p )
			{
				ArrayList<Integer> permutations = new ArrayList<Integer>();
				permuteNonDuplicate( new String( "" + p ).toCharArray(), 0, permutations );
				Collections.sort( permutations );

				int pLength = permutations.size();
				for( int j = pLength - 1; j > 0; --j )
				{
					for( int k = j - 1; k > 0; --k )
					{
						if( a == permutations.get( j ) - permutations.get( k ) )
						{
							return i;
						}
					}
				}
			}
		}

		return -1;
	}

	public void permuteNonDuplicate( char[] str, int d, ArrayList<Integer> subset )
	{
		if( d == str.length )
		{
			int number = Integer.parseInt( new String( str ) );
			subset.add( number );
		}
		else
		{
			char lastSwap = '\0';

			for( int i = d; i < str.length; ++i )
			{
				if( lastSwap == str[ i ] )
				{
					continue;
				}
				else
				{
					lastSwap = str[ i ];
				}

				// swap the characters for permutation
				swap( str, i, d );

				permuteNonDuplicate( str, d + 1, subset );

				// undo the swapping for parent call
				swap( str, i, d );
			}
		}
	}

	private void swap( char[] array, int i, int j )
	{
		char tmp = array[ i ];
		array[ i ] = array[ j ];
		array[ j ] = tmp;
	}
}
