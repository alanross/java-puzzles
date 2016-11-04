package com.ar.puzzles.fb.smallWorld;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=6
 * 
 * http://en.wikipedia.org/wiki/Nearest_neighbor_search
 * http://en.wikipedia.org/wiki/Closest_pair_of_points
 * http://en.wikipedia.org/wiki/Quadtree
 * http://en.wikipedia.org/wiki/Kd-tree
 * http://en.wikipedia.org/wiki/BSP_tree
 * http://en.wikipedia.org/wiki/Kd-tree
 * http://en.wikipedia.org/wiki/Nearest_neighbor_search
 * http://en.wikipedia.org/wiki/BSP_tree
 * http://donar.umiacs.umd.edu/quadtree/index.html
 * http://stackoverflow.com/questions/253767/kdtree-implementation-in-java
 * http://stackoverflow.com/questions/4172358/all-k-nearest-neighbors-in-2d-c
 * http://www.kelvinjiang.com/2011/02/facebook-puzzles-its-small-world.html
 * http://www.cs.sunysb.edu/~algorith/implement/ANN/implement.shtml
 * http://www.java-forum.org/allgemeine-java-themen/40858-k-nearest-neighbor-algorithmus.html
 * http://www.koders.com/java/fid8BF27A76291C9E572C84A0E45B770DA5D6EFEDC2.aspx?s=idef%3Aimage
 * http://www.koders.com/java/fidEEB3EFEA42FEF7623A826A5BADE7FB971CD8A227.aspx?s=cdef%3anearest+neighbor#L7
 * http://www.codeproject.com/KB/architecture/KDTree.aspx
 * http://www.iro.umontreal.ca/~kegl/ift3390/2006_1/Lectures/l02_KDTreesMoore.pdf
 * http://home.wlu.edu/~levys/software/kd/
 * http://robowiki.net/wiki/User:Duyn/kd-tree_Tutorial
 * http://robowiki.net/wiki/User:Rednaxela/kD-Tree (!)
 * http://oceanstore.cs.berkeley.edu/publications/papers/pdf/nn-tr.pdf
 * http://home.wlu.edu/~levys/software/kd/
 * https://github.com/kanwei/algorithms/blob/91665f9bf35537b3e274d6d1618e6d2a227a2e7c/lib/containers/kd_tree.rb
 * 
 * http://kanwei.com/code/2009/02/18/facebook-Smallworld.html
 * 
 * 
 * @author Alan Ross
 */
public class Smallworld
{
	public static String INPUT_ERROR1 = "Please provide an input file: Smallworld <Inputfile>";
	public static String INPUT_ERROR2 = "Smallworld only accepts one argument, the name of the input file: Smallworld <Inputfile>";
	public static String INPUT_ERROR3 = "Please provide a valide input file: Smallworld <Inputfile>";

	private KDTree<Friend> _tree;
	private List<Friend> _friends;

	public Smallworld()
	{
	}

	public void run(String inputFileName)
	{
		if (inputFileName.length() < 1)
			System.err.println(INPUT_ERROR3);

		try
		{
			_tree = new KDTree<Friend>( 2, Integer.MAX_VALUE );
			_friends = new ArrayList<Friend>();

			Scanner fileInputScanner = new Scanner( new File( inputFileName ) );
			
			try
			{
				while (fileInputScanner.hasNextLine())
				{
					Scanner textInputScanner = new Scanner(fileInputScanner.nextLine());

					Friend f = new Friend(
							textInputScanner.nextInt(),
							textInputScanner.nextDouble(),
							textInputScanner.nextDouble()
							); 
					
					_tree.addPoint( f.location, f );
					_friends.add( f );
				}
			}
			finally
			{
				fileInputScanner.close();
			}

			Iterator<Friend> fit = _friends.iterator();
			
			while( fit.hasNext() )
			{
				Friend f = fit.next();
				
				//System.out.println( f.location[0] + " "+f.location[1] );
				List<Friend> nearest = _tree.nearestNeighbor( f.location, 4, true );
				
				System.out.print( f.id );
				System.out.print(" " + nearest.get(2).id );
				System.out.print("," + nearest.get(1).id );
				System.out.println("," + nearest.get(0).id );
			}
		}
		catch (IOException e)
		{
		}
	}

	public static void main(String args[])
	{
		if (args.length <= 0)
			System.err.println(INPUT_ERROR1);
		else if (args.length > 1)
			System.err.println(INPUT_ERROR2);

		Smallworld puzzleSolver = new Smallworld();
		puzzleSolver.run(args[0]);
	}
}