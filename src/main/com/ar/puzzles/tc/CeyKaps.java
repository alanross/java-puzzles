package com.ar.puzzles.tc;

/**
 * SRM ? DIV 2
 *
 * @author Alan Ross
 */
public class CeyKaps
{
	public CeyKaps()
	{
		String[] test1 = { "A:B", "B:C", "A:D" };
		String[] test2 = { "A:B", "B:C", "C:D", "D:E", "E:A" };
		String[] test3 = { "W:O", "W:I" };
		System.out.println( "CCCCC " + decipher( "AAAAA", test1 ) + "\n" );
		System.out.println( "AEBCD " + decipher( "ABCDE", test2 ) + "\n" );
		System.out.println( "WHOSWITCHEDMYKEYCAPSAROUND " + decipher( "IHWSIOTCHEDMYKEYCAPSARWUND", test3 ) + "\n" );
	}

	public String decipher( String typed, String[] switches )
	{
		StringBuilder result = new StringBuilder();

		int i;

		// create alphabet. Note 65 is A in ASCII
		char[] keys = new char[ 26 ];
		for( i = 0; i < keys.length; ++i )
		{
			keys[ i ] = ( char ) ( 65 + i );
		}

		//do the caps swapping 
		for( i = 0; i < switches.length; ++i )
		{
			String[] swapped = switches[ i ].split( ":" );

			int c0 = swapped[ 0 ].charAt( 0 );
			int c1 = swapped[ 1 ].charAt( 0 );
			int i0 = -1;
			int i1 = -1;

			for( int e = 0; e < keys.length; ++e )
			{
				if( keys[ e ] == c0 )
				{
					i0 = e;
				}
				if( keys[ e ] == c1 )
				{
					i1 = e;
				}
				if( i0 != -1 && i1 != -1 )
				{
					break;
				}
			}

			char tmp = keys[ i0 ];
			keys[ i0 ] = keys[ i1 ];
			keys[ i1 ] = tmp;
		}

		//replace message content with the swapped keys
		for( i = 0; i < typed.length(); ++i )
		{
			result.append( keys[ ( int ) typed.charAt( i ) - 65 ] );
		}

		return result.toString();
	}
}