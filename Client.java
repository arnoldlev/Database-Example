package database;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Database db = new Database();
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Course> courses = db.loadCourses();
		int selection = 0;
		
		do {
			print("Welcome! Please make a selection");
			print("1) Print all courses");
			print("2) Add a course");
			print("3) Remove a course");
			print("Or -1 to quit");
			selection = scan.nextInt();
			scan.nextLine();
			
			switch (selection) {
				case 1:
					for (Course c : courses) {
						print("\t" + c.getID() + " | " + c.getName() + " | " + c.getProfessor() + " | " + c.getUnits());
						print("\tMeeting: " + c.getInfo().getDaysMet() + " | Start: " + c.getInfo().getStartTime() + " | End: " + c.getInfo().getEndTime());
					}
					print("");
					break;
					
				case 2:
					print("Enter course ID:");
					int ID = scan.nextInt();
					scan.nextLine();
					
					print("Please enter the course name:");
					String courseName = scan.nextLine();
	
					print("Please enter professors name:");
					String name = scan.nextLine();
					
					print("Please enter the number of units:");
					double units = scan.nextDouble();
					scan.nextLine();
					
					print("Please enter the meeting days: EX (Tu/Thu or Mon/Wed/Sat");
					String meet = scan.nextLine();
					
					print("Please enter the start time: EX (5 PM)");
					String start = scan.nextLine();
					
					print("Please enter the end time: EX ( 8 PM)");
					String end = scan.nextLine();
					
					DayTime info = new DayTime(meet, start, end);
					
					Course c = new Course(ID, courseName, name, units);
					c.setInfo(info);
					db.addCourse(c);
					courses.add(c);
					print("ADDED!");
					break;
					
				case 3:
					print("Enter the course ID to remove:");
					int I = scan.nextInt();
					
					for (Course e : courses) {
						if (e.getID() == I) {
							db.deleteCourse(I);
							courses.remove(e);
						}
					}
					print("DELETED!");
					
					break;
					
				case -1:
					print("Bye!");
					break;
					
				default:
					print("Wrong Selection! Try again");
			}
	
			
		} while (selection != -1);
		
		
		

	}
	
	
	public static void print(String s) {
		System.out.println(s);
	}

}
