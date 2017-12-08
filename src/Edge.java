import java.util.*;

public class Edge
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	public Vertex endpoint1;
	public Vertex endpoint2;
	public int weight;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructors
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Edge(Vertex end1, Vertex end2, int w)
	{
		this.endpoint1 = end1;
		this.endpoint2 = end2;
		this.weight = w;
	}

	public Edge(Edge edgeCopy)
	{
		this.endpoint1 = edgeCopy.endpoint1;
		this.endpoint2 = edgeCopy.endpoint2;
		this.weight = edgeCopy.weight;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Utilities  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public String toString()
	{
		return "(" + this.endpoint1.name + " - " + this.endpoint2.name + ") : " + this.weight;
	}
}