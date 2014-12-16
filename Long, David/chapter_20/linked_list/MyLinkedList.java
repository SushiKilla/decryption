package linked_list;

import java.util.NoSuchElementException;

public class MyLinkedList<E>
{    
	private final ListNode<E> START, END;
	private int size = 0;
	
    public MyLinkedList()
    {
        START = new ListNode<E>(null);
        END = new ListNode<E>(null);
        START.next = END;
        END.prev = START;
    }
    
    //Appends the specified element to the end of this list
    public boolean add(E o)
    {
        size++;
      
        ListNode<E> temp = END.prev;
        
        ListNode<E> toAdd = new ListNode<E>(o, temp, END);
        temp.next = toAdd;
        END.prev = toAdd;
        
        return true;
    }
    
    //Inserts the specified element at the specified position in this list.
    public void add(int index, E element)
    {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        
        size++;
        
        ListNode<E> bef = START;
        for (int i = 0; i < index; i++)
        	bef = bef.next;       
        ListNode<E> after = bef.next;
        
        ListNode<E> toAdd = new ListNode<E>(element, bef, after);        
        bef.next = toAdd;
        after.prev = toAdd;
    }
    
    //Inserts the specified element at the beginning of this list.
    public void addFirst(E o)
    {
        size++;
        
        
        ListNode<E> temp = START.next;
        ListNode<E> toAdd = new ListNode<E>(o, START, temp);
 
        START.next = toAdd;
        temp.prev = toAdd;
    }
    
    //Appends the specified element to the end of this list.
    public void addLast(E o)
    {
        add(o);
    }
    
    //Returns the first element in this list.
    public E getFirst()
    {
        if (size == 0) throw new NoSuchElementException();
        return START.next.value;
    }
    
    //Returns the last element in this list.
    public E getLast()
    {
    	if (size == 0) throw new NoSuchElementException();
    	return END.prev.value;
    }
    
    public E removeFirst()
    {
    	if (size == 0) throw new NoSuchElementException();
    	
    	size--;
    	
    	ListNode<E> temp = START.next;
    	temp.next.prev = START;
    	START.next = temp.next; 	
    	return temp.value;
    }
    
    public E removeLast()
    {
    	if (size == 0) throw new NoSuchElementException();
    	
    	size--;
    	
    	ListNode<E> temp = END.prev;
    	temp.prev.next = END;
    	END.prev = temp.prev; 	
    	return temp.value;
    }
    
    public void clear()
    {
    	size = 0;
    	
    	START.next = END;
    	END.prev = START;
    }
    
    public E get(int index)
    {
    	if (index < 0 || index >= size) throw new NoSuchElementException();
    	
    	ListNode<E> temp = START.next;
    	for (int i = 0; i < index; i++)
    		temp = temp.next;
    	
    	return temp.value;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    //Retrieves and removes the head (first element) of this list.
    public E remove()
    {
        return removeFirst();
    }
    
    //Removes the element at the specified position in this list.
    public E remove(int index)
    {
    	if (index < 0 || index >= size) throw new NoSuchElementException();
    	
    	size--;
    	
    	ListNode<E> toRemove = START.next;
    	for (int i = 0; i < index; i++)
    		toRemove = toRemove.next;
    	
    	toRemove.prev.next = toRemove.next;
    	toRemove.next.prev = toRemove.prev;
    	
    	return toRemove.value;
    }
    
    public E set(int index, E element)
    {
    	if (index < 0 || index >= size) throw new NoSuchElementException();
    	
    	ListNode<E> toChange = START.next;
    	for (int i = 0; i < index; i++)
    		toChange = toChange.next;
    	
    	E temp = toChange.value;
    	toChange.value = element;
    	
    	return temp;
    }
    
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
    	String s = "";
        s += "[";
        
       	ListNode<E> temp = START.next;
        for (int i = 0; i < size; i++)
        {
        	if (i < size - 1) s += temp.value + ", ";
        	else s += temp.value;
        	temp = temp.next;	
        }
        
        s += "]";
        
        return s;
    }
    
    @SuppressWarnings("hiding")
	private class ListNode<E>
    {
        public E value;
        public ListNode<E> prev;
        public ListNode<E> next;
    
        public ListNode(E initValue)
        {
            value = initValue;
            prev = null;
            next = null;
        }
    
        private ListNode(E initValue, ListNode<E> initPrev, ListNode<E> initNext)
        {
            value = initValue;
            prev = initPrev;
            next = initNext;
        }
    }
}