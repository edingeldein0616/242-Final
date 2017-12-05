import java.io.*;
import java.util.*;

public class Driver
{

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Main method
	* * * * * * * * * * * * * * * * * * * * * * * */

	public static void main(String[] args) 
	{

		Graph graph = Helper.getGraphFromCSV("../data/DATA-FINAL-F17.txt");

		graph.sortEdges();
		graph.sortThisEdges();
		System.out.println("\"graph\" edges:");
		tester(graph);

		Graph mst = Graph.baruvkaMST(graph);
		System.out.println();
		System.out.println("\"mst\" edges:");
		tester(mst);
		System.out.println();

		System.out.println("Number of edges in \"graph\": " + graph.edges.size());
		System.out.println("Number of verts in \"graph\": " + graph.verts.size());
		System.out.println("Number of edges in \"mst\": " + mst.edges.size());
		System.out.println("Number of verts in \"mst\": " + mst.verts.size());
		//Helper.printGraphVerticies(graph);
		System.out.println();

		//Helper.searchAllGraphVerticies(graph);

	}

	public static void tester(Graph graph)
	{
		int i = 1;
		while(i < graph.edges.size())
		{
			Edge edge = graph.edges.get(i - 1);
			System.out.println(i + ":" + edge.toString());
			i++;
		}
	}

}