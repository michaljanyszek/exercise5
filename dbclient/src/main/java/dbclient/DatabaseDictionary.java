package dbclient;

public enum DatabaseDictionary {
	
	DROP_TABLE_STUDENT("DROP TABLE Student IF EXISTS CASCADE"),
	DROP_TABLE_FACULTY("DROP TABLE Faculty IF EXISTS CASCADE"),
	DROP_TABLE_CLASS ("DROP TABLE Class IF EXISTS CASCADE"),
	DROP_TABLE_ENROLLMENT ("DROP TABLE Enrollment IF EXISTS CASCADE"),
	
	CREATE_TABLE_STUDENT(
			"CREATE TABLE Student( " +
            "pkey INT NOT NULL," +
            "name VARCHAR(50) NOT NULL," +
            "sex VARCHAR(6) NOT NULL," +
            "age INT NOT NULL," +
            "level INT NOT NULL," +
            "PRIMARY KEY (pkey) );"),
	
	CREATE_TABLE_FACULTY (
			"CREATE TABLE Faculty( " +
            "pkey INT NOT NULL," +
            "name VARCHAR(50) NOT NULL," +
        	"PRIMARY KEY (pkey) );"),
	
	CREATE_TABLE_CLASS("CREATE TABLE Class( " +
            "pkey INT NOT NULL," +
            "name VARCHAR(50) NOT NULL," +
            "fkey_faculty INT NOT NULL," +
            "PRIMARY KEY (pkey),"+
            "FOREIGN KEY (fkey_faculty) REFERENCES Faculty (pkey));"),
	
	CREATE_TABLE_ENROLLMENT("CREATE TABLE Enrollment( " +
            "fkey_student INT NOT NULL," +
            "fkey_class INT NOT NULL," +
            "FOREIGN KEY (fkey_class) REFERENCES Class (pkey),"+
            "FOREIGN KEY (fkey_student) REFERENCES Student (pkey));"),
	
	//INSERTING DATA TO STUDENT
	
	STUDENT_INSERT_VAL1("INSERT INTO Student "+
            "VALUES (1, 'John Smith', 'male', 23, 2);"),
	
	STUDENT_INSERT_VAL2("INSERT INTO Student "+
            "VALUES (2, 'Rebecca Milson', 'female', 27, 3);"),
	STUDENT_INSERT_VAL3("INSERT INTO Student "+
            "VALUES (3, 'George Heartbreaker', 'male', 19, 1);"),
	 STUDENT_INSERT_VAL4 ( 
            "INSERT INTO Student "+
            "VALUES (4, 'Deepika Chopra', 'female', 25, 3);"),
	
	//INSERTING DATA TO FACULTY
	 FACULTY_INSERT_VAL1 ( 
            "INSERT INTO Faculty "+
            "VALUES (100, 'Engineering');"),
	 FACULTY_INSERT_VAL2 ( 
            "INSERT INTO Faculty "+
            "VALUES (101, 'Philosophy');"),
	 FACULTY_INSERT_VAL3 ( 
            "INSERT INTO Faculty "+
            "VALUES (102, 'Law and administration');"),
	 FACULTY_INSERT_VAL4 ( 
            "INSERT INTO Faculty "+
            "VALUES (103, 'Languages');"),
	
	//INSERTING DATA TO CLASS
	 CLASS_INSERT_VAL1 ( 
			"INSERT INTO Class "+
            "VALUES (1000, 'Introduction to labour law', 102);"),
	 CLASS_INSERT_VAL2 ( 
            "INSERT INTO Class "+
            "VALUES (1001, 'Graph algorithms', 100);"),
	 CLASS_INSERT_VAL3 ( 
            "INSERT INTO Class "+
            "VALUES (1002, 'Existentialism in 20th century', 101);"),
	 CLASS_INSERT_VAL4 (
            "INSERT INTO Class "+
            "VALUES (1003, 'English grammar', 103);"),
	 CLASS_INSERT_VAL5 ( 
            "INSERT INTO Class "+
            "VALUES (1004, 'From Plato to Kant', 101);"),
	
	//INSERTING DATA TO ENROLLMENT
	 ENROLLMENT_ISERT_VAL1 ( 
            "INSERT INTO Enrollment "+
            "VALUES (1, 1000);"),
	 ENROLLMENT_INSERT_VAL2 (
            "INSERT INTO Enrollment "+
            "VALUES (1, 1002);"),
	 ENROLLMENT_INSERT_VAL3 (
            "INSERT INTO Enrollment "+
            "VALUES (1, 1003);"),
	 ENROLLMENT_INSERT_VAL4 ( 
            "INSERT INTO Enrollment "+
            "VALUES (1, 1004);"),
	 ENROLLMENT_INSERT_VAL5 ( 
            "INSERT INTO Enrollment "+
            "VALUES (2, 1002);"),
	 ENROLLMENT_INSERT_VAL6 ( 
            "INSERT INTO Enrollment "+
            "VALUES (2, 1003);"),
	 ENROLLMENT_INSERT_VAL7 ( 
            "INSERT INTO Enrollment "+
            "VALUES (4, 1000);"),
	 ENROLLMENT_INSERT_VAL8 ( 
            "INSERT INTO Enrollment "+
            "VALUES (4, 1002);"),
	 ENROLLMENT_INSERT_VAL9 (
			"INSERT INTO Enrollment "+
            "VALUES (4, 1003);"),
	
	//SELECTING QUERIES
	
	 SELECT_QUERY1 ( 
			 "SELECT pkey, name FROM Student"),
	 SELECT_QUERY2 ( 
			 "SELECT s.pkey, s.name "
					 			 + "FROM Student s LEFT JOIN Enrollment e ON (s.pkey=e.fkey_student) "
					 			 + "WHERE e.fkey_student IS NULL"),
	 SELECT_QUERY3 ( 
			  "SELECT s.pkey, s.name "
	    	  + "FROM Student s LEFT JOIN Enrollment e ON (s.pkey=e.fkey_student) "
	    	  + "JOIN Class c ON (e.fkey_class=c.pkey)"
	    	  + "WHERE s.sex='female' AND c.name='Existentialism in 20th century'"),
	 SELECT_QUERY4 ( 
    		"SELECT f.name "
	    	  + "FROM Student s FULL JOIN Enrollment e ON (s.pkey=e.fkey_student) "
	    	  + "FULL JOIN Class c ON (e.fkey_class=c.pkey)"
	    	  + " FULL JOIN Faculty f ON (c.fkey_faculty=f.pkey)"
	    	  + "WHERE c.pkey NOT IN (SELECT fkey_class FROM Enrollment)"),
	 SELECT_QUERY5 ( 
    		  "SELECT MAX(s.age) maxAge "
	    	  + "FROM Student s FULL JOIN Enrollment e ON (s.pkey=e.fkey_student) "
	    	  + "FULL JOIN Class c ON (e.fkey_class=c.pkey)"
	    	  + "WHERE c.name='Introduction to labour law'"),
	 SELECT_QUERY6 ( 
			  "SELECT c.name, COUNT(e.fkey_student) countFk "
	    	  + "FROM Enrollment e  "
	    	  + "FULL JOIN Class c ON (e.fkey_class=c.pkey)"
	    	  + "GROUP BY c.name "
	    	  + "HAVING COUNT(e.fkey_student)>=2  "),
	 SELECT_QUERY7 (
			  "SELECT s.level, AVG(s.age) av "
			  + "FROM Student s "
		      + "GROUP BY s.level ");
	
	 private String query;
	 
	 private DatabaseDictionary(String query) {
	        this.query = query;
	    }

	    public String getQuery() {
	        return query;
	    }
}
