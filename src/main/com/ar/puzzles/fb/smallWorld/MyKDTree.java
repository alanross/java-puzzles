package com.ar.puzzles.fb.smallWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * @author Alan Ross
 */
public class MyKDTree implements Comparator<MyKDNode>
{
	private int k = 2; // just using two dimensions
	private int axis;
	private MyKDNode root;

	private List<MyKDNode> nearest;

	/**
	 *
	 */
	public MyKDTree( List<MyKDNode> points )
	{
		nearest = new ArrayList<MyKDNode>();
		root = createTree( points, 0 );
	}

	/**
	 *
	 */
	private MyKDNode createTree( List<MyKDNode> points, int depth )
	{
		if( points.size() == 0 )
		{
			return new MyKDNode( -1, new double[ 2 ], null, null );
		}

		// Select axis based on depth so that axis cycles through all valid
		// values
		axis = depth % k;

		// Sort point list and choose median as pivot element
		Collections.sort( points, this );

		int median = points.size() / 2;

		// Create node and construct subtrees
		MyKDNode p = points.get( median );
		MyKDNode node = new MyKDNode( p.id, p.location, null, null );

		node.left = createTree( ( Vector<MyKDNode> ) points.subList( 0, median ), depth + 1 );
		node.right = createTree( ( Vector<MyKDNode> ) points.subList( median + 1, points.size() - 1 ), depth + 1 );

		return node;
	}

	/**
	 * Find k closest points to given coordinates
	 */
	public List<MyKDNode> nearestNeighbors( double[] target, int k )
	{
		nearest = new ArrayList<MyKDNode>();
		findNearest( root, target, k, 0 );
		return nearest;
	}

	/**
	 *
	 */
	@Override
	public int compare( MyKDNode n0, MyKDNode n1 )
	{
		double v0 = n0.location[ axis ];
		double v1 = n1.location[ axis ];

		if( v0 < v1 )
		{
			return -1;
		}
		if( v0 > v1 )
		{
			return 1;
		}

		return 0;
	}

	/**
	 *
	 */
	private void findNearest( MyKDNode node, double[] target, int k_nearest, int depth )
	{
		int axis = depth % 2;

		if( node.left == null && node.right == null ) // Leaf node
		{
			nearest = checkNearest( nearest, node, target, k_nearest );
			return;
		}

		MyKDNode nearer;
		MyKDNode further;

		// Go down the nearest split
		if( node.right == null || ( node.left != null && ( target[ axis ] <= node.location[ axis ] ) ) )
		{
			nearer = node.left;
			further = node.right;

		}
		else
		{
			nearer = node.right;
			further = node.left;
		}

		findNearest( nearer, target, k_nearest, depth + 1 );

		// See if we have to check other side
		if( further != null )
		{
			if( nearest.size() < k_nearest /*|| (target[axis] - node.location[axis])**2 < nearest.last[0]*/ )
			{
				findNearest( further, target, k_nearest, depth + 1 );
			}
		}

		nearest = checkNearest( nearest, node, target, k_nearest );
	}

	/**
	 * Update array of nearest elements if necessary
	 */
	private List<MyKDNode> checkNearest( List<MyKDNode> nearest, MyKDNode node, double[] target, int k_nearest )
	{
		double d = dist( node.location, target );

		if( nearest.size() < k_nearest || d < nearest.get( nearest.size() - 1 ).location[ 0 ] )
		{
			// nearest.pop if nearest.size >= k_nearest
			// nearest << [d, node.id]
			// nearest.sort! { |a, b| a[0] <=> b[0] }
		}
		return nearest;
	}

	/**
	 *
	 */
	private double dist( double[] p1, double[] p2 )
	{
		double d = 0;

		for( int i = 0; i < p1.length; i++ )
		{
			double diff = ( p1[ i ] - p2[ i ] );
			if( !Double.isNaN( diff ) )
			{
				d += diff * diff;
			}
		}

		return d;
	}

}