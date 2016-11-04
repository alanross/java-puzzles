package com.ar.puzzles.tc;

/**
 * SRM 257 DIV 2
 *
 * @author Alan Ross
 */
public class Salary
{
	public Salary()
	{
		String[] arrival1 = { "08:00:00", "13:00:00", "19:27:32" };
		String[] departure1 = { "12:00:00", "17:00:00", "20:48:10" };

		howMuch( arrival1, departure1, 1000 );
	}

	public int howMuch( String[] arrival, String[] departure, int wage )
	{
		double result = 0.0;

		int normalTimeStart = 6 * 60 * 60;
		int normalTimeEnd = 18 * 60 * 60;

		for( int i = 0; i < arrival.length; ++i )
		{
			String[] arr = arrival[ i ].split( ":" );
			String[] dep = departure[ i ].split( ":" );

			int s = ( Integer.parseInt( arr[ 0 ] ) * 60 * 60 ) + ( Integer.parseInt( arr[ 1 ] ) * 60 ) + Integer.parseInt( arr[ 2 ] );
			int e = ( Integer.parseInt( dep[ 0 ] ) * 60 * 60 ) + ( Integer.parseInt( dep[ 1 ] ) * 60 ) + Integer.parseInt( dep[ 2 ] );

			int worktime = e - s;
			int overtime = 0;

			if( s < normalTimeStart )
			{
				overtime += ( e >= normalTimeStart ) ? normalTimeStart - s : e - s;
			}
			if( e >= normalTimeEnd )
			{
				overtime += ( s < normalTimeEnd ) ? e - normalTimeEnd : e - s;
			}

			worktime -= overtime;

			result += ( ( worktime ) / 3600.0 ) * wage * 1.0;
			result += ( ( overtime ) / 3600.0 ) * wage * 1.5;
		}

		return ( int ) result;
	}

}