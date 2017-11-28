import java.util.*;

public class Edge
{
	public Vertex endpoint1;
	public Vertex endpoint2;
	public int weight;

	public Edge(Vertex end1, Vertex end2, int w)
	{
		this.endpoint1 = end1;
		this.endpoint2 = end2;
		this.weight = w;
	}
}