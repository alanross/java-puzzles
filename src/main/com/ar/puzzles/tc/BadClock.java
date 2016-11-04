package com.ar.puzzles.tc;

import java.util.StringTokenizer;

/**
 * SRM 172 DIV 2
 *
 * @author Alan Ross
 */
public class BadClock
{
	public double nextAgreement( String trueTime, String skewTime, int hourlyGain )
	{
		int t0 = parseTime( trueTime );
		int t1 = parseTime( skewTime );
		int h12 = 12 * 60 * 60;

		if( hourlyGain < 0 )
		{
			hourlyGain *= -1;

			int tmp = t0;
			t0 = t1;
			t1 = tmp;
		}

		while( t0 < t1 )
		{
			t0 += h12;
		}

		return ( double ) ( t0 - t1 ) / hourlyGain;
	}

	private int parseTime( String s )
	{
		StringTokenizer st = new StringTokenizer( s, ":" );

		return (
				Integer.parseInt( st.nextToken() ) * 60 * 60 +
						Integer.parseInt( st.nextToken() ) * 60 +
						Integer.parseInt( st.nextToken() )
		);
	}
}