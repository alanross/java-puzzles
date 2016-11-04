package com.ar.puzzles.tc;

/**
 * SRM 161 DIV 2
 *
 * @author Alan Ross
 */
public class StringTrain
{
	public StringTrain()
	{
		String[] cars0 = { "ABCDE", "CDFFF", "CDE", "CDEGG", "GABC" };
		System.out.println( buildTrain( cars0 ) ); //"10 DEGABC"

		String[] cars1 = { "AAAAA", "AAAAA", "AAAAA" };
		System.out.println( buildTrain( cars1 ) ); //"7 A"

		String[] cars2 = { "CABABDABAB", "CABAB", "ABABDABAB", "DABAB" };
		System.out.println( buildTrain( cars2 ) ); //"15 CDAB"

		String[] cars3 = { "ABABAB", "ABABAB", "ABABABAB", "BZZZZZ" };
		System.out.println( buildTrain( cars3 ) ); //"15 ABZ"

		String[] cars4 = { "A", "A", "A", "AA" };
		System.out.println( buildTrain( cars4 ) ); //"1 A"
	}

	public String buildTrain( String[] cars )
	{
		StringBuilder train = new StringBuilder( cars[ 0 ] );

		for( int i = 1; i < cars.length; ++i )
		{
			String tmp = new String( train );
			String car = cars[ i ];

			int n = car.length();

			while( --n > 0 )
			{
				String prefix = car.substring( 0, n );

				if( tmp.endsWith( prefix ) && tmp.compareTo( prefix ) != 0 )
				{
					train.append( car.substring( n, car.length() ) );
					break;
				}
			}
		}

		int n = train.length();

		for( int i = n - 1; i > -1; --i )
		{
			char c = train.charAt( i );

			int e = i;

			while( --e > -1 )
			{
				if( train.charAt( e ) == c )
				{
					train.deleteCharAt( e );
					--i;
				}
			}
		}

		return ( n + " " + new String( train ) );
	}
}