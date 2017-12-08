import java.io.PrintWriter;
import java.io.File;

public class MSTDriver
{

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Main method
	* * * * * * * * * * * * * * * * * * * * * * * */

	public static void main(String[] args) 
	{

		// Create the graph from data file.
		Graph graph = Helper.getGraphFromCSV("../data/DATA-FINAL-F17.txt");
		// Sort all of the edges of the created graph.
		graph.sortEdges();
		graph.sortThisEdges();

		// Create an MST from graph using baruvka's algorithm
		Graph mst = Graph.baruvkaMST(graph);

		printMstInfo(mst);
		System.out.println("MST has been printed to file : ../data/MinimumSpanningTreeOutput.txt");

	}

	public static void printMstInfo(Graph graph)
	{
		try {
			PrintWriter writer = new PrintWriter(new File("../data/MinimumSpanningTreeOutput.txt"));
			writer.println("************************************************************************");
			writer.println("*************************Minimum Spanning Tree**************************");
			writer.println("************************************************************************");
			writer.println();
			writer.println("Roads of the Minimum spanning tree: \n");
			int roadNum = 1;
			for(Edge e : graph.edges)
			{
				writer.println("\t" + roadNum + "-> " + e.toString());
				roadNum++;
			}
			writer.println("\nTotal weight of the minimum spanning tree: " + graph.totalWeight() + "\n");
			writer.println("Total number of roads in the minimum spanning tree: " + graph.edges.size() + "\n");
			writer.close();

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

}