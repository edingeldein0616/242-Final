import java.util.*;

public class Vertex
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	public LinkedList<Edge> edges;
	public String name;
	public int pathDistance;
	//ADDED BY @jakelahti
	public boolean visited;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Vertex(String n)
	{
		this.name = n;
		this.edges = new LinkedList<Edge>();
		this.pathDistance = Integer.MAX_VALUE;
		//ADDED BY @jakelahti
		this.visited = false;
	}


	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Accessors  
	* * * * * * * * * * * * * * * * * * * * * * * */
	/**
	* Gets the degree of the vertex by calculating the size of it's edges list.
	* @return The degree of the vertex. 
	*/
	public int degree()
	{
		return edges.size();
	}

	/**
	* Gets the shortest path distance of the vertex instance. Used when creating Shortest Path Tree.
	* @return An integer representing the shortest path distance of the current vertex.
	*/
	public int getPathDistance()
	{
		return this.pathDistance;
	}

	/**
	* Gets an edge to the input vertex from current vertex instance.
	* @param destVert The destination endpoint of the edge to find.
	* @return Edge that contains the current vertex instance and the input vertex as endpoints. Returns null if edge doesn't exist.
	*/
	public Edge getEdgeTo(Vertex destVert)
	{
		for(Edge e : this.edges)
		{
			Vertex e1 = e.endpoint1;
			Vertex e2 = e.endpoint2;

			if(e1.equals(destVert) || e2.equals(destVert))
				return e;
		}
		return null;
	}

	/**
	* Returns an linked list of all adjacent verticies to vertex instance.
	* @return A linked list of adjacent verticies.
	*/
	public LinkedList<Vertex> getAdjacentVerts()
	{
		LinkedList<Vertex> verts = new LinkedList<Vertex>();
		for(Edge e : this.edges)
		{
			Vertex e1 = e.endpoint1;
			Vertex e2 = e.endpoint2;
			if(!e1.name.equals(this.name))
				verts.add(e1);
			else if(!e2.name.equals(this.name))
				verts.add(e2);
		}
		return verts;

	}


	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Mutators  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	/**
	* Adds an edge to the current vertex.
	* @param vert The adjacent vertex that will be the other endpoint of the edge.
	* @param weight The weight of the edge.
	*/
	public void addEdge(Vertex vert, int weight)
	{
		Edge newEdge = new Edge(this, vert, weight);
		this.edges.add(newEdge);
	}

	/**
	* Sets the shortest path distance of the vertex instance. Used when creating shortest path tree.
	* @param newPathDistance The updated path distance.
	*/
	public void setPathDistance(int newPathDistance)
	{
		this.pathDistance = newPathDistance;
	}	

	/**
	* Sorts the edge list from lowest to highest weight.
	* @output The edge list sorted in ascending order of weight.
	*/
	public void sortEdges()
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

	/*****************************************
	 *          ADDED BY @jakelahti          *
	 *****************************************/
	/**
	* @param invisit boolean that will set whether or not vertex has been visited
	* @return whether or not the vertex has been visited
	*/
	public void setVisited(boolean invisit)
	{
		visited = invisit;
	}
	public boolean getVisit()
	{
		return visited;
	}



	public String toString()
	{
		return "Name: " + name +"\n Edges --- " + edges + "\n Visited? -" + visited + "";
	}
}