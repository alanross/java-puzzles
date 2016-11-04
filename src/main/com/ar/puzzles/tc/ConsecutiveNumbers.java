package com.ar.puzzles.tc;

/**
 * SRM 402 DIV2
 *
 * @author Alan Ross
 */
public class ConsecutiveNumbers
{
	public int[] missingNumber( int[] numbers )
	{
		int n = numbers.length - 1;

		quicksort( numbers, 0, n );

		int num = -1;

		for( int i = 0; i < n; ++i )
		{
			int a = numbers[ i ];
			int b = numbers[ i + 1 ];

			if( a + 1 != b )
			{
				if( b - a > 2 || num != -1 )
				{
					return new int[ 0 ];
				}

				num = a + 1;
			}
		}

		if( num == -1 )
		{
			if( numbers[ 0 ] - 1 < 1 )
			{
				int[] result = { numbers[ n ] + 1 };
				return result;
			}

			int[] result = { numbers[ 0 ] - 1, numbers[ n ] + 1 };
			return result;
		}
		else
		{
			int[] result = { num };
			return result;
		}
	}

	private void quicksort( int[] array, int left, int right )
	{
		int i = left;
		int j = right;
		int pivot = array[ left + ( right - left ) / 2 ];

		while( i <= j )
		{
			while( array[ i ] < pivot )
			{
				i++;
			}
			while( array[ j ] > pivot )
			{
				j--;
			}

			if( i <= j )
			{
				int tmp = array[ i ];
				array[ i ] = array[ j ];
				array[ j ] = tmp;
				i++;
				j--;
			}
		}

		if( left < j )
		{
			quicksort( array, left, j );
		}
		if( i < right )
		{
			quicksort( array, i, right );
		}
	}
}