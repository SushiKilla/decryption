package randomStudentAccess;

public class Student {
	private int ID;
	private String first, last;
	private double GPA;
	
	public Student(int ID, String firstName, String lastName, double GPA)
	{
		this.ID = ID;
		first = firstName;
		last = lastName;
		this.GPA = GPA;
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
