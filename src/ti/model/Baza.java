package ti.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Baza {

	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:biblioteka.db";

	private Connection conn;
	private Statement stat;

	public ArrayList<Object[]> baza=new ArrayList<>();

	public Baza() {
		try {
			Class.forName(Baza.DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem polaczenia");
			e.printStackTrace();
		}

		createTables();
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamknieciem polaczenia");
			e.printStackTrace();
		}
	}

	public boolean createTables() {
		String createUsers = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, login varchar(255), password varchar(255), permissions int)";
		String createTests = "CREATE TABLE IF NOT EXISTS tests (" +
							 "id		INTEGER PRIMARY KEY AUTOINCREMENT," +
							 "testId	INTEGER," +
							 "testName	VARCHAR (255)," +
							 "question	VARCHAR (255)," +
							 "points	VARCHAR (255)," +
							 "answer1	VARCHAR (255)," +
							 "answer2	VARCHAR (255)," +
							 "answer3	VARCHAR (255)," +
							 "answer4	VARCHAR (255)," +
							 "correct1	BOOLEAN (255)," +
							 "correct2	BOOLEAN (255)," +
							 "correct3	BOOLEAN (255)," +
							 "correct4	BOOLEAN (255)" +
							 ");";
		String createUsersTests = "CREATE TABLE IF NOT EXISTS usersTests (" +
				"testId	INTEGER," +
				"userId	INTEGER" +
				");";

		try {
			stat.execute(createUsers);
			stat.execute(createTests);
			stat.execute(createUsersTests);
		} catch (SQLException e) {
			System.err.println("Blad przy tworzeniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertUser(User user) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("insert into users values (NULL, ?, ?, ?);");
			prepStmt.setString(1, user.getLogin());
			prepStmt.setString(2, user.getPassword());
			prepStmt.setInt(3, user.getPermissions());
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu użytkownika");
			System.err.println(e.getErrorCode());
			return false;
		}
		return true;
	}

	public User selectUser(String login, String password) {
		User user=null;
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM users where login=? and password=? ;");
			prepStmt.setString(1, login);
			prepStmt.setString(2, password);

			ResultSet result = prepStmt.executeQuery();
			if(result.next())
				user = new User(result.getInt("id"), result.getString("login"), result.getString("password"), result.getInt("permissions"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public List<User> selectUsers() {
		List<User> users = new LinkedList<User>();
		try {
			ResultSet result = stat.executeQuery("SELECT * FROM users");
			while (result.next()) {
				users.add(new User(result.getInt("id"), result.getString("login"), result.getString("password"), result.getInt("permissions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}

    public boolean loginAvailable(String login) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM users where login=?");
			prepStmt.setString(1, login);
			ResultSet result = prepStmt.executeQuery();
			if(result.next())
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
    }

	public boolean setPermissions(int id, int permissions) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("UPDATE users SET permissions = ? WHERE id = ?;");
			prepStmt.setInt(1, permissions);
			prepStmt.setInt(2, id);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy aktualizacji użytkownika");
			System.err.println(e.getErrorCode());
			return false;
		}
		return true;
	}

	public boolean deleteUser(int id) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM users  WHERE id = ?;");
			prepStmt.setInt(1, id);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy usuwaniu użytkownika");
			System.err.println(e.getErrorCode());
			return false;
		}
		return true;

	}

	public boolean addQuestion(String testName, String[] questions, String[] points, String[] answer1, String[] answer2, String[] answer3, String[] answer4, String[] correct1, String[] correct2, String[] correct3, String[] correct4) {
		try {
			int testId = getLastNumber();
			if(testId<0)
				return false;
			testId++;
			for(int i =0; i<points.length;i++) {
				PreparedStatement prepStmt = conn.prepareStatement("insert into tests (id,testId,testName,question,points,answer1,answer2,answer3,answer4,correct1,correct2,correct3,correct4) values (NULL,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				prepStmt.setInt(1, testId);
				prepStmt.setString(2, testName);
				prepStmt.setString(3, questions[i]);
				prepStmt.setString(4, points[i]);
				prepStmt.setString(5, answer1[i]);
				prepStmt.setString(6, answer2[i]);
				prepStmt.setString(7, answer3[i]);
				prepStmt.setString(8, answer4[i]);
				prepStmt.setString(9, correct1[i]);
				prepStmt.setString(10, correct2[i]);
				prepStmt.setString(11, correct3[i]);
				prepStmt.setString(12, correct4[i]);
				prepStmt.execute();
			}
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu użytkownika");
			System.err.println(e.getErrorCode());
			return false;
		}
		return true;
	}

	private int getLastNumber() {
		User user=null;
		try {
			PreparedStatement prepStmt = conn.prepareStatement("select max(testId) as testId  from tests ;");
			ResultSet result = prepStmt.executeQuery();
			if(result.next())
				return result.getInt("testId");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

		return -1;
	}

	public List<String[]> selectTests() {
		List<String[]> tests = new LinkedList<String[]>();
		try {
			ResultSet result = stat.executeQuery("SELECT DISTINCT testId,testName FROM tests");
			while (result.next()) {
				tests.add(new String[]{result.getString("testId"), result.getString("testName")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tests;
	}

	public String selectTestName(String testId) {

		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT DISTINCT testName FROM tests where testId=?;");
			prepStmt.setString(1, testId);
			ResultSet result = prepStmt.executeQuery();
			if(result.next())
				return result.getString("testName");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return "Błąd odczytu";

	}

	public List<String> selectUsersTestsId(String testId) {
		List<String> tests = new LinkedList<String>();
		try {

			PreparedStatement prepStmt = conn.prepareStatement("SELECT userId FROM usersTests WHERE testId = ?");
			prepStmt.setString(1, testId);

			ResultSet result = prepStmt.executeQuery();
			while (result.next()) {
				tests.add(result.getString("userId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tests;
	}

	public boolean deleteUsersTestsId(String testId) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM usersTests  WHERE testId = ?;");
			prepStmt.setString(1, testId);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy usuwaniu użytkownika");
			System.err.println(e.getErrorCode());
			return false;
		}
		return true;
	}

	public boolean addUserToTest(String[] userInTest,String testId) {
		try {
			if(!deleteUsersTestsId(testId))
				throw new SQLException();
			for(int i =0; i<userInTest.length;i++) {
				PreparedStatement prepStmt = conn.prepareStatement("insert into usersTests (testId,userId) values (?, ?);");
				prepStmt.setString(1, testId);
				prepStmt.setString(2, userInTest[i]);
				prepStmt.execute();
			}
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu użytkownika");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
}