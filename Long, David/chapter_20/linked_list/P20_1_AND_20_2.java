package linked_list;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class P20_1_AND_20_2 {
	public static void main(String[] args) throws IOException {
		LinkedList<String> staff = new LinkedList<String>();
		staff.add("David");
		staff.add("Arthur");
		staff.add("Anurag");
		staff.add("Cole");
		staff.add("Richard");
		staff.add("LoL");
		staff.add("Horn");
		staff.add("Cool");
		
		reverse(staff);
		for (String i: staff) System.out.println(i);
		
		System.out.println();
		
		Collections.reverse(staff);
		for (String i: staff) System.out.println(i);
		
		System.out.println();
		
		downsize(staff);
		for (String i: staff) System.out.println(i);
	}
	
	public static void downsize(LinkedList<String> staff)
	{
		ListIterator<String> iter = staff.listIterator();
		int n = staff.size()/2;
		
		for (int i = 0; i < n; i++)
		{
			iter.next();
			iter.remove();
			iter.next();
		}
	}
	
	public static void reverse(LinkedList<String> staff)
	{
		LinkedList<String> newStaff = new LinkedList<String>();
		ListIterator<String> iter = staff.listIterator(staff.size());
		while (iter.hasPrevious())
			newStaff.add(iter.previous());
		staff.clear(); staff.addAll(newStaff);
	}
}
