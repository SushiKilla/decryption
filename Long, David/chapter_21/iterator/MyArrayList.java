package iterator;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E>
{
	private Object[] a;
	private int size;
	private int modCount;

	public MyArrayList()
	{
		this(10);
	}

	public MyArrayList(int initialCapacity)
	{
		if (initialCapacity < 0)
			throw new IllegalArgumentException();

		a = new Object[initialCapacity];
		size = 0;
	}

	public int size()
	{
		return size;
	}

	@SuppressWarnings("unchecked")
    public E get(int index)
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		return (E) a[index];
	}

	@SuppressWarnings("unchecked")
	public E set(int index, E element)
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		modCount++;
		
		Object old = a[index];
		a[index] = element;
		return (E) old;
	}

	public boolean contains(Object elem)
	{
		for (int i = 0; i < size; i++)
		{
			if (elem == null ? a[i] == null : elem.equals(a[i]))
				return true;
		}
		return false;
	}

	public void trimToSize()
	{
		if (size < a.length)
		{
			Object[] b = new Object[size];
			System.arraycopy(a, 0, b, 0, size);
			a = b;
		}
	}

	public void add(E elem)
	{
		add(size, elem);
		modCount++;
	}

	public void add(int index, E element)
	{
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (size < a.length)
			System.arraycopy(a, index, a, index + 1, size - index);
		else
		{
			Object[] b = new Object[a.length * 2 + 1];
			System.arraycopy(a, 0, b, 0, index);
			System.arraycopy(a, index, b, index + 1, size - index);
			a = b;
		}
		a[index] = element;
		size++;
		modCount++;
	}

	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		Object old = a[index];
		System.arraycopy(a, index + 1, a, index, size - (index + 1));
		size--;
		a[size] = null;
		modCount++;
		return (E) old;
	}

	public boolean remove(Object elem)
	{
		for (int i = 0; i < size; i++)
		{
			if (elem.equals(a[i])) // TODO: TAKE NULL INTO ACCOUNT
			{
				modCount++;
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ListIterator<E> listIterator()
	{
	    return new MyListIterator();
	}
	
	public ListIterator<E> listIterator(int index)
	{
		return new MyListIterator(index);
	}
	
	private class MyListIterator implements ListIterator<E>
    {
		private int pos;
		private int iterModCount;
		private boolean canRemove = false;
		private boolean canSet = false;
		
		public MyListIterator()
		{
			pos = 0;
			iterModCount = modCount;
		}
	    public MyListIterator(int n)
        {
	        pos = n;
	        iterModCount = modCount;
        }

        private void verifyModCount()
        {
            if (modCount != iterModCount) throw new ConcurrentModificationException();
        }

        public boolean hasNext()
        {
            verifyModCount();       
            return pos < a.length;
        }

        public boolean hasPrevious()
        {
        	verifyModCount();      	
            return pos > 0;
        }

        public int previousIndex()
        {
        	verifyModCount();    	
        	return pos - 1;
        }

        public int nextIndex()
        {
        	verifyModCount();       
        	return pos + 1;
        }

        @SuppressWarnings("unchecked")
		public E previous()
        {
        	verifyModCount();
            
        	if (!hasPrevious()) throw new NoSuchElementException();

        	canSet = true;
            canRemove = true;
        	
        	return (E) a[--pos];
        }

        @SuppressWarnings("unchecked")
		public E next()
        {
        	verifyModCount();
            if (!hasNext()) throw new NoSuchElementException();
            
            canSet = true;
            canRemove = true;
            
        	return (E) a[pos++];
        }

        public void add(E o)
        {
        	verifyModCount();

        	MyArrayList.this.add(pos, o);
        	
        	canRemove = false;
        	canSet = false;
        	iterModCount++;
        }

        public void set(E o)
        {
        	verifyModCount();
            if (!canSet) throw new IllegalStateException();
            	
        	MyArrayList.this.set(pos, o);
        	iterModCount++;
        }

        public void remove()
        {
        	verifyModCount();
        	if (!canRemove) throw new IllegalStateException();   	

        	MyArrayList.this.remove(pos);
        	
        	canRemove = false;
        	canSet = false;
        	iterModCount++;
        }
    }
}
