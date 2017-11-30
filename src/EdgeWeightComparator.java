import java.util.Comparator;

public class EdgeWeightComparator implements Comparator<Edge>
{
	@Override
	public int compare(Edge x, Edge y)
	{
		if (x.weight < y.weight)
        {
            return -1;
        }
        if (x.weight > y.weight)
        {
            return 1;
        }
        return 0;
	}

	public int equals(Edge e1, Edge e2)
	{
		return e2.weight - e1.weight;
	}
}