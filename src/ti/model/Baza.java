package ti.model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

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
			e.getMessage();
		}

		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem polaczenia");
			e.getMessage();
		}

		createTables();
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamknieciem polaczenia");
			e.getMessage();
		}
	}

	public boolean createTables() {
		String createUsers = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, login varchar(255), password varchar(255), permissions int)";
		String createTests = "CREATE TABLE IF NOT EXISTS tests (" +
							 "questionId		INTEGER PRIMARY KEY AUTOINCREMENT," +
							 "testId	INTEGER," +
							 "quesrNum	INTEGER," +
							 "testName	VARCHAR (255)," +
							 "odData	DATETIME ," +
							 "doData	DATETIME ," +
							 "ileMin	INTEGER ," +
							 "question	VARCHAR (255)," +
							 "points	INTEGER," +
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
				"userId	INTEGER," +
				"startData	DATETIME," +
				"locked	BOOLEAN," +
				"started BOOLEAN" +
				");";

		String createAnswers = "CREATE TABLE IF NOT EXISTS answers (" +
				"questionId	INTEGER," +
				"userId	INTEGER," +
				"answer1	BOOLEAN (255)," +
				"answer2	BOOLEAN (255)," +
				"answer3	BOOLEAN (255)," +
				"answer4	BOOLEAN (255)" +
				");";

		try {
			stat.execute(createUsers);
			stat.execute(createTests);
			stat.execute(createUsersTests);
			stat.execute(createAnswers);
		} catch (SQLException e) {
			System.err.println("Blad przy tworzeniu tabeli");
			e.getMessage();
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
			System.err.println(e.getMessage());
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
			e.getMessage();
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
			e.getMessage();
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
			e.getMessage();
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
			System.err.println(e.getMessage());
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
			System.err.println(e.getMessage());
			return false;
		}
		return true;

	}

	public boolean addQuestion(String testName,String odData,String doData,String ileMin,  String[] questions, String points, String[] answer1, String[] answer2, String[] answer3, String[] answer4, String[] correct1, String[] correct2, String[] correct3, String[] correct4) {
		try {
			int testId = getLastNumber();
			if(testId<0)
				return false;
			testId++;
			int quesrNum=0;
			for(int i =0; i<questions.length;i++) {
				PreparedStatement prepStmt = conn.prepareStatement("insert into tests (questionId,testId,quesrNum,testName,odData,doData,ileMin,question,points,answer1,answer2,answer3,answer4,correct1,correct2,correct3,correct4) values (NULL,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				prepStmt.setInt(1, testId);
				prepStmt.setInt(2, quesrNum);
				prepStmt.setString(3, testName);
				prepStmt.setString(4, odData);
				prepStmt.setString(5, doData);
				prepStmt.setString(6, ileMin);
				prepStmt.setString(7, questions[i]);
				prepStmt.setString(8, points);
				prepStmt.setString(9, answer1[i]);
				prepStmt.setString(10, answer2[i]);
				prepStmt.setString(11, answer3[i]);
				prepStmt.setString(12, answer4[i]);
				prepStmt.setString(13, correct1[i]);
				prepStmt.setString(14, correct2[i]);
				prepStmt.setString(15, correct3[i]);
				prepStmt.setString(16, correct4[i]);
				prepStmt.execute();
				quesrNum++;
			}
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu testu");
			System.err.println(e.getMessage());
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
			e.getMessage();
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
			e.getMessage();
			return null;
		}
		return tests;
	}

	public List<Object[]> selectTests(int userId) {
		List<Object[]> tests = new LinkedList<Object[]>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'kk:mm");
		Random generator = new Random();
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT COUNT(tests.testId)AS questNum, tests.ileMin,tests.doData,tests.odData,tests.testId,testName FROM tests JOIN usersTests ON tests.testId=usersTests.testId WHERE userId = ? GROUP BY tests.testId;");

			prepStmt.setInt(1, userId);
			ResultSet result = prepStmt.executeQuery();
			while (result.next()) {
				int quesrId = generator.nextInt(result.getInt("questNum"));
				Date doData = ft.parse(result.getString("doData"));
				Date odData = ft.parse(result.getString("odData"));
				tests.add(new Object[]{result.getInt("testId"), result.getString("testName"),result.getInt("ileMin"),odData,doData,quesrId});
			}
		} catch (SQLException | ParseException e) {
			e.getMessage();
			return null;
		}
		return tests;
	}

	public List<Test> selectTests(int userId, int testId) {
		List<Test> tests = new LinkedList<Test>();
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM tests WHERE testId = ? ORDER BY questionId;");
			prepStmt.setInt(1, testId);
			ResultSet result = prepStmt.executeQuery();

			SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd'T'kk:mm");

			while (result.next()) {

				Date odData = ft.parse(result.getString("odData"));
				Date doData = ft.parse(result.getString("doData"));
				tests.add(new Test.TestBuilder()
						.withTestId(result.getInt("testId"))
						.withQuestId(result.getInt("questionId"))
						.withQuestNum(result.getInt("quesrNum"))
						.withTestName(result.getString("testName"))
						.withQuestion(result.getString("question"))
						.withPoints(result.getInt("points"))
						.withOdData(odData)
						.withDoData(doData)
						.withIleMin(result.getInt("ileMin"))
						.withAnswer1(result.getString("answer1"))
						.withAnswer2(result.getString("answer2"))
						.withAnswer3(result.getString("answer3"))
						.withAnswer4(result.getString("answer4"))

						.withCorrect1(Boolean.parseBoolean(result.getString("correct1")))
						.withCorrect2(Boolean.parseBoolean(result.getString("correct2")))
						.withCorrect3(Boolean.parseBoolean(result.getString("correct3")))
						.withCorrect4(Boolean.parseBoolean(result.getString("correct4")))
						.build());

			}
		} catch (SQLException | ParseException e) {
			e.getMessage();
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
			e.getMessage();
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
			e.getMessage();
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
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean addUserToTest(String[] userInTest,String testId) {
		try {//ToDo nie można zmieniać po dacie rozpoczęcia testu
			if(!deleteUsersTestsId(testId))
				throw new SQLException();
			if(userInTest!=null)
				for(int i =0; i<userInTest.length;i++) {
					PreparedStatement prepStmt = conn.prepareStatement("insert into usersTests (testId,userId,locked,started) values (?, ?,False ,False);");
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

//--------------------------------------------------------------------------------------
	public Test selectTest(int uesrId, String quesrNum, String testId) {
		Test test = null;
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM tests where quesrNum=? and testId=? ;");
			prepStmt.setString(1, quesrNum);
			prepStmt.setString(2, testId);

			ResultSet result = prepStmt.executeQuery();

			SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd'T'kk:mm");

			if(result.next()) {
				Date odData = ft.parse(result.getString("odData"));
				Date doData = ft.parse(result.getString("doData"));
				test = new Test.TestBuilder()
						.withTestId(result.getInt("testId"))
						.withQuestId(result.getInt("questionId"))
						.withQuestNum(result.getInt("quesrNum"))
						.withTestName(result.getString("testName"))
						.withQuestion(result.getString("question"))
						.withPoints(result.getInt("points"))
						.withOdData(odData)
						.withDoData(doData)
						.withIleMin(result.getInt("ileMin"))
						.withAnswer1(result.getString("answer1"))
						.withAnswer2(result.getString("answer2"))
						.withAnswer3(result.getString("answer3"))
						.withAnswer4(result.getString("answer4"))

						.withCorrect1(result.getBoolean("correct1"))
						.withCorrect2(result.getBoolean("correct2"))
						.withCorrect3(result.getBoolean("correct3"))
						.withCorrect4(result.getBoolean("correct4"))
						.build();
			}
		} catch (SQLException | ParseException e) {
			e.getMessage();
			return null;
		}
		return test;
	}

	public boolean addAnswers(Answers answers) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("insert into answers (questionId,userId,answer1,answer2,answer3,answer4) values (?, ?, ?, ?, ?, ?);");
			prepStmt.setInt(1, answers.getQuestionId());
			prepStmt.setInt(2, answers.getUserId());
			prepStmt.setBoolean(3, answers.getAnswer1());
			prepStmt.setBoolean(4, answers.getAnswer2());
			prepStmt.setBoolean(5, answers.getAnswer3());
			prepStmt.setBoolean(6, answers.getAnswer4());
			
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy dodawaniu odpowiedzi");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean isPermission(String testId, int userId) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM usersTests where testId=? and userId=? ;");
			prepStmt.setString(1, testId);
			prepStmt.setInt(2, userId);
			ResultSet result = prepStmt.executeQuery();




			if (result.next()) {
				Boolean locked;
				Date dateStop = null;
				if (result.getBoolean("started")) {
					Test test = selectTest(userId, "0", testId);
					SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm");

					String startDataString = result.getString("startData");
					dateStop = ft.parse(startDataString);
					dateStop.setTime(dateStop.getTime()+(test.getIleMin()*60000));

					locked = result.getBoolean("locked");
					if (!locked && dateStop.after(new Date()))
						return true;
					else {
						lockTest(testId, userId);
						return false;
					}
				}else{
					return true;
				}

			} else
				return false;
		} catch (SQLException | ParseException e) {
			e.getMessage();
			return false;
		}
	}

	public boolean lockTest(String testId, int userId) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("UPDATE usersTests SET locked = True where testId=? and userId=?;");
			prepStmt.setString(1, testId);
			prepStmt.setInt(2, userId);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;

	}

	public boolean setStartDate(String testId, int userId) {
		Date dNow = new Date( );
		SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd'T'kk:mm");
		String data= ft.format(dNow);
		try {
			PreparedStatement prepStmt = conn.prepareStatement("UPDATE usersTests SET startData = ?,started=True where testId=? and userId=?;");
			prepStmt.setString(1, data);
			prepStmt.setString(2, testId);
			prepStmt.setInt(3, userId);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public List<Answers> selectAnswers(int userId, int testId) {
		List<Answers> tests = new LinkedList<Answers>();
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM answers WHERE userId = ? and questionId IN( SELECT questionId FROM tests WHERE testId = ?) ORDER BY questionId");
			prepStmt.setInt(1, userId);
			prepStmt.setInt(2, testId);
			ResultSet result = prepStmt.executeQuery();
			while (result.next()) {
				tests.add(new Answers.AnswersBuilder()
						.userId(result.getInt("userId"))
						.questionId(result.getInt("questionId"))
						.answer1(Boolean.parseBoolean(result.getString("answer1")))
						.answer2(Boolean.parseBoolean(result.getString("answer2")))
						.answer3(Boolean.parseBoolean(result.getString("answer3")))
						.answer4(Boolean.parseBoolean(result.getString("answer4")))
						.build());
			}
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
		return tests;
	}

	public boolean isStarted(String testId, int userId) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM usersTests where testId=? and userId=? ;");
			prepStmt.setString(1, testId);
			prepStmt.setInt(2, userId);
			ResultSet result = prepStmt.executeQuery();

			if (result.next()) {
				return result.getBoolean("started");
			} else
				return false;
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
	}
}