package hashCode;

public class Student {
	private int ID;
	private String first, last;
	private double GPA;
	private final double GPA_TOLERANCE = 0.001;
	
	public Student(int ID, String firstName, String lastName, double GPA)
	{
		this.ID = ID;
		first = firstName;
		last = lastName;
		this.GPA = GPA;
	}
	
	public boolean equals(Object other)
	{
		if (!(other instanceof Student)) return false;
		return equals((Student) other);
	}
	public boolean equals(Student other)
	{
		return this.ID == other.ID && Math.abs(this.GPA - other.GPA) < GPA_TOLERANCE;
	}
	public int hashCode()
	{
		final int H_FACTOR = 19;
		return H_FACTOR*new Integer(ID).hashCode() + H_FACTOR*new Double(GPA).hashCode();
	}
	
	
	public int getID() {
		return ID;
	}
	public String getFirstName() {
		return first;
	}
	public String getLastName() {
		return last;
	}
	public double getGPA() {
		return GPA;
	}
	public String toString()
	{
		return "ID#" + ID + ": " + first + " " + last + ", has a GPA of " + GPA;
	}
	
}

