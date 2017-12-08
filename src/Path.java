import java.util.LinkedList;

public class Path
{
	public String src;
	public String dest;
	public LinkedList<Vertex> verts;
	public LinkedList<Edge> edges;

	public Path(String s, String d)
	{
		this.src = s;
		this.dest = d;
		this.verts = new LinkedList<Vertex>();
		this.edges = new LinkedList<Edge>();
	}

	public int getPathLength()
	{
		int total = 0;
		for(Edge e : this.edges)
		{
			total += e.weight;
		}
		return total;
	}
}