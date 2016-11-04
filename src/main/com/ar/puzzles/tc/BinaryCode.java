package com.ar.puzzles.tc;

/**
 * SRM 144 DIV 2
 *
 * @author Alan Ross
 */
public class BinaryCode
{
	public BinaryCode()
	{
		//String[] result = decode("123210122");
		//String[] result = decode("11");
		String[] result = decode( "22111" );
		//String[] result = decode("123210120");
		//String[] result = decode("3");
		//String[] result = decode("12221112222221112221111111112221111");

		System.out.println( result[ 0 ] + " " + result[ 1 ] );
	}

	public String[] decode( String message )
	{
		int[] q = convertToIntArray( message );

		String[] result = { convert( q, 0 ), convert( q, 1 ) };

		return result;
	}

	private int[] convertToIntArray( String str )
	{
		int n = str.length();
		int[] result = new int[ n ];

		while( --n > -1 )
		{
			result[ n ] = Character.getNumericValue( str.charAt( n ) );
		}

		return result;
	}

	private String convert( int[] encrypted, int firstValue )
	{
		int d = 0;
		int n = encrypted.length; // input 123210122 must be 0-3
		int[] decoded = new int[ n ]; //output 011100011 must be 0 or 1
		StringBuffer decodedString = new StringBuffer();

		decoded[ 0 ] = firstValue;

		for( int i = 0; i < n; ++i )
		{
			decodedString.append( d = decoded[ i ] );

			if( d > 1 )
			{
				return "NONE";
			}
			if( i > 0 )
			{
				d += decoded[ i - 1 ];
			}
			if( i < n - 1 )
			{
				d += decoded[ i + 1 ] = encrypted[ i ] - d;
			}
			if( d != encrypted[ i ] )
			{
				return "NONE";
			}

			System.out.println( i + " " + d + " " + encrypted[ i ] );
		}

		return decodedString.toString();
	}
}
