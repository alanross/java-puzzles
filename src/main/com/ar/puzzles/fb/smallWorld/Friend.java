package com.ar.puzzles.fb.smallWorld;

/**
 * @author Alan Ross
 */
public class Friend
{
	public int id = 0;
	public double[] location = new double[2];
	
	public Friend( int id, double x, double y )
	{
		this.id = id;
		this.location[0] = x;
		this.location[1] = y;
	}
	
	@Override
	public String toString()
	{
	    return "[Friend: " + id +" ]";
	}
}