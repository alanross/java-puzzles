package com.ar.puzzles.tc;

import java.util.StringTokenizer;

/**
 * SRM 151 DIV 2
 *
 * @author Alan Ross
 */
public class Birthday
{
	public Birthday()
	{
		String date1 = "06/17";
		String[] birthdays1 = { "02/17 Wernie", "10/12 Stefan" };

		String date2 = "06/17";
		String[] birthdays2 = { "10/12 Stefan" };

		String date3 = "02/17";
		String[] birthdays3 = { "02/17 Wernie", "10/12 Stefan" };

		String date4 = "12/24";
		String[] birthdays4 = { "10/12 Stefan" };

		String date5 = "01/02";
		String[] birthdays5 = { "02/17 Wernie",
								"10/12 Stefan",
								"02/17 MichaelJordan",
								"10/12 LucianoPavarotti",
								"05/18 WilhelmSteinitz" };

		System.out.println( "10/12: " + getNext( date1, birthdays1 ) + "\n" );
		System.out.println( "10/12: " + getNext( date2, birthdays2 ) + "\n" );
		System.out.println( "02/17: " + getNext( date3, birthdays3 ) + "\n" );
		System.out.println( "10/12: " + getNext( date4, birthdays4 ) + "\n" );
		System.out.println( "02/17: " + getNext( date5, birthdays5 ) + "\n" );
	}

	public String getNext( String date, String[] birthdays )
	{
		//"MM/DD"
		StringTokenizer st = new StringTokenizer( date, "/" );
		int mmDate = Integer.parseInt( st.nextToken() );
		int ddDate = Integer.parseInt( st.nextToken() );

		int absDate = ( mmDate * 30 ) + ddDate;

		int ddMin = 0;
		int mmMin = 0;
		int diffMin = 1000;

		for( int i = 0; i < birthdays.length; ++i )
		{
			//"MM/DD <Name>"
			st = new StringTokenizer( birthdays[ i ], "/ " );
			int mmPerson = Integer.parseInt( st.nextToken() );
			int ddPerson = Integer.parseInt( st.nextToken() );
			String namePerson = st.nextToken();

			int absPerson = ( ( mmPerson ) * 30 ) + ddPerson;

			if( absPerson < absDate )
			{
				absPerson += 12 * 30;
			}

			int diff = absPerson - absDate;

			if( diff < diffMin )
			{
				mmMin = mmPerson;
				ddMin = ddPerson;
				diffMin = diff;
			}
		}

		return ( ( mmMin < 10 ) ? "0" + mmMin : mmMin ) + "/" + ( ( ddMin < 10 ) ? "0" + ddMin : ddMin );
	}
}