import java.io.*;
import java.util.*;

/***************

***************/

public class DFSDriver
{
    public static void main(String[] args) throws IOException
	{

		Graph graph = Helper.getGraphFromCSV("../data/DATA-FINAL-F17.txt");

		graph.sortEdges();

		Helper.searchAllGraphVerticies(graph);
    
		DFS.runDFS(graph, "Grand Forks");

	}
}
