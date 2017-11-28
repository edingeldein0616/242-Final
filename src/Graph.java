import java.util.*;

public class Graph implements GraphInterface
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public LinkedList<Vertex> verts;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Graph()
	{
		this.verts = new LinkedList<Vertex>();
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
			allEdges.add(vert.edges);
		}
		return allEdges;
	}

	/**
	* Searches the verts array for a vertex whos name matches the input parameter.
	* @param name The name of the vertex to retrieve.
	* @return The vertex. Null if it does not exist.
	*/
	public Vertex getVertex(String name)
	{
		for(Vertex vert : this.verts)
		{
			if(vert.name.equals(name))
				return vert;
		}
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
		Vertex vert = getVertex(name);
		if(vert != null)
		{
			return vert.degree;
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
		Vertex vert = getVertex(name);
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
		Vertex vert = getVertex(name);

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

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Mutators  
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Creates and adds a vertex to the verts list of the current graph.
	* @param name The name of the vertex to be created and added.
	*/
	public void addVertex(String name)
	{
		name = String.replaceAll("^\"|\"$", "");
		Vertex vert = new Vertex(name);
		this.verts.add(vert);
	}

	/**
	* Creates an edge between two verticies and adds it to their edges list.
	* @param end1 The first endpoint of the edge.
	* @param end2 The second endpoint of the edge.
	*/
	public void addEdge(Vertex end1, Vertex end2, int weight)
	{
		Edge edge = new Edge(end1, end2, weight);
		end1.edges.add(edge);
		end2.edges.add(edge);
	}
}