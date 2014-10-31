package randomStudentAccess;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDataInterface {

	public static void main(String[] args){
		//create the file
		/*
		FileOutputStream fos = new FileOutputStream(new File("h:/workspace/Long, David/chapter16/StudentData.bin"));
		fos.close();
		if (true) System.exit(0);
		*/
		
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter an existing file path or create a new one "
				+ "(e.g. H:/git-repositories/decryption/Long, David/chapter16/StudentData.bin) : ");
		boolean fileFound = false;
		
		while (!fileFound)
		{
			try
			{
				String fileName = k.nextLine();
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				StudentDataAccessor s = new StudentDataAccessor(raf);
						
				fileFound = true;
				
				boolean done = false;
				while(!done)
				try
				{
					/*
					s.addStudent(new Student (91040092, "David", "Long", 4.00));
					System.out.println(s.get_ID_of_student_n(0));
					System.out.println(s.get_firstName_of_student_n(0));
					System.out.println(s.get_lastName_of_student_n(0));	
					System.out.println(s.get_GPA_of_student_n(0));
					*/
					System.out.println();
					System.out.println("What would you like to do with the file?");
					System.out.println("1) Get Student Info");
					System.out.println("2) Get Student ID");
					System.out.println("3) Get Student First Name");
					System.out.println("4) Get Student Last Name");
					System.out.println("5) Get Student GPA");
					System.out.println("6) Add Student");
					System.out.println("7) Change Student ID");
					System.out.println("8) Change Student First Name");
					System.out.println("9) Change Student Last Name");
					System.out.println("10) Change Student GPA");
					System.out.println("11) Print All Existing Students");
					System.out.println("Any other number to Save and quit");
					int c = 0;
					boolean badInput = false;
					try 
					{
						c = k.nextInt();
					}
					catch (InputMismatchException e)
					{
						badInput = true;
					}
					k.nextLine();
					System.out.println();
					
					try{
						if (raf.length() == 0 && (c >= 1 && c <= 5))
						{
							System.out.println("No Students to access info yet");
						}
						else if (c == 1)
						{
							System.out.println("Enter number (Nth student)");
							Student stu = s.getStudentN(k.nextInt());
							System.out.println("The Student is " + stu);
						}
						else if (c == 2)
						{
							System.out.println("Enter number (Nth student)");
							System.out.println("The ID is " + s.get_ID_of_student_n(k.nextInt()));
						}
						else if (c == 3)
						{
							System.out.println("Enter number (Nth student)");
							System.out.println("The First Name is " + s.get_firstName_of_student_n(k.nextInt()));
						}
						else if (c == 4)
						{
							System.out.println("Enter number (Nth student)");
							System.out.println("The Last Name is " + s.get_lastName_of_student_n(k.nextInt()));
						}
						else if (c == 5)
						{
							System.out.println("Enter number (Nth student)");
							System.out.println(s.get_GPA_of_student_n(k.nextInt()));
						}
						else if (c == 6)
						{
							System.out.println("Enter ID #");
							int id = k.nextInt(); k.nextLine();
							System.out.println("Enter First Name");
							String f = k.nextLine();
							System.out.println("Enter Last Name");
							String l = k.nextLine();
							System.out.println("Enter GPA");
							double gpa = k.nextDouble();
							s.addStudent(id, f, l, gpa);
							System.out.println("New Student Added");	
						}
						else if (c == 7)
						{
							System.out.println("Enter number (Nth student)");
							int n = k.nextInt();
							System.out.println("Enter new desired ID #");
							int id = k.nextInt(); k.nextLine();
							s.modify_ID_of_student_n(n, id);
							System.out.println("Modified ID #");
						}
						else if (c == 8)
						{
							System.out.println("Enter number (Nth student)");
							int n = k.nextInt();
							System.out.println("Enter new desired first name");
							String f = k.nextLine();
							s.modify_firstName_of_student_n(n, f);
							System.out.println("Modified First Name");
						}
						else if (c == 9)
						{
							System.out.println("Enter number (Nth student)");
							int n = k.nextInt();
							System.out.println("Enter new desired last name");
							String l = k.nextLine();
							s.modify_lastName_of_student_n(n, l);
							System.out.println("Modified Last Name");
						}
						else if (c == 10)
						{
							System.out.println("Enter number (Nth student)");
							int n = k.nextInt();
							System.out.println("Enter new desired ID #");
							double gpa = k.nextInt(); k.nextLine();
							s.modify_GPA_of_student_n(n, gpa);
							System.out.println("Modified GPA");
						}
						else if (c == 11)
						{
							System.out.println();
							Student[] students = s.getAllStudents();
							if (students.length == 0) System.out.println("No Students Yet!");
							else System.out.println("Existing Students:");
							for (Student stu: students) System.out.println(stu);
							
						}
						else if (badInput)
						{
							System.out.println("You have inputed invalid input. Please start over.");
						}
						else
						{
							done = true;
							System.out.println("File Saved. Exiting");
						}
					}
					catch (InputMismatchException e)
					{
						System.out.println("Invalid Input. Please start over.");
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Student entered does not exist");
					}
				}
				
				finally
				{
					if (done) {raf.close();}
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File not found or could not be created. Please enter an existing file path: ");
			}
			catch (CorruptDataException e)
			{
				System.out.println("Data file corrupted. Please enter another instead: ");
			}
			catch (IOException e)
			{
				System.out.println("File error. Please enter an existing file path: ");
			}
		}
		
		k.close();
	}

}
