import java.util.PriorityQueue;
import java.util.LinkedList;

public class SSSPDriver
{
	public static void main(String[] args)
	{
		
		Graph graph = Helper.getGraphFromCSV("../data/DATA-FINAL-F17.txt");

		LinkedList<Path> path = new LinkedList<Path>();
		path.add(Graph.findShortestPath("Grand Forks", "Seattle", graph));
		path.add(Graph.findShortestPath("Seattle", "Los Angeles", graph));
		path.add(Graph.findShortestPath("Los Angeles", "Dallas", graph));
		path.add(Graph.findShortestPath("Dallas", "Miami", graph));
		path.add(Graph.findShortestPath("Miami", "Boston", graph));
		path.add(Graph.findShortestPath("Boston", "Chicago", graph));
		path.add(Graph.findShortestPath("Chicago", "Grand Forks", graph));

		int totalPathDistance = 0;
		for(Path p : path)
		{
			printit(p);
			totalPathDistance += p.getPathLength();
		}

		System.out.println("\nLength of the total circular path from Grand Forks to Grand Forks: " + totalPathDistance);

	}

	public static void printit(Path path) {
		System.out.println(path.src + " - " + path.dest);
		System.out.println("\tCities:");
		int i = 1;
		for(Vertex vert : path.verts)
		{
			System.out.println("\t\t" + i + " -> " + vert.name);
			i++;
		}
		System.out.println("\n\tRoads:");
		i = 1;
		for(Edge edge : path.edges)
		{
			System.out.println("\t\t" + i + " -> " + edge.toString());
			i++;
		}
		System.out.println();
		System.out.println("Distance of route: " + path.getPathLength());
		System.out.println();
	}

}