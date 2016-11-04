package com.ar.puzzles.fb.smallWorld;

public class MyKDNode
{
	public int id = 0;
	public double[] location;
	
	public MyKDNode left;
	public MyKDNode right;
	
	public MyKDNode( int id, double[] location, MyKDNode left, MyKDNode right )
	{
		this.id = id;
		this.location = location;
		this.left = left;
		this.right = right;
	}
}