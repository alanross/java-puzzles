package com.ar.puzzles.tc;

/**
 * SRM 154 DIV 2
 * <p/>
 * http://www.asciitable.com/
 *
 * @author Alan Ross
 */
public class SuperRot
{
	public SuperRot()
	{
		System.out.println( "Hello 73: " + decoder( "Uryyb 28" ) + "\n" );
		System.out.println( "TopCoder: " + decoder( "GbcPbqre" ) + "\n" );
		System.out.println( ": " + decoder( "" ) + "\n" );
		System.out.println( "0123456789: " + decoder( "5678901234" ) + "\n" );
		System.out.println( "AaBbCcDdEe NnOoPpQqRr: " + decoder( "NnOoPpQqRr AaBbCcDdEe" ) + "\n" );
		System.out.println( "Time is 09 26 PM on July 9th of the year 2003 AD: " + decoder( "Gvzr vf 54 71 CZ ba Whyl 4gu bs gur lrne 7558 NQ" ) + "\n" );
	}

	public String decoder( String message )
	{
		StringBuilder result = new StringBuilder();

		for( int i = 0; i < message.length(); ++i )
		{
			char c = message.charAt( i );

			if( c >= 'n' )
			{
				c -= 13;
			}
			else if( c >= 'a' )
			{
				c += 13;
			}
			else if( c >= 'N' )
			{
				c -= 13;
			}
			else if( c >= 'A' )
			{
				c += 13;
			}
			else if( c >= '5' )
			{
				c -= 5;
			}
			else if( c >= '0' )
			{
				c += 5;
			}

			result.append( c );
		}

		return result.toString();
	}
}