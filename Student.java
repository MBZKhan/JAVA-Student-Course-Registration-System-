package crs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Student class represents a student in the course registration system.
 * It extends the User class and implements the StudentInterface and Serializable interfaces.
 * It contains methods for viewing courses, registering for courses, withdrawing from courses,
 * and viewing registered courses.
 */
public class Student extends User implements StudentInterface, java.io.Serializable {

	public static ArrayList<String> registeredCourses = new ArrayList<String>();
	public String firstName;
	public String lastName;
	public String username;
	public String password;
	
	// Define constructors
	public Student() {
		
	}
	
	//public static Course course = new Course();
	public static Student student = new Student();
	//public static crsData crsdata = new crsData();
	public Student (String fN, String lN, String usn, String psw, ArrayList<String> courses) {
		this.firstName = fN;
		this.lastName = lN;
		this.username = usn;
		this.password = psw;
		Student.registeredCourses = courses;
	}
	
	/**
	 * Returns the list of registered courses.
	 * @return ArrayList of registered courses.
	 */
	public static ArrayList<String> getRegisteredCourses() {
		return registeredCourses;
	}

	/**
	 * Sets the list of registered courses.
	 * @param registeredCourses ArrayList of registered courses.
	 */
	public static void setRegisteredCourses(ArrayList<String> registeredCourses) {
		Student.registeredCourses = registeredCourses;
	}
	
	/**
	 * Returns the first name of the student.
	 * @return First name of the student.
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the student.
	 * @param firstName First name of the student.
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the student.
	 * @return Last name of the student.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 * @param lastName Last name of the student.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the username of the student.
	 * @return Username of the student.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the student.
	 * @param username Username of the student.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the password of the student.
	 * @return Password of the student.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the student.
	 * @param password Password of the student.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns a string representation of the student object.
	 * @return String representation of the student.
	 */
	public String toString () {
		return this.firstName + " " + this.lastName;
	}

	// Student methods
	
	//WORKS
	/**
	 * Displays all the courses available in the system.
	 */
	public static void viewAllCourses() {
		System.out.println(crsMain.courseArrayList);
	}

	//WORKS
	/**
	 * Displays the courses that are not full (still have available slots).
	 * @throws IOException if an I/O error occurs.
	 */
	public static void viewNotFullCourses() throws IOException {
		// In ArrayList of courses, if Max# == enrolled# return course info
		Course course = new Course();
		for (int i = 0; i<crsMain.courseArrayList.size(); i++) {
			course = crsMain.courseArrayList.get(i);
			if (course.getEnrolledStudents() != course.getMaxStudents()) {
				String courseID = course.getCourseID();
				fullCourseID = courseID;
				System.out.println(courseID);
			}
		}
	}

	//WORKS
	/**
	 * Allows the student to register for a course by providing the course ID and verifying their name.
	 */
	public static void register() {
		Course course = new Course();
		ArrayList<String> regCourse = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		// Enter courseID
		System.out.print("Enter courseID: ");
		String courseID = input.nextLine();
		// Add courseID to list of registered courses

		// Add name to registered students
		System.out.println("Verify full name: ");
		String firstName = input.nextLine();
		System.out.println("Verify last name: ");
		String lastName = input.nextLine();
		String name = firstName + " " + lastName;

		for (int i = 0; i<crsData.students.size(); i++) {
			Student student = crsData.students.get(i);
			if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {

				for (int j = 0; j<crsMain.courseArrayList.size(); j++) {
					course = crsMain.courseArrayList.get(j);

					if (courseID.equals(course.getCourseID())) {
						course.addStudent(name);

					}
					if (course.getEnrolledStudents() == course.getMaxStudents()) {
						System.out.println("Course is full.");
						//break;
					}
				}
			}
			crsData.courses.add(courseID);
		}
		System.out.println(crsData.courses);
	}

	//WORKS
	/**
	 * Allows the student to withdraw from a course by providing the course ID and verifying their name.
	 */
	public static void withdraw() {
		Course course = new Course();
		// Enter courseID
		Scanner input = new Scanner (System.in);
		System.out.print("Enter courseID: ");
		String courseID = input.nextLine();
		System.out.print("Verify first name: ");
		String firstName = input.nextLine();
		System.out.print("Verify last name: ");
		String lastName = input.nextLine();
		String name = firstName + " " + lastName;

		for (int i = 0; i<crsData.students.size(); i++) {
			Student student = crsData.students.get(i);
			if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {

				for (int j = 0; j<crsMain.courseArrayList.size(); j++) {
					course = crsMain.courseArrayList.get(j);

					if (courseID.equals(course.getCourseID())) {
						course.removeStudent(name);
					}
				}
			}
			crsData.courses.remove(courseID);
		}
		System.out.println(crsData.courses.toString());
	}

	/**
	 * Displays the courses in which the student is registered.
	 */
	public static void viewYourCourses() {
		// Serialization.courseDeserialization();
		// Print out list of registered courses

		System.out.println(crsData.courses.toString());
		// if courseID is in csv file, print out course name also
	}

	// Storage of courses
}
