/*Eliot Golden DFS Search
Does some fancy DFS magic and jazz
*/

import java.io.*;
import java.util.*;




public class DFS
{

    static edgeType cross = edgeType.valueOf("crossEdge");
	static edgeType discovery = edgeType.valueOf("discoveryEdge");
	static Queue<Vertex> queue = new LinkedList<Vertex>();
	static Queue<Edge> temp = new LinkedList<Edge>();
	static LinkedList<Edge>	discEdge = new LinkedList<Edge>();
	static LinkedList<Edge>	backEdge = new LinkedList<Edge>();
	
    public static void doDFS(Graph g, Vertex v)
    {
        //v.edges.peekFirst().setVisited(true);
        v.setVisited(true);
        System.out.println(v.name);
        for(Edge e : v.edges)
        {
            Vertex w = null;
            
            if(e.getVisit() == false)
            {
                if (!e.endpoint1.equals(v) && !e.endpoint1.equals(null))
                {
                    w = e.endpoint1;
                }
                else if (!e.endpoint2.equals(v) && !e.endpoint2.equals(null))
                {
                    w = e.endpoint2;
                }
                
                if (!w.getVisit())
                {
                    e.setType(discovery);
                    discEdge.add(e);
                    doDFS(g, w);
                 }
                else
                {
                    e.setType(cross);
                    if(!backEdge.contains(e) && !discEdge.contains(e))
                        backEdge.add(e);
                
                }
            }
            
        }
    
    }

    public static void runDFS(Graph ingraph, String start) throws IOException
    {
        //Start at Grand FRoks

		
		
		int totalWeight = 0;
		
		ArrayList<String> discovList = new ArrayList<String>();
        ArrayList<String> crossList = new ArrayList<String>();
        
        //FileWriter scribble = new FileWriter("../data/BreadthFirstSearchOutput.txt");
        //PrintWriter PWrite = new PrintWriter(scribble);
        
        LinkedList<Vertex> verts = ingraph.getAllVerts();
        int numOfRoads = 0;
        int numOfCities = 0;
        
        for(Vertex vert : verts)
        {
            if (start.equals(vert.name))
			{
					queue.add(vert);
			}
        }
        queue.peek().setVisited(true);
        System.out.println("\n---------VERTICIES IN ORDER OF DFS TRAVERSAL------------");
        doDFS(ingraph, queue.peek());
        
        while (!queue.isEmpty())
        {
            System.out.println(queue.remove());
        }
        
        /*
        * Dis
        */
        int i = 1;
        int totWait = 0;
        System.out.println("-------------DISCOVERY EDGES-------------");
        for(Edge eee: discEdge)
        {
            System.out.println(i + " -> " + eee);
            i++;
            totWait = totWait + eee.weight;
        }
        System.out.println("-------------BACK EDGES-------------");
        i = 0;
        for(Edge ee: backEdge)
        {
            System.out.println(i + " -> " + ee);
            i++;
        }
        
        System.out.println("Total Weight for traversal of graph: " + totWait);
    }
    
}


