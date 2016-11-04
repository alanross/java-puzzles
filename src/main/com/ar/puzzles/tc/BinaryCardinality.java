package com.ar.puzzles.tc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * SRM 166 DIV 2
 *
 * @author Alan Ross
 */
public class BinaryCardinality
{
	public int[] arrange( int[] numbers )
	{
		Tree cardinality = new Tree();

		for( int i = 0; i < numbers.length; ++i )
		{
			int number = numbers[ i ];
			int count = binaryCardinality( number );
			cardinality.insert( number, count );
		}

		ArrayList<Node> array = cardinality.createArray();

		int[] result = new int[ numbers.length ];
		int v = -1;

		for( int i = 0; i < array.size(); ++i )
		{
			ArrayList<Integer> nodeNumbers = array.get( i ).numbers;

			for( int e = 0; e < nodeNumbers.size(); ++e )
			{
				result[ ++v ] = nodeNumbers.get( e );
			}
		}

		return result;
	}


	private int binaryCardinality( int number )
	{
		String binary = Integer.toBinaryString( number );
		int n = binary.length();
		int count = 0;

		while( --n > -1 )
		{
			if( binary.charAt( n ) == '1' )
			{
				count++;
			}
		}

		return count;
	}

	class Node
	{
		public Node left;
		public Node right;
		public int cardinality;
		public ArrayList<Integer> numbers;

		public Node( int number, int cardinality )
		{
			numbers = new ArrayList<Integer>();
			add( number );
			this.cardinality = cardinality;
		}

		public void add( int number )
		{
			numbers.add( number );
			Collections.sort( numbers );
		}
	}

	class Tree
	{
		public ArrayList<Node> array;
		private Node root;

		public Tree()
		{
			array = new ArrayList<Node>();
		}

		public void insert( int number, int cardinality )
		{
			if( root == null )
			{
				root = new Node( number, cardinality );
				return;
			}

			internInsert( root, number, cardinality );
		}

		private void internInsert( Node node, int number, int cardinality )
		{

			if( cardinality < node.cardinality )
			{
				if( node.left != null )
				{
					internInsert( node.left, number, cardinality );
				}
				else
				{
					node.left = new Node( number, cardinality );
				}

			}
			else if( cardinality > node.cardinality )
			{
				if( node.right != null )
				{
					internInsert( node.right, number, cardinality );
				}
				else
				{
					node.right = new Node( number, cardinality );
				}
			}
			else
			{
				node.add( number );
			}
		}

		public ArrayList<Node> createArray()
		{
			array.clear();
			internCreateArray( root );
			return array;
		}

		private void internCreateArray( Node node )
		{
			if( node != null )
			{
				internCreateArray( node.left );
				array.add( node );
				internCreateArray( node.right );
			}
		}
	}
}