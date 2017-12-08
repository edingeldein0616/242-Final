/*
 *BFS method which traverses the graph via BFS method
 *
 *@author jakelahti
 */
import java.io.*;
import java.util.*;
public class BFS
{
	
	public static void runBFS(Graph ingraph, String start) throws IOException
	{
		//Creates a QUEUE starting with start city (Grand Forks) then traverses graph
		Queue<Vertex> queue = new LinkedList<Vertex>();
		Queue<Edge> temp = new LinkedList<Edge>();
		
		/****************************************************************
		 *creates edgeType to be able to assign the proper type of edge *
		 ****************************************************************/
		edgeType cross = edgeType.valueOf("crossEdge");
		edgeType discovery = edgeType.valueOf("discoveryEdge");
		//@returns total weight of traversal
		int totalWeight = 0;
		/**************************************************
		 * @returns list of discovery edges & cross edges *
		 **************************************************/
		ArrayList<String> discovList = new ArrayList<String>();
		ArrayList<String> crossList = new ArrayList<String>();
		/***********************
		 * creates output file *
		 ***********************/
		FileWriter fileW = new FileWriter("../data/BreadthFirstSearchOutput.txt");
		PrintWriter PWrite = new PrintWriter(fileW);


		LinkedList<Vertex> verts = ingraph.getAllVerts();
		int numOfRoads = 0;
		int numOfCities = 0;

		//FINDS STARTING POINT AND ADDS TO QUEUE
		for(Vertex vert : verts)
		{
			if (start.equals(vert.name))
			{
					queue.add(vert);
			}
		}
		/**********************************************
		 * Starting of Breadth First Search Traversal *
		 **********************************************/
		queue.peek().setVisited(true);
		// System.out.println("\n----------------------BFS TRAVERSAL----------------------");
		PWrite.println("\n----------------------BFS TRAVERSAL----------------------");
		while (!queue.isEmpty())
		{
			Vertex tempV = queue.poll();
			String tempCity1 = tempV.edges.peek().endpoint1.name;
			String tempCity2 = tempV.edges.peek().endpoint2.name;
			/***************************
			 * Prints traversal of BFS *
			 ***************************/
			if (!tempV.equals(null))
			{
				// System.out.println(tempV.name);
				PWrite.println(tempV.name);
			}
			/*************************************************************
			 * compares two city names to see which one to add to queue. *
			 * add new city name not existing city                       *
			 *************************************************************/
			if (tempV.name.equals(tempCity1) && !tempV.name.equals(tempCity2))
			{
				if(tempV.edges.peekFirst().endpoint2.getVisit() == false && tempV.edges.peekFirst().getVisit() == false)
				{							
					tempV.edges.peekFirst().endpoint2.setVisited(true);
					tempV.edges.peekFirst().setVisited(true);	
					tempV.edges.peekFirst().setType(discovery);
					queue.add(tempV.edges.peekFirst().endpoint2);
					totalWeight = totalWeight +tempV.edges.peekFirst().weight;
					discovList.add(tempV.edges.peekFirst().toString());
					numOfRoads++;
				}
				else if (tempV.edges.peekFirst().getVisit() == false && tempV.edges.peekFirst().endpoint2.getVisit() == true)
				{
					tempV.edges.peekFirst().setVisited(true);
					tempV.edges.peekFirst().setType(cross);
					tempV.edges.peek();							
					crossList.add(tempV.edges.peekFirst().toString());		
					numOfRoads++;
					}
				else
				{
					if (!tempV.edges.equals(null))
					{
						tempV.edges.peek();	
					}
				}
				//System.out.println(tempV.edges.peek());
				tempV.edges.removeFirst();
			}
			else if (!tempV.name.equals(tempCity1) && tempV.name.equals(tempCity2))
			{
				if(tempV.edges.peekFirst().endpoint1.getVisit() == false && tempV.edges.peekFirst().getVisit() == false )
				{			
					tempV.edges.peekFirst().endpoint1.setVisited(true);
					tempV.edges.peekFirst().setVisited(true);
					tempV.edges.peekFirst().setType(discovery);
					queue.add(tempV.edges.peekFirst().endpoint1);
					totalWeight =totalWeight+ tempV.edges.peekFirst().weight;
					discovList.add(tempV.edges.peekFirst().toString());
					
					numOfRoads++;	
				}
				else if (tempV.edges.peekFirst().getVisit() == false && tempV.edges.peekFirst().endpoint1.getVisit() == true)
				{									
					//Marks the edge as visited
					tempV.edges.peekFirst().setVisited(true);
					//marks the edgetype as crossedge
					tempV.edges.peekFirst().setType(cross);
					crossList.add(tempV.edges.peekFirst().toString());
					numOfRoads++;
				}
				else
				{
					if (!tempV.edges.equals(null))
					{
						tempV.edges.peek();
					}
				}
				//System.out.println(tempV.edges.peek());
				tempV.edges.removeFirst();
			}
			/**********************************************************
		 	**********************************************************
		 	**********************************************************
		 	**THIS IS THE WHILE LOOP AFTER FIRST EDGE IS RUN THROUGH**
		 	**********************************************************
		 	**********************************************************
		 	**********************************************************/
			if (!tempV.edges.equals(null))				
			{
				while (tempV.edges.size() > 0)
				{	
					tempCity1 = tempV.edges.peek().endpoint1.name;
					tempCity2 = tempV.edges.peek().endpoint2.name;

					/*************************************************************
			 		* compares two city names to see which one to add to queue. *
			 		* add new city name not existing city                       *
			 		*************************************************************/
					if ((!tempV.edges.peekFirst().endpoint1.name.equals(tempV.name)) && (tempV.edges.peekFirst().endpoint2.name.equals(tempV.name)) )
					{
						if(tempV.edges.peekFirst().endpoint1.getVisit() == false && tempV.edges.peekFirst().getVisit() == false )
						{
							tempV.edges.peekFirst().endpoint1.setVisited(true);
							tempV.edges.peekFirst().setVisited(true);
							tempV.edges.peekFirst().setType(discovery);
							queue.add(tempV.edges.peekFirst().endpoint1);
							totalWeight = totalWeight+ tempV.edges.peekFirst().weight;		
							discovList.add(tempV.edges.peekFirst().toString());					
							numOfRoads++;
						}
						else if (tempV.edges.peekFirst().getVisit() == false && tempV.edges.peekFirst().endpoint1.getVisit() == true)
						{
							//Marks the edge as visited
							tempV.edges.peekFirst().setVisited(true);
							//marks the edgetype as crossedge
							tempV.edges.peekFirst().setType(cross);							
							//removes edge from tempVertex
							tempV.edges.peek();
							crossList.add(tempV.edges.peekFirst().toString());
							numOfRoads++;
						}
						else
						{
							if (!tempV.edges.equals(null))
							{
								tempV.edges.peek();
							}
						}	
					}
					else if ((!tempV.edges.peekFirst().endpoint2.name.equals(tempV.name)) && (tempV.edges.peekFirst().endpoint1.name.equals(tempV.name)) )
					{
						if(tempV.edges.peekFirst().endpoint2.getVisit() == false  && tempV.edges.peekFirst().getVisit() ==false)
						{
							tempV.edges.peekFirst().endpoint2.setVisited(true);
							tempV.edges.peekFirst().setVisited(true);	
							tempV.edges.peekFirst().setType(discovery);
							queue.add(tempV.edges.peekFirst().endpoint2);	
							totalWeight = totalWeight + tempV.edges.peekFirst().weight;
							discovList.add(tempV.edges.peekFirst().toString());
							numOfRoads++;
						}
						else if (tempV.edges.peekFirst().getVisit() == false && tempV.edges.peekFirst().endpoint2.getVisit() == true)
						{
							tempV.edges.peekFirst().setVisited(true);
							tempV.edges.peekFirst().setType(cross);
							tempV.edges.peek();
							crossList.add(tempV.edges.peekFirst().toString());
							numOfRoads++;
						}
						else
						{
							if (!tempV.edges.equals(null))
							{
								tempV.edges.peek();
							}
						}
						
					}
					
					tempV.edges.removeFirst();	
				}
			}
			
		
				numOfCities++;
			
		}
		/*******************************************************
		 * PRINTS OUT TOTAL NUMBER OF CITIES, ROAD, AND WEIGHT *
		 *******************************************************/
		// System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNumber of Cities: " + numOfCities);
		// System.out.println("Number of Roads: " + numOfRoads);
		// System.out.println("totalWeight:" + totalWeight);
		
		/****************************************
		 * PRINTS OUT TOTAL WEIGHT OF TRAVERSAL *
		 ****************************************/
		PWrite.println();
		PWrite.println("Total weight of BFS Traversal :" + totalWeight);
		
		/**********************************
		 * PRINTS LIST OF DISCOVERY EDGES *
		 **********************************/
		// System.out.println("\n----------------------------------- List of discoveryEdges");
		PWrite.println();
		PWrite.println("\n----------------------------------- List of discoveryEdges");
		for (String stuff:discovList)
		{
			// System.out.println(stuff);
			PWrite.println(stuff);
		}
		/******************************
		 * PRINTS LIST OF CROSS EDGES *
		 ******************************/
		// System.out.println("\n----------------------------------- List of crossEdges");
		PWrite.println();
		PWrite.println("\n----------------------------------- List of crossEdges");
		for (String moreStuff: crossList)
		{
			// System.out.println(moreStuff);
			PWrite.println(moreStuff);
		}

			//System.out.println("\n\n\n----------------------------------\n" + queue);
		PWrite.close();
	}

}