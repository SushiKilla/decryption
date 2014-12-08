package hashSet;

import java.util.ArrayList;

public class HashSet<E> {
	private final int FIXED_LENGTH = 1000;
	private ArrayList<E> list[];
	
	public HashSet()
	{
		list = new ArrayList[FIXED_LENGTH];
		for (int i = 0; i < list.length; i++)
		{
			list[i] = new ArrayList<E>();
		}
	}
	
	public boolean add(E e)
	{
		int pos = Math.abs(e.hashCode()) % FIXED_LENGTH;
		boolean inArray = false;
		for (E obj: list[pos])
			if (obj.equals(e))
			{
				inArray = true;
				break;
			}
		if (inArray)
			return false;
		list[pos].add(e);
		return true;
	}
	
	public boolean contains(Object o)
	{
		int pos = Math.abs(o.hashCode()) % FIXED_LENGTH;
		return list[pos].contains(o);
	}
	
	public boolean remove(Object o)
	{
		int pos = Math.abs(o.hashCode()) % FIXED_LENGTH;
		return list[pos].remove(o);
	}
	
	public int size()
	{
		int size = 0;
		for (int i = 0; i < list.length; i++)
		{
			size += list[i].size();
		}	
		return size;
	}	
}
