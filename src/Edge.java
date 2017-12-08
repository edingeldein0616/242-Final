import java.util.*;

public class Edge
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */	
	public Vertex endpoint1;
	public Vertex endpoint2;
	public int weight;
	public edgeType type = null;
	public boolean visited;

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

	/***********************************************
	 *        ADDED BY @jakelahti                  *
	 ***********************************************/
	public void setType(edgeType intype)
	{
		type = intype;
	}

	public edgeType getType()
	{
		return type;
	}

	/*****************************************
	 *          ADDED BY @jakelahti          *
	 *****************************************/
	/**
	* @param invisit boolean that will set whether or not edge has been visited
	* @return whether or not the edge has been visited
	*/
	public void setVisited(boolean invisit)
	{
		visited = invisit;
	}
	public boolean getVisit()
	{
		return visited;
	}

}