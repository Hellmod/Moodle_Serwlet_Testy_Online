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

	public boolean createTables() {
		String createUsers = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, login varchar(255), password varchar(255), permissions int)";
		try {
			stat.execute(createUsers);
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
		List<User> urzytkownicy = new LinkedList<User>();
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM users ;");
			ResultSet result = prepStmt.executeQuery();
			while (result.next()) {
				urzytkownicy.add(new User(result.getInt("id"), result.getString("login"), result.getString("password"), result.getInt("permissions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return urzytkownicy;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamknieciem polaczenia");
			e.printStackTrace();
		}
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
}