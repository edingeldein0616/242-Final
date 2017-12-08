import java.util.LinkedList;

public class Component
{

	private LinkedList<Vertex> vertSet;

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Constructor
	* * * * * * * * * * * * * * * * * * * * * * * */
	public Component(Vertex vert)
	{
		this.vertSet = new LinkedList<Vertex>();
		this.vertSet.add(vert);
	}	

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Mutators
	* * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
	* Merges this component with input component c. Discards the input component.
	* @param c A component to merge with current component instance.
	*/
	public void mergeComponent(Component c)
	{
		LinkedList<Vertex> cVertSet = c.getVertSet();
		while(cVertSet.size() != 0)
		{
			Vertex vert = cVertSet.poll();
			if(this.exists(vert))
			{
				System.out.println("This error shouldn't happen. A vertex exists in two components breaking the property of an mst.");
				return;
			}
			else
			{
				this.vertSet.add(vert);
			}
		}
		c = null;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Acessors
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Determines whether or not the input vertex exists in the component set.
	* @param vert The vertex to search for in the set.
	* @return A boolean indicating whether or not the input vertex exists in the set.
	*/
	public boolean exists(Vertex vert)
	{
		return this.vertSet.contains(vert);
	}

	/**
	* Gets the vertSet of the current instance of the component.
	* @return The linked list of verticies in this component.
	*/
	public LinkedList<Vertex> getVertSet()
	{
		return this.vertSet;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * *
	* 				Pulic static functions
	* * * * * * * * * * * * * * * * * * * * * * * */

	/**
	* Creates a new component list of verticies from an input graph.
	* @param g A graph from which to create a component vertex list.
	* @return A linked list of components each containing a single vertex from g. list.size = g.verts.size;
	*/
	public static LinkedList<Component> newCompSet(Graph g)
	{
		LinkedList<Component> newSet = new LinkedList<Component>();
		for(Vertex vert : g.verts)
		{
			newSet.add(new Component(vert));
		}
		return newSet;
	}

}