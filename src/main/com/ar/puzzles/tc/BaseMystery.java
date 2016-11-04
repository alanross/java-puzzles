package com.ar.puzzles.tc;

import java.util.ArrayList;

/**
 * SRM 158 DIV 2
 *
 * @author Alan Ross
 */
public class BaseMystery
{
	public BaseMystery()
	{
		getBase( "1+1=2" );            //{ 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 }
		getBase( "1+1=10" );            //{ 2 }
		getBase( "1+1=3" );            //none
		getBase( "ABCD+211=B000" );     //{ 14 }
		getBase( "ABCD+322=B000" );     //{ 15 }
		getBase( "1+0=1" );            //{ 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 }
		getBase( "GHIJ+1111=HJ00" );    //{ 20 }
		getBase( "1234+8765=9999" );    //{ 10,11,12,13,14,15,16,17,18,19,20 }
	}

	public int[] getBase( String equation )
	{
		equation = equation.toLowerCase();
		System.out.print( equation + " : " );

		String[] e = equation.split( "[//+=]" ); //<num>+<num>=<num>

		ArrayList<Integer> valid = new ArrayList<Integer>();

		for( int base = 2; base <= 20; ++base )
		{
			if( add( e[ 0 ], e[ 1 ], base ).compareToIgnoreCase( e[ 2 ] ) == 0 )
			{
				valid.add( base );
			}
		}

		int[] result = new int[ valid.size() ];

		for( int i = 0; i < valid.size(); ++i )
		{
			System.out.print( valid.get( i ) + "," );
			result[ i ] = valid.get( i );
		}

		System.out.println( "" );

		return result;
	}

	private String add( String num1, String num2, int base )
	{
		StringBuilder result = new StringBuilder();

		int carry = 0;

		int n = num1.length();
		int m = num2.length();

		while( n > 0 || m > 0 )
		{
			int digit = carry;

			if( n > 0 )
			{
				digit += charToInt( num1.charAt( --n ) );
			}
			if( m > 0 )
			{
				digit += charToInt( num2.charAt( --m ) );
			}

			result.insert( 0, intToChar( digit % base ) );

			carry = digit / base;
		}

		if( carry > 0 )
		{
			result.insert( 0, intToChar( carry % base ) );
		}

		return new String( result );
	}

	private int charToInt( char digit )
	{
		if( digit >= 97 && digit <= 106 )
		{
			return digit - 87;    //a-j
		}
		if( digit >= 48 && digit <= 57 )
		{
			return digit - 48;     //0-9
		}

		return 0;
	}

	private char intToChar( int digit )
	{
		if( digit <= 9 )
		{
			return ( char ) ( digit + 48 );    //0-9
		}
		return ( char ) ( digit + 87 );                    //a-j
	}

	private int convertToBase10( String value, int base )
	{
		int n = value.length();
		int pos = -1;
		int result = 0;

		while( --n > -1 )
		{
			result += charToInt( value.charAt( n ) ) * Math.pow( base, ++pos );
		}

		return result;
	}
}