package linked_list;

import java.util.NoSuchElementException;

public class MyLinkedList<E>
{    
	private final ListNode<E> START, END;
	private int size = 0;
	
    public MyLinkedList()
    {
        START = new ListNode(null);
        END = new ListNode(null);
        START.next = END;
        END.prev = START;
    }
    
    //Appends the specified element to the end of this list
    public boolean add(E o)
    {
        size++;
        ListNode n = new ListNode(o);
        ListNode temp = END.prev;
        
        temp.next = n;
        n.prev = temp;
        END.prev = n;
        n.next = END;
        
        return true;
    }
    
    //Inserts the specified element at the specified position in this list.
    public void add(int index, E element)
    {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        
        size++;
        
        ListNode bef = START;
        for (int i = 0; i < index; i++)
        	bef = bef.next;       
        ListNode after = bef.next;
        
        ListNode toAdd = new ListNode(element);
        
        bef.next = toAdd;
        toAdd.prev = bef;
        
        after.prev = toAdd;
        toAdd.next = after;     
    }
    
    //Inserts the specified element at the beginning of this list.
    public void addFirst(E o)
    {
        size++;
        
        ListNode n = new ListNode(o);
        ListNode temp = START.next;
        
        START.next = n;
        n.prev = START;
        
        temp.prev = n;
        n.next = temp;
    }
    
    //Appends the specified element to the end of this list.
    public void addLast(E o)
    {
        add(o);
    }
    
    public E getFirst()
    {
        
    }
    
    public E getLast()
    {
        
    }
    
    public E removeFirst()
    {
        
    }
    
    public E removeLast()
    {
        
    }
    
    public void clear()
    {
        
    }
    
    public E get(int index)
    {
        
    }
    
    public boolean isEmpty()
    {
        
    }
    
    public E remove()
    {
        
    }
    
    public E remove(int index)
    {
        
    }
    
    public E set(int index, E element)
    {
        
    }
    
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
        
    }
    
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