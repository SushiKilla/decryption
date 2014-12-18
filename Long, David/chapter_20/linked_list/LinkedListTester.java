package linked_list;

public class LinkedListTester {

	public static void main(String[] args) {
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("A");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add(1, "B");
		
		System.out.println(list.toString());
		System.out.println(list.size());
		
		list.removeLast();
		System.out.println(list.toString());
		System.out.println(list.size());
		
		
		list.remove(1);
		System.out.println(list.toString());
		System.out.println(list.size());
		
		list.clear();
		System.out.println(list.size());
		
	}

}
