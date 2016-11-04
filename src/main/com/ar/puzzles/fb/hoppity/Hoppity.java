package com.ar.puzzles.fb.hoppity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to find out how to play and submit Facebook puzzles...
 * <p/>
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=7
 *
 * @author Alan Ross
 */
public class Hoppity
{
	public static String INPUT_ERROR1
			= "Please provide an input file: Hoppity <Inputfile>";
	public static String INPUT_ERROR2
			= "Hoppity only accepts one argument, the name of the input file: Hoppity <Inputfile>";
	public static String INPUT_ERROR3
			= "Please provide a valide input file: Hoppity <Inputfile>";

	public Hoppity()
	{

	}

	public void run( String inputFileName )
	{
		if( inputFileName.length() < 1 )
		{
			System.err.println( INPUT_ERROR3 );
		}

		try
		{
			Scanner scanner = new Scanner( new File( inputFileName ) );

			int n = scanner.nextInt();

			for( int i = 1; i <= n; ++i )
			{
				if( i % 3 == 0 && i % 5 == 0 )
				{
					System.out.print( "Hop\n" );
				}
				else if( i % 3 == 0 )
				{
					System.out.print( "Hoppity\n" );
				}
				else if( i % 5 == 0 )
				{
					System.out.print( "Hophop\n" );
				}
			}

			scanner.close();
		}
		catch( IOException e )
		{
			System.err.println( "Error: " + e );
		}
	}

	public static void main( String[] args )
	{
		if( args.length <= 0 )
		{
			System.err.println( INPUT_ERROR1 );
		}
		else if( args.length > 1 )
		{
			System.err.println( INPUT_ERROR2 );
		}

		Hoppity puzzle = new Hoppity();
		puzzle.run( args[ 0 ] );
	}
}
