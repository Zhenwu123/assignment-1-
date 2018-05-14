package utlility;

import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Adult;
import entity.Child;
import entity.Profile;

/**
 * This class is a Database Utility class which defines methods to initiate data in 
 * the database, check whether the database exist, read data from database, save and
 *  delete new object to/from database.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

public class DatabaseUtility {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
				
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		
		return connection;
	}
	
	public static void initProfileDB() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		
		//connection.prepareStatement("drop table profiles if exists;").execute();
		connection.prepareStatement("create table profiles(name varchar(20) not null, image varchar(20), "
				+ "status varchar(20), age integer);").execute();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('Alex Smith', '', 'student at RMIT', 21);").execute();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('Ben Turner', 'images/BenPhoto.png', '¡°manager at Coles', 35);").execute();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('Hannah White', 'images/Hannah.png', 'student at PLC', 14);").execute();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('Zoe Foster', '', 'Founder of ZFX', 28);").execute();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('Mark Turner', 'images/Mark.png', '', 2);").execute();
		connection.commit();
		connection.close();
	}
	
	public static boolean checkDBexisted() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, "PROFILES", null);
		rs.next();
		if(rs.getString(3) != null) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Profile> getProfileFromDB() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		ResultSet rs = connection.prepareStatement("select name, image, status, age from profiles;").executeQuery();
		while(rs.next()) {
			if(rs.getInt(4) <= 16) {
				profiles.add(new Child(rs.getString(1), Integer.valueOf(rs.getString(4)), rs.getString(3), rs.getString(2)));
			}else {
				profiles.add(new Adult(rs.getString(1), Integer.valueOf(rs.getString(4)), rs.getString(3), rs.getString(2)));
			}
		}
		connection.close();
		rs.close();
		return profiles;
	}
	
	public static void addProfileIntoDB(Profile profile) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		connection.prepareStatement("insert into profiles(name, image, status, age) values ('" + 
		profile.getName() + "', '','" + profile.getStatus() + "'," + profile.getAge()+");").execute();
		connection.close();
	}
	
	public static void deleteProfileFromDB(Profile profile) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		connection.prepareStatement("delete from profiles where name = '" + profile.getName() + "');").execute();
		connection.close();
	}
}
