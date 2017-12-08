import java.util.*;

public interface GraphInterface
{
	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Properties  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public LinkedList<Vertex> verts = new LinkedList<Vertex>();
	public LinkedList<Edge> edges = new LinkedList<Edge>();

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Accessors  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public int numVerts();
	public int numEdges();
	public LinkedList<Vertex> getAllVerts();
	public LinkedList<Edge> getAllEdges();
	public Vertex getVertex(String city, boolean printNotFound);
	public int getDegree(String city);
	public LinkedList<Edge> getEdges(String city);
	public LinkedList<Vertex> getAdjacent(String city);
	public int totalWeight();

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Mutators  
	* * * * * * * * * * * * * * * * * * * * * * * */
	public void addVertex(String city);
	public void addEdge(Vertex end1, Vertex end2, int weight);
	public void sortEdges();
	public void sortThisEdges();

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Public static functions  
	* * * * * * * * * * * * * * * * * * * * * * * */
	//public static Graph baruvkaMST(Graph graph);

}