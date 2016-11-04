package com.ar.puzzles.tc;

import java.util.ArrayList;

/**
 * SRM 171 DIV 2
 *
 * @author Alan Ross
 */
public class CrossCountry
{
	public String scoreMeet( int numTeams, String finishOrder )
	{
		Team[] teams = new Team[ numTeams ];

		for( int i = 0; i < numTeams; ++i )
		{
			teams[ i ] = new Team();
		}

		for( int i = 0; i < finishOrder.length(); ++i )
		{
			char id = finishOrder.charAt( i );
			int index = id - 65; //A = asciicode 65

			teams[ index ].id = id;
			teams[ index ].addRun( i + 1 );

			if( teams[ index ].numRuns() < 6 )
			{
				teams[ index ].score += i + 1;
			}
		}

		sortByScore( teams, 0, numTeams - 1 );

		StringBuilder result = new StringBuilder();

		for( int i = 0; i < numTeams; ++i )
		{
			System.out.println( teams[ i ].id + " " + teams[ i ].numRuns() + " " + teams[ i ].score );
		}

		for( int i = 0; i < numTeams - 1; ++i )
		{
			if( teams[ i ].numRuns() >= 5 )
			{
				if( teams[ i ].score == teams[ i + 1 ].score )
				{
					if( teams[ i ].numRuns() >= 6 && teams[ i + 1 ].numRuns() >= 6 )
					{
						if( teams[ i ].runs.get( 5 ) > teams[ i + 1 ].runs.get( 5 ) )
						{
							swap( teams, i, i + 1 );
						}
					}
					if( teams[ i ].numRuns() < 6 && teams[ i + 1 ].numRuns() >= 6 )
					{
						swap( teams, i, i + 1 );
					}
					if( teams[ i ].numRuns() < 6 && teams[ i + 1 ].numRuns() < 6 )
					{
						if( teams[ i ].id > teams[ i + 1 ].id )
						{
							swap( teams, i, i + 1 );
						}
					}
				}

				if( teams[ i ].numRuns() >= 5 )
				{
					result.append( teams[ i ].id );
				}
			}
		}

		if( teams[ numTeams - 1 ].numRuns() >= 5 )
		{
			result.append( teams[ numTeams - 1 ].id );
		}

		return new String( result );
	}

	private void sortByScore( Team[] array, int left, int right )
	{
		int i = left;
		int j = right;
		int pivot = array[ left + ( right - left ) / 2 ].score;

		while( i <= j )
		{
			while( array[ i ].score < pivot )
			{
				i++;
			}
			while( array[ j ].score > pivot )
			{
				j--;
			}

			if( i <= j )
			{
				swap( array, i, j );
				i++;
				j--;
			}
		}

		if( left < j )
		{
			sortByScore( array, left, j );
		}
		if( i < right )
		{
			sortByScore( array, i, right );
		}
	}

	private void swap( Team[] array, int i, int j )
	{
		Team tmp = array[ i ];
		array[ i ] = array[ j ];
		array[ j ] = tmp;
	}

	class Team
	{
		public char id = ' ';
		public int score = 0;
		private ArrayList<Integer> runs = new ArrayList<Integer>();

		public void addRun( int place )
		{
			runs.add( place );
		}

		public int numRuns()
		{
			return runs.size();
		}
	}
}