import java.util.*;

/**
* A class that implements the data structures and and methods needed to 
* create a graph.
*/
public class Graph implements GraphInterface
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties
	* * * * * * * * * * * * * * * * * * * * * * * */
	public LinkedList<Vertex> verts;
	public LinkedList<Edge> edges;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Graph()
	{
		this.verts = new LinkedList<Vertex>();
		this.edges = new LinkedList<Edge>();
	}	

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Accessors
	* * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
	* Gets the number of verticies in the graph.
	* @return The number of verticies.
	*/
	public int numVerts()
	{
		return this.verts.size();
	}

	/**
	* Gets the total number of edges in the graph.
	* @return Number of edges.
	*/
	public int numEdges() 
	{
		int total = 0;
		for(Vertex vert : this.verts)
		{
			total += vert.edges.size();
		}
		return total;
	}

	/**
	* Gets the verts list.
	* @return The verts list.
	*/
	public LinkedList<Vertex> getAllVerts()
	{
		return this.verts;
	}

	/**
	* Merges the edges list of every vertex into a single list and returns it.
	* @return A list of all edges in the graph.
	*/
	public LinkedList<Edge> getAllEdges()
	{
		LinkedList<Edge> allEdges = new LinkedList<Edge>();
		for(Vertex vert : this.verts)
		{
			allEdges.addAll(vert.edges);
		}
		return allEdges;
	}

	/**
	* Searches the verts array for a vertex whos name matches the input parameter.
	* @param name The name of the vertex to retrieve.
	* @param printNotFound Prints console output when vertex isn't found if true.
	* @return The vertex. Null if it does not exist.
	*/
	public Vertex getVertex(String name, boolean printNotFound)
	{
		for(Vertex vert : this.verts)
		{
			if(vert.name.equals(name))
				return vert;
		}
		if(printNotFound)
			System.out.println("Vertex with name \"" + name + "\" does not exist in the graph.");
		
		return null;
	}

	/**
	* Gets the degree of the vertex whoes name matches the input parameter.
	* @param name The name of the vertex from which to get the degree.
	* @param The degree of the vertex. -1 if the vertex does not exist.
	*/
	public int getDegree(String name)
	{
		Vertex vert = getVertex(name, true);
		if(vert != null)
		{
			return vert.degree();
		} 
		System.out.println("\tCannot retrieve the degree.");
		return -1;
	}

	/**
	* Gets the edge array of the vertex whoes name matches the input parameter.
	* @param name The name of the vertex from which to get the edge array.
	* @return The edge array of the vertex. Null if it does not exist.
	*/
	public LinkedList<Edge> getEdges(String name)
	{
		Vertex vert = getVertex(name, true);
		if(vert != null)
		{
			return vert.edges;
		}
		System.out.println("\tCannot retrieve the edges.");
		return null;
	}

	/**
	* Creates a list of adjacent verticies to the vertex matching the input name parameter.
	* @param name The name of the vertex from which to get it's adjacencies.
	* @return A list of adjacent verticies to the vertex. Null if vertex doesn't exist.
	*/
	public LinkedList<Vertex> getAdjacent(String name)
	{
		LinkedList<Vertex> adjacencies = new LinkedList<Vertex>();
		Vertex vert = getVertex(name, true);

		if(vert == null)
		{
			System.out.println("\tCannot get adjacencies.");
			return null;
		}

		for(Edge edge : vert.edges)
		{
			if(!edge.endpoint1.equals(vert)) adjacencies.add(edge.endpoint1);
			if(!edge.endpoint2.equals(vert)) adjacencies.add(edge.endpoint2);
		}
		return adjacencies;
	}

	/**
	* Copies the edges array of the current graph instance.
	* @return A copy of this graphs edges array.
	*/
	public LinkedList<Edge> copyEdges()
	{
		LinkedList<Edge> newEdges = new LinkedList<Edge>();
		for(Edge edge : this.edges)
		{
			newEdges.add(new Edge(edge));
		}
		return newEdges;
	}

	/**
	* Returns the total weight of the graph's edges.
	* @return An integer representing the total weight of the edges of the graph.
	*/
	public int totalWeight()
	{
		int total = 0;
		for(Edge e : this.edges)
		{
			total += e.weight;
		}
		return total;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Mutators
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Creates and adds a vertex to the verts list of the current graph.
	* If the vertex already exists in the graph, it will not be added.
	* @param name The name of the vertex to be created and added.
	*/
	public void addVertex(String name)
	{
		Vertex vert = getVertex(name, false);
		if(vert == null)
		{
			vert = new Vertex(name);
			this.verts.add(vert);
		}
	}

	/**
	* Creates an edge between two verticies and adds it to their edges list.
	* @param end1 The first endpoint of the edge.
	* @param end2 The second endpoint of the edge.
	*/
	public void addEdge(Vertex end1, Vertex end2, int weight)
	{
		Edge edge = new Edge(end1, end2, weight);
		this.edges.add(edge);
		end1.edges.add(edge);
		end2.edges.add(edge);
	}

	/**
	* Sorts the edges of all of the verticies in the graph from lowest to highest weight.
	* @output The edges list of each vertex int the graph will be sorted in ascending order of weight.
	*/
	public void sortEdges()
	{
		for(Vertex vert : this.verts)
		{
			vert.sortEdges();
		}
	}

	/**
	* Sorts the edges list of the current graph instance.
	* @output The edges list of this graph will be sorted in ascending order of weight.
	*/
	public void sortThisEdges()
	{
		int listSize = this.edges.size();
		EdgeWeightComparator comp = new EdgeWeightComparator();
		PriorityQueue<Edge> pqueue = new PriorityQueue<Edge>(listSize, new EdgeWeightComparator());
		while(this.edges.size() != 0)
		{
			pqueue.add(this.edges.removeFirst());
		}

		while(pqueue.size() != 0)
		{
			Edge e = pqueue.poll();
			this.edges.add(e);
		}
	}

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Public static functions  
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Creates a new graph that adheres to the properties of an MST that is a subgraph of the input graph.
	* @param graph The graph from which to create the MST.
	* @return A graph that adheres to the properties of an MST that contains all the verticies of the input graph.
	*/
	public static Graph baruvkaMST(Graph graph)
	{
		// Create a subgraph mst
		Graph t = createSubgraph(graph);
		// Create component list of t
		LinkedList<Component> compSet = Component.newCompSet(t);
		// Get new edges list of graph
		LinkedList<Edge> edges = graph.copyEdges();

		// If E.size of t is less than (V.size - 1) of graph, t is not an mst
		while(t.edges.size() <= graph.verts.size() - 1 && compSet.size() > 1)
		{
			// Get the lowest weighted edge and it's endpoints
			Edge edge = edges.poll();
			Vertex src = edge.endpoint1;
			Vertex dest = edge.endpoint2;			

			// Find the index of the components that contain the endpoints
			int srcIndex = indexOfComponent(compSet, src);
			int destIndex = indexOfComponent(compSet, dest);

			// If the endpoints are in the same component, skip this edge and continue.
			if(srcIndex == destIndex)
				continue;

			Component srcComp;
			Component destComp;
			// Make sure verticies exist in the compSet
			if(srcIndex > -1 && destIndex > -1)
			{
				srcComp = compSet.remove(srcIndex);
				//Make sure were removing the correct destination index after removing source index.
				// (If we remove srcIndex that is before destIndex in the list, it changes the index of destIndex)
				if(destIndex > srcIndex)
					destComp = compSet.remove(destIndex - 1);
				else
					destComp = compSet.remove(destIndex);
			}
			else
			{
				System.out.println("Source or destination index is -1");
				continue;
			}

			// Merge the destination component into the source component now that they are connected by the new edge.
			srcComp.mergeComponent(destComp);

			// Add merged component back to compSet
			compSet.add(srcComp);

			// Add edge to the mst.
			t.edges.add(edge);
		}

		// Return the completed mst
		return t;
	}

	/**
	* Creates a new graph that contains all of the verticies of the input graph and adds a minimum path weight to each vertex.
	* @param graph The graph from which to create a SPT.
	* @param startingVertex The starting vertex of the SPT.
	* @return A graph that contains all of the verticies of the input graph each with its shortest distance from the starting
	* vertex and the edges included in the SPT.
	*/
	public static Graph dijkstraSPT(Graph graph, Vertex startingVertex)
	{
		
	}

	/**
	* Duplicates the graph g and creates a new graph that contains only the verticies from g.
	* @param g The graph to be duplicated.
	* @return The duplicated subgraph that contains all the same verticies as g.
	*/
	public static Graph createSubgraph(Graph g)
	{
		Graph newSubgraph = new Graph();
		for(Vertex vert : g.verts)
		{
			newSubgraph.verts.add(vert);
		}
		return newSubgraph;
	}

	/**
	* Gets the index of the component that contains the input vertex. Returns -1 of vertex doesn't exist in set.
	* @param compSet The set of components that should contain the search vertex.
	* @param searchVert The vertex to find in the compSet.
	* @return An integer representing the index of the component in the set that contains the input vertex.
	*/
	public static int indexOfComponent(LinkedList<Component> compSet, Vertex searchVert)
	{
		int index = 0;
		for(Component comp : compSet)
		{
			for(Vertex vert : comp.getVertSet())
			{
				if(vert.name.equals(searchVert.name))
				{
					//System.out.println("GOOGLY: " + index);
					return index;
				}
			}
			index++;
		}
		return -1;
	}
}