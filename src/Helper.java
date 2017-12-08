import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;

public class Helper
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Helper Methods
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Creates a graph from an input CSV file.
	* @param filename The name of the CSV file.
	* @return A graph created with the data from the CSV file.
	*/
	public static Graph getGraphFromCSV(String filename)
	{
		Graph newGraph = new Graph();
		try {
			
			Scanner scnr = new Scanner(new File(filename));

			// File read loop
			int line = 0;
			while(scnr.hasNext())
			{
				String readLine = scnr.nextLine();
				
				line++;

				if(line < 3)
					continue;

				String[] splitLine = readLine.split("\t");
				String nameVert1 = splitLine[0].replaceAll("^\"|\"$", "");
				String nameVert2 = splitLine[1].replaceAll("^\"|\"$", "");

				newGraph.addVertex(nameVert1);
				newGraph.addVertex(nameVert2);

				//Add edges
				Vertex vert1 = newGraph.getVertex(nameVert1, false);
				Vertex vert2 = newGraph.getVertex(nameVert2, false);
				int weight = Integer.parseInt(splitLine[2]);
				newGraph.addEdge(vert1, vert2, weight);

			}

			scnr.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		return newGraph;
	}

	/**
	* Prints the names of all verticies from the input graph to the console.
	* @param The graph whose verticies will be printed to the console.
	* @output The names of all the verticies to the console.
	*/
	public static void printGraphVerticies(Graph graph)
	{

		LinkedList<Vertex> verts = graph.getAllVerts();
		int i = 1;
		
		for(Vertex vert : verts)
		{
			System.out.println(i + ": " + vert.name);
			for(Edge edge : vert.edges)
			{
				System.out.println("\t" + edge.toString());
			}
			System.out.println("\n");
			i++;
		}

	}

	public static void searchAllGraphVerticies(Graph graph) {
		LinkedList<Vertex> verts = graph.verts;

		for(Vertex vert : verts)
		{
			graph.getVertex(vert.name, true);
		}
		
	}
}