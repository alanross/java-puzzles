package com.ar.puzzles.fb.simonSays;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;


/**
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=13
 *
 * @author Alan Ross
 */
public class PuzzleSimonSays
{
	public PuzzleSimonSays()
	{

	}

	public void run()
	{
		try
		{
			String host = "thriftpuzzle.facebook.com";
			int port = 9030;

			// Init thrift connection to server
			TTransport transport = new TSocket( host, port );
			TProtocol protocol = new TBinaryProtocol( transport );
			SimonSays.Client client = new SimonSays.Client( protocol );

			transport.open();

			client.registerClient( "hello@adjazent.com" );

			boolean win = false;

			while( !win )
			{
				List<Color> colors = client.startTurn();

				System.out.println( colors.size() );

				// this should be done interactively and handle wrong color input
				for( Color color : colors )
				{
					client.chooseColor( color );
				}

				win = client.endTurn();
			}

			System.out.println( client.winGame() );
		}
		catch( Exception e )
		{
			System.err.println( "Error: " + e );
		}
	}

	public static void main( String[] args )
	{
		PuzzleSimonSays puzzle = new PuzzleSimonSays();
		puzzle.run();
	}
}
