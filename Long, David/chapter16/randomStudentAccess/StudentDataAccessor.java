package randomStudentAccess;

import java.io.*;

/**
 * Develop an application that allows users to access & modify information about students.
 * The following types of information should be supported.
      Student ID number (unique)
      First name
      Last name
      GPA

   Alternatively, the program may allow users to access & modify equivalent information
   about some other equivalent entity.

   The program should store data in a binary file with fixed length records.
   The program should not read the entire file into memory.

   The user interface must be functional but need not be elaborate.

 * @author 91040092
 *
 */
public class StudentDataAccessor {
	/*
	 * Store as binary file:
	 * 
	 * Student ID is a 4-byte integer
	 * First Name is a String of maximum 20 letters, or 20 bytes
	 * Last Name is a String of maximum 20 letters, or 20 bytes
	 * GPA is a 8-bytes double
	 * There are 49 bytes per student
	 * 
	 */
	private final int DATA_PER_STUDENT = 52;
	private RandomAccessFile raf;
	
	/**
	 * 
	 * @param raf
	 * @throws IOException
	 *PreCondition: raf is valid and is set to "rw"
	 *Data is not corrupted (all data is a multiple of 52)
	 * @throws CorruptDataException 
	 */
	public StudentDataAccessor(RandomAccessFile raf) throws IOException, CorruptDataException
	{
		if (raf.length()%DATA_PER_STUDENT != 0) throw new CorruptDataException();
		this.raf = raf;
	}
	
	public boolean nth_student_exists(int n) throws IOException
	{
		return (n*DATA_PER_STUDENT < raf.length());
	}
	
	public Student[] getAllStudents() throws IOException
	{
		Student[] stu = new Student[(int) (raf.length()/DATA_PER_STUDENT)];
		for (int i = 0; i < stu.length; i++)
			stu[i] = getStudentN(i);
		return stu;
	}
	
	/**
	 * 
	 * @param n
	 * @param ID
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public void modify_ID_of_student_n(int n, int ID) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n);
		raf.writeInt(ID);
	}
	
	/**
	 * 
	 * @param n
	 * @param firstName
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public void modify_firstName_of_student_n(int n, String firstName) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 4);
		raf.write(toByteArray(firstName));
	}
	
	/**
	 * 
	 * @param n
	 * @param lastName
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public void modify_lastName_of_student_n(int n, String lastName) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 24);
		raf.write(toByteArray(lastName));
	}
	
	/**
	 * 
	 * @param n
	 * @param ID
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public void modify_GPA_of_student_n(int n, double GPA) throws IOException
	{
		if (nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 44);
		raf.writeDouble(GPA);
	}
	
	/**
	 * 
	 * @param n
	 * @param s
	 * @throws IOException
	 * 
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public void modifyStudentN(int n, Student s) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(n * DATA_PER_STUDENT);
		raf.write(s.getID());
		raf.write(toByteArray(s.getFirstName()));
		raf.write(toByteArray(s.getLastName()));
		raf.writeDouble(s.getGPA());
	}
	
	/**
	 * Adds a student to the File and sets seek to the end of the array
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param GPA
	 * @throws IOException
	 */
	public void addStudent(int ID, String firstName, String lastName, double GPA) throws IOException
	{
		raf.seek(raf.length());
		raf.writeInt(ID);
		raf.write(toByteArray(firstName));
		raf.write(toByteArray(lastName));
		raf.writeDouble(GPA);
	}
	
	/**
	 * 
	 * @param s The Student
	 * @throws IOException
	 */
	public void addStudent(Student s) throws IOException
	{
		//System.out.println(raf.length());
		raf.seek(raf.length());
		raf.writeInt(s.getID());
		raf.write(toByteArray(s.getFirstName()));
		raf.write(toByteArray(s.getLastName()));
		raf.writeDouble(s.getGPA());
	}
	
	/**
	 * 
	 * @return
	 * PreCondition: n*DATA_PER_STUDENT < raf.length();
	 */
	public Student getStudentN(int n) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(n * DATA_PER_STUDENT);
		int ID = raf.readInt();
		String firstName = "";
		for (int i = 0; i < 20; i++) firstName += (char)raf.readByte();
		firstName = firstName.replaceAll(" ", "");
		String lastName = "";
		for (int i = 0; i < 20; i++) lastName += (char)raf.readByte();
		lastName = lastName.replaceAll(" ","");
		double GPA = raf.readDouble();
		return new Student(ID, firstName, lastName, GPA);
	}
	
	/**
	 * 
	 * @param n
	 * @param ID
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT < raf.length()
	 */
	public int get_ID_of_student_n(int n) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n);
		return raf.readInt();
	}
	
	/**
	 * 
	 * @param n
	 * @param firstName
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT + 4< raf.length()
	 */
	public String get_firstName_of_student_n(int n) throws IOException
	{
		//System.out.println(raf.length());
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 4);
		String firstName = "";
		for (int i = 0; i < 20; i++) firstName += (char)raf.readByte();
		return firstName.replaceAll(" ","");
	}
	
	/**
	 * 
	 * @param n
	 * @param lastName
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT + 24< raf.length()
	 */
	public String get_lastName_of_student_n(int n) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 24);
		String lastName = "";
		for (int i = 0; i < 20; i++) lastName += (char)raf.readByte();
		return lastName.replaceAll(" ","");
	}
	
	/**
	 * 
	 * @param n
	 * @param ID
	 * @throws IOException
	 * PreCondition: n*DATA_PER_STUDENT + 44 < raf.length()
	 */
	public double get_GPA_of_student_n(int n) throws IOException
	{
		if (!nth_student_exists(n)) throw new IllegalArgumentException();
		raf.seek(DATA_PER_STUDENT*n + 44);
		return raf.readDouble();
	}

	
	/**
	 * 
	 * @param a
	 * @return a byte array of length 20
	 * 
	 * Precondition: length of a <= 20
	 */
	public byte[] toByteArray(String a)
	{
		byte[] b = new byte[20];
		for (int i = 0; i < a.length(); i++)
		{
			b[i] = (byte)(a.charAt(i));
		}
		for (int i = a.length(); i < 20; i++)
		{
			b[i] = (byte)(' ');
		}
		return b;
	}
	
	
	
}
