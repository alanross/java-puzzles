package com.ar.puzzles.tc;

import java.util.ArrayList;

/**
 * SRM 167 DIV 2
 *
 * @author Alan Ross
 */
public class Animation
{
	public String[] animate( int speed, String init )
	{
		ArrayList<Particle> particles = new ArrayList<Particle>();

		int n = init.length();

		for( int i = 0; i < n; ++i )
		{
			if( init.charAt( i ) == 'L' )
			{
				particles.add( new Particle( i, -speed ) );
			}
			if( init.charAt( i ) == 'R' )
			{
				particles.add( new Particle( i, speed ) );
			}
		}

		ArrayList<String> sequence = new ArrayList<String>();

		if( particles.size() == 0 )
		{
			sequence.add( new String( createCanvas( n ) ) );
		}

		while( particles.size() > 0 )
		{
			char[] c = createCanvas( n );
			int m = particles.size();

			while( --m > -1 )
			{
				Particle p = particles.get( m );

				if( p.pos < 0 || p.pos >= n )
				{
					particles.remove( m );
				}
				else
				{
					c[ p.pos ] = 'X';
				}

				p.pos += p.vel;
			}

			sequence.add( new String( c ) );
		}

		String[] result = new String[ sequence.size() ];

		for( int i = 0; i < sequence.size(); ++i )
		{
			result[ i ] = sequence.get( i );
		}

		return result;
	}

	private char[] createCanvas( int size )
	{
		char[] c = new char[ size ];

		while( --size > -1 )
		{
			c[ size ] = '.';
		}

		return c;
	}

	class Particle
	{
		public int pos;
		public int vel;

		public Particle( int p, int v )
		{
			this.pos = p;
			this.vel = v;
		}
	}
}