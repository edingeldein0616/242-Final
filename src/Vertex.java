import java.util.*;

public class Vertex
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	public LinkedList<Edge> edges;
	public String name;
	//ADDED BY @jakelahti
	public boolean visited;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Vertex(String n)
	{
		this.name = n;
		this.edges = new LinkedList<Edge>();
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