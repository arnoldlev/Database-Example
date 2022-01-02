package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
    
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://<host>:3306/<db>";
    private static final String USERNAME = "<user>";
    private static final String PASSWORD = "<pw>";
    private Connection connection;
    private Properties properties;
    
    public void deleteCourse(int ID) {
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("DELETE FROM courses WHERE courseID = ?");
    		stat.setInt(1, ID);
    		stat.execute();
    		
    		stat.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    }
    
    public void addCourse(Course c) {
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("INSERT INTO courses(courseID, name, prof, units) VALUES (?,?,?,?)");
    		stat.setInt(1, c.getID());
    		stat.setString(2, c.getName());
    		stat.setString(3, c.getProfessor());
    		stat.setDouble(4, c.getUnits());
	    	stat.execute();
	    	
    		stat.close();
    		
    		
    		stat = con.prepareStatement("INSERT INTO daytime(courseID, meet, start, end) VALUES (?,?,?,?)");
    		stat.setInt(1, c.getID());
    		stat.setString(2, c.getInfo().getDaysMet());
    		stat.setString(3, c.getInfo().getStartTime());
    		stat.setString(4, c.getInfo().getEndTime());
    		stat.execute();
    		
    		stat.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    }
    
    public ArrayList<Course> loadCourses() {
    	ArrayList<Course> courses = new ArrayList<Course>();
    	try {
    		Connection con = connect();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM courses, daytime");
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			Course c = new Course(rs.getInt("courseID"), rs.getString("name"), rs.getString("prof"), rs.getDouble("units"));
    			DayTime info = new DayTime(rs.getString("meet"), rs.getString("start"), rs.getString("end"));
    			c.setInfo(info);
    			courses.add(c);
    			//courses.add(new Course(rs.getInt("courseID"), rs.getString("name"), rs.getString("prof"), rs.getDouble("units")));
    		}
    		rs.close();
    		stat.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		disconnect();
    	}
    	return courses;
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    private Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
