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

			// Remove the components to which the endpoints belong.
			int srcIndex = indexOfComponent(compSet, src);
			int destIndex = indexOfComponent(compSet, dest);
			boolean compRemoved = false;
			if(srcIndex == destIndex)
			{
				//System.out.println("Source and destination are in the same component.");
				continue;
			}

			Component srcComp;
			Component destComp;
			// Make sure verticies exist in the compSet
			if(srcIndex > -1 && destIndex > -1)
			{
				srcComp = compSet.remove(srcIndex);
				//Make sure were removing the correct destination index after removing source index.
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

			// If endpoints are in the same component, ignore this edge.
			if(srcComp.equals(destComp))
				continue;
			// If not, merge the destination component into the source component.
			srcComp.mergeComponent(destComp);

			// Add source back to compSet
			compSet.add(srcComp);

			// Add edge to the mst.
			t.edges.add(edge);
		}

		// Return the completed mst
		return t;
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