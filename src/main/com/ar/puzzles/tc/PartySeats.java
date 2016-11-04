package com.ar.puzzles.tc;

/**
 * SRM
 *
 * @author Alan Ross
 */
public class PartySeats
{
	public String[] seating( String[] attendees )
	{
		int half = attendees.length / 2;
		int bi = -1;
		int gi = -1;

		String[] boys = new String[ half ];
		String[] girls = new String[ half ];

		for( int i = 0; i < attendees.length; ++i )
		{
			String[] person = attendees[ i ].split( " " );
			String name = person[ 0 ];
			String gender = person[ 1 ];

			if( gender.compareToIgnoreCase( "girl" ) == 0 )
			{
				if( ++gi >= half )
				{
					System.out.println( "TOO MANY GIRLS " + half + " " + gi + " " + name );
					return new String[ 0 ];
				}
				girls[ gi ] = name;
			}
			else
			{
				if( ++bi >= half )
				{
					System.out.println( "TOO MANY BOYS " + half + " " + bi + " " + name );
					return new String[ 0 ];
				}

				boys[ bi ] = name;
			}
		}

		if( bi != gi || boys.length % 2 == 1 )
		{
			System.out.println( "NUM GIRLS != BOYS" + gi + " " + bi );
			return new String[ 0 ];
		}

		java.util.Arrays.sort( boys );
		java.util.Arrays.sort( girls );

		String[] result = new String[ boys.length + girls.length + 2 ];
		result[ 0 ] = "HOST";
		result[ result.length / 2 ] = "HOSTESS";

		int e = 0;
		for( int i = 1; i < result.length / 2; i += 2 )
		{
			result[ i ] = girls[ e ];
			result[ i + 1 ] = boys[ e ];
			++e;
		}

		for( int i = result.length / 2 + 1; i < result.length - 1; i += 2 )
		{
			result[ i ] = boys[ e ];
			result[ i + 1 ] = girls[ e ];
			++e;
		}

		return result;
	}
}