package database;

public class Course {
	
	private int ID;
	private String name;
	private String professor;
	private double units;
	private DayTime info;
	
	public Course() {
		
	}
	
	public Course(int ID, String name, String prof, double units) {
		setID(ID);
		setName(name);
		setProfessor(prof);
		setUnits(units);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public DayTime getInfo() {
		return info;
	}

	public void setInfo(DayTime info) {
		this.info = info;
	}

}
