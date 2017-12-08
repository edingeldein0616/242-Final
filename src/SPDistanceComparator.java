import java.util.Comparator;

public class SPDistanceComparator implements Comparator<Vertex> {
	
	@Override
	public int compare(Vertex x, Vertex y)
	{
		if (x.getPathDistance() < y.getPathDistance())
        {
            return -1;
        }
        if (x.getPathDistance() > y.getPathDistance())
        {
            return 1;
        }
        return 0;
	}

	public int equals(Vertex v1, Vertex v2)
	{
		return v2.getPathDistance() - v1.getPathDistance();
	}

}