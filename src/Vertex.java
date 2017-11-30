import java.util.*;

public class Vertex
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	public LinkedList<Edge> edges;
	public String name;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Vertex(String n)
	{
		this.name = n;
		this.edges = new LinkedList<Edge>();
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
}