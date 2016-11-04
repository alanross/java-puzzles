package com.ar.puzzles.tc;

import java.util.LinkedList;

/**
 * SRM 147 DIV 2
 *
 * @author Alan Ross
 */
public class PeopleCircle
{
	public PeopleCircle()
	{
		System.out.println( "MFMFMFMM\n" + order( 5, 3, 2 ) + "\n" );
		System.out.println( "FFFMMMMMMM\n" + order( 7, 3, 1 ) + "\n" );
		System.out.println( "MMMMMFFFFFFMFMFMMMFFMFFFFFFFFFMMMMMMMFFMFMMMFMFMMF\n" + order( 25, 25, 1000 ) + "\n" );
		System.out.println( "MFFMMFFMFM\n" + order( 5, 5, 3 ) + "\n" );
		System.out.println( "M\n" + order( 1, 0, 245 ) + "\n" );
	}

	//http://code.google.com/p/goalboytopcoder/source/browse/trunk/com.adjazent.puzzles.tc.PeopleCircle.java
	public String order( int numMales, int numFemales, int K )
	{
		StringBuffer people = new StringBuffer();
		LinkedList<Integer> circle = new LinkedList<Integer>();

		for( int i = 0; i < numMales + numFemales; i++ )
		{
			circle.add( i );
			people.append( 'M' );
		}

		int pos = 0;

		for( int i = 0; i < numFemales; i++ )
		{
			int nextPos = ( pos + K - 1 ) % circle.size();
			int index = circle.remove( nextPos );
			people.setCharAt( index, 'F' );
			if( !circle.isEmpty() )
			{
				pos = nextPos % circle.size();
			}
		}

		return people.toString();
	}


	public String orderOLD( int numMales, int numFemales, int K )
	{
		StringBuilder people = new StringBuilder();

		int i = 0;
		int pos = 0;
		int offset = numFemales * K;

		--K;
		for(; i < numMales; ++i )
		{
			people.append( "M" );
		}

		for( i = 0; i < numFemales; ++i )
		{
			pos = ( offset - ( K * i ) ) % ( people.length() - 1 );

			people.insert( pos, "F" );
////			people.insert( pos, ""+(i+1) );
////			System.out.println(i +" "+pos+" "+people);
		}

		return people.toString();
	}
}