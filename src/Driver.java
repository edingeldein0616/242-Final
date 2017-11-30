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

		Helper.printGraphVerticies(graph);
		System.out.println();

		Helper.searchAllGraphVerticies(graph);

	}

}