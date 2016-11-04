package com.ar.puzzles.tc;
/**
 * SRM 159 DIV 2
 *
 * @author Alan Ross
 */

import java.util.ArrayList;
import java.util.Collections;

public class Sets
{
	public Sets()
	{
	}

	public int[] operate( int[] A, int[] B, String operation )
	{
		ArrayList<Integer> result = new ArrayList<Integer>();

		if( operation.compareToIgnoreCase( "UNION" ) == 0 )
		{
			result = union( A, B );
		}
		else if( operation.compareToIgnoreCase( "INTERSECTION" ) == 0 )
		{
			result = intersection( A, B );
		}
		else if( operation.compareToIgnoreCase( "SYMMETRIC DIFFERENCE" ) == 0 )
		{
			result = symetricDifference( A, B );
		}

		Collections.sort( result );

		int[] array = new int[ result.size() ];

		for( int i = 0; i < result.size(); ++i )
		{
			array[ i ] = result.get( i );
		}

		return array;
	}

	private ArrayList<Integer> union( int[] A, int[] B )
	{
		ArrayList<Integer> result = toArrayList( A );

		for( int i = 0; i < B.length; ++i )
		{
			int element = B[ i ];
			if( result.indexOf( element ) == -1 )
			{
				result.add( element );
			}
		}

		return result;
	}

	private ArrayList<Integer> intersection( int[] A, int[] B )
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> a = toArrayList( A );

		for( int i = 0; i < B.length; ++i )
		{
			int element = B[ i ];
			if( a.indexOf( element ) != -1 )
			{
				result.add( element );
			}
		}

		return result;
	}

	private ArrayList<Integer> symetricDifference( int[] A, int[] B )
	{
		ArrayList<Integer> result = union( A, B );
		ArrayList<Integer> in = intersection( A, B );

		int i = result.size();
		while( --i > -1 )
		{
			if( in.indexOf( result.get( i ) ) != -1 )
			{
				result.remove( result.indexOf( result.get( i ) ) );
			}
		}

		return result;
	}

	private ArrayList<Integer> toArrayList( int[] array )
	{
		ArrayList<Integer> result = new ArrayList<Integer>();

		for( int i = 0; i < array.length; ++i )
		{
			result.add( array[ i ] );
		}

		return result;
	}

	class Node
	{
		public int value;
		public Node left;
		public Node right;

		public Node( int value )
		{
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	class Tree
	{
		Node root;

		public Tree( int[] values )
		{
			for( int i = 0; i < values.length; ++i )
			{
				insert( values[ i ] );
			}
		}

		public void insert( int value )
		{
			internInsert( root, value );
		}

		public Boolean has( int value )
		{
			return internHas( root, value );
		}

		private void internInsert( Node node, int value )
		{
			if( root == null )
			{
				root = new Node( value );
				return;
			}

			if( value < node.value )
			{
				if( node.left != null )
				{
					internInsert( node.left, value );
				}
				else
				{
					node.left = new Node( value );
				}
			}
			else if( value > node.value )
			{
				if( node.right != null )
				{
					internInsert( node.right, value );
				}
				else
				{
					node.right = new Node( value );
				}
			}
		}

		private Boolean internHas( Node node, int value )
		{
			if( node == null )
			{
				return false;
			}

			if( node.value == value )
			{
				return true;
			}
			else if( value < node.value )
			{
				return internHas( node.left, value );
			}
			else
			{
				return internHas( node.right, value );
			}
		}
	}
}