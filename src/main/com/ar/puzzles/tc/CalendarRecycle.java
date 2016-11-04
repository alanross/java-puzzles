package com.ar.puzzles.tc;

/**
 * SRM 163 DIV 2
 *
 * @author Alan Ross
 */
public class CalendarRecycle
{
	public int useAgain( int year )
	{
		boolean leap = isLeapYear( year );

		int days = ( leap ) ? 366 : 365;
		boolean l = isLeapYear( ++year );

		while( days % 7 != 0 || l != leap )
		{
			days += ( l ) ? 366 : 365;
			l = isLeapYear( ++year );
		}

		return year;
	}

	private boolean isLeapYear( int year )
	{
		return ( ( year % 4 == 0 ) && ( year % 100 != 0 ) ) || ( year % 400 == 0 );
	}
}