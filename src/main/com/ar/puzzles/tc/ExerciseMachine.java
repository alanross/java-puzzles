package com.ar.puzzles.tc;

/**
 * SRM ? DIV ?
 *
 * @author Alan Ross
 */
public class ExerciseMachine
{
	public ExerciseMachine()
	{
		getPercentages( "00:30:00" ); // Returns: 99
		getPercentages( "00:28:00" ); // Returns: 19
		getPercentages( "23:59:59" ); // Returns: 0
		getPercentages( "00:14:10" ); // Returns: 49
		getPercentages( "00:19:16" ); // Returns: 3
	}

	/**
	 * Given a String time representing how long the workout lasts,
	 * in the format "hours:minutes:seconds", return the number of
	 * times a percentage will be displayed by the routine. The machine
	 * should never display 0% or 100%.
	 */
	public int getPercentages( String time )
	{
		String[] t = time.split( ":" );
		int hh = Integer.parseInt( t[ 0 ] );
		int mm = Integer.parseInt( t[ 1 ] );
		int ss = Integer.parseInt( t[ 2 ] );

		double totalSeconds = ( hh * 60 * 60 ) + ( mm * 60 ) + ss;

		int count = 0;

		for( int i = 1; i < totalSeconds; i++ )
		{
			double tmp = ( ( ( double ) i / totalSeconds ) * 1000000000.0 ) / 10000000.0;
			if( tmp % 1 == 0 )
			{
				count++;
			}
		}

		//System.out.println( hh +":" + mm + ":"+ ss +" >> "+ count );

		return count;
	}
}