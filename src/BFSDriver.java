import java.io.*;
import java.util.*;

public class BFSDriver
{

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Main method
	* * * * * * * * * * * * * * * * * * * * * * * */

	public static void main(String[] args) throws IOException
	{

		Graph graph = Helper.getGraphFromCSV("../data/DATA-FINAL-F17.txt");

		graph.sortEdges();

		Helper.searchAllGraphVerticies(graph);

		BFS.runBFS(graph, "Grand Forks");

	}

}