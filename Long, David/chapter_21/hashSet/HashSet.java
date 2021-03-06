package hashSet;

import java.util.ArrayList;

public class HashSet<E> {
	private final int FIXED_LENGTH = 1000;
	private ArrayList<E> list[];
	private int size = 0;
	
	@SuppressWarnings("unchecked")
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
		if (list[pos].contains(e)) return false;
		list[pos].add(e);
		size++;
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
		return size;
	}	
}
