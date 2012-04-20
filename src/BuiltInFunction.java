import java.sql.Connection;
import java.sql.DriverManager;

public class BuiltInFunction {

	//load method
	StlSetNode load(String connection_string, String userName, String password, String category){
		
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connection_string,userName,password);
			System.out.println("Connected to the database");
			conn.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

	//askQuestion method
	int askQuestion(StlSetNode s){

		return 0;
	}
	//readLine
	String readline(){
		return null;
	}
	//print
	void print(String s){
		System.out.println(s);
	}
	//printVar for int 
	void printVar(int x){
		System.out.println(x);
	}

	//printVar for char
	void printVar(char x){
		System.out.println(x);
	}

	//printVar for float
	void printVar(float x){
		System.out.println(x);
	}

	//printVar for set and questions
	void printVar(Object x){
		System.out.println(x);
	}
	//length of a set
	int len (StlSetNode s){

		return 0;
	}

}
