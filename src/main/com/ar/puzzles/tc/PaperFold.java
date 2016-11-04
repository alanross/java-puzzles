package com.ar.puzzles.tc;

/**
 * SRM 162 DIV2
 *
 * @author Alan Ross
 */
public class PaperFold
{

	public PaperFold()
	{
		int[] paper1 = { 8, 11 };
		int[] box1 = { 6, 10 };
		System.out.println( numFolds( paper1, box1 ) );//Returns: 1

		int[] paper2 = { 11, 17 };
		int[] box2 = { 6, 4 };
		System.out.println( numFolds( paper2, box2 ) );//Returns: 4

		int[] paper3 = { 11, 17 };
		int[] box3 = { 5, 4 };
		System.out.println( numFolds( paper3, box3 ) );//Returns: 4

		int[] paper4 = { 1000, 1000 };
		int[] box4 = { 62, 63 };
		System.out.println( numFolds( paper4, box4 ) );//Returns: -1

		int[] paper5 = { 100, 30 };
		int[] box5 = { 60, 110 };
		System.out.println( numFolds( paper5, box5 ) );//Returns: 0

		int[] paper6 = { 1895, 6416 };
		int[] box6 = { 401, 1000 };
		System.out.println( numFolds( paper6, box6 ) );//Returns: 5
	}

	public int numFolds( int[] paper, int[] box )
	{
		int result1 =
				maxFolds( paper[ 0 ], box[ 0 ] ) + maxFolds( paper[ 1 ], box[ 1 ] );
		int result2 =
				maxFolds( paper[ 1 ], box[ 0 ] ) + maxFolds( paper[ 0 ], box[ 1 ] );

		if( result1 <= result2 && result1 <= 8 )
		{
			return result1;
		}
		if( result1 > result2 && result1 <= 8 )
		{
			return result2;
		}

		return -1;
	}

	private int maxFolds( double paperSize, int boxSize )
	{
		int folds = 0;

		while( paperSize > boxSize )
		{
			paperSize /= 2.0;
			++folds;
		}

		return folds;
	}
}