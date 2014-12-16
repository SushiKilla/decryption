package iterator;

import java.util.ListIterator;

public class IteratorTester {

	public static void main(String[] args) {
		MyArrayList<String> list = new MyArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		
		//ListIterator<String> it = list.listIterator();
		//System.out.println(it.previous());
		
		//ListIterator<String> it = list.listIterator();
		//System.out.println(it.next());
		
		/*
		ListIterator<String> it = list.listIterator();
		it.add("0");
		it.remove();
		 */
		/*
		ListIterator<String> it = list.listIterator();
		System.out.println(it.next());
		it.remove();
		it.remove();
		*/
		/*
		ListIterator<String> it = list.listIterator();
		list.add("0");
		it.next();
		*/
	}

}
