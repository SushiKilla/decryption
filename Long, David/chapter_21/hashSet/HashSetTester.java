package hashSet;

public class HashSetTester {

	public static void main(String[] args) {
		HashSet<Integer> s = new HashSet<Integer>();
		s.add(new Integer("100"));
		System.out.println(s.add(new Integer("100")));
		s.add(new Integer("50"));
		s.add(new Integer("750"));
		s.add(new Integer("1000"));
		s.add(new Integer("10000"));
		s.add(new Integer("1"));
		
		System.out.println(s.contains(new Integer("100")));
		System.out.println(s.remove(new Integer("100")));
	}

}
