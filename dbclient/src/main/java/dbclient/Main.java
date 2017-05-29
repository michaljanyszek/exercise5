package dbclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.*;



public class Main {
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;  
	
	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		try {
			connection = DriverManager.getConnection(
			        "jdbc:hsqldb:hsql://127.0.0.1:9001/test-db",
			        "SA",
			        "");
			
			//STUDENTS
			
			statement = connection.prepareStatement(DatabaseDictionary.DROP_TABLE_STUDENT.getQuery()); 
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CREATE_TABLE_STUDENT.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.STUDENT_INSERT_VAL1.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.STUDENT_INSERT_VAL2.getQuery()
                    );
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.STUDENT_INSERT_VAL3.getQuery()
                    );
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.STUDENT_INSERT_VAL4.getQuery());
			statement.execute();
			
			//FACULTY
			  
			statement = connection.prepareStatement(DatabaseDictionary.DROP_TABLE_FACULTY.getQuery()); 
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CREATE_TABLE_FACULTY.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.FACULTY_INSERT_VAL1.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.FACULTY_INSERT_VAL2.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.FACULTY_INSERT_VAL3.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.FACULTY_INSERT_VAL4.getQuery());
			statement.execute();		
			
			//CLASS
	
			statement = connection.prepareStatement(DatabaseDictionary.DROP_TABLE_CLASS.getQuery()); 
			statement.execute();		
			statement = connection.prepareStatement(DatabaseDictionary.CREATE_TABLE_CLASS.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CLASS_INSERT_VAL1.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CLASS_INSERT_VAL2.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CLASS_INSERT_VAL3.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CLASS_INSERT_VAL4.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CLASS_INSERT_VAL5.getQuery());
			statement.execute();

			//ENROLLMENT
			
			statement = connection.prepareStatement(DatabaseDictionary.DROP_TABLE_ENROLLMENT.getQuery()); 
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.CREATE_TABLE_ENROLLMENT.getQuery());			
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_ISERT_VAL1.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL2.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL3.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL4.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL5.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL6.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL7.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL8.getQuery());
			statement.execute();
			statement = connection.prepareStatement(DatabaseDictionary.ENROLLMENT_INSERT_VAL9.getQuery());
			statement.execute();	
			
			//SELECTING QUERIES 
			
				statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY1.getQuery());
			    ResultSet resultSet = statement.executeQuery();
			    String result="\nFIRST_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {
			        int x = resultSet.getInt("pkey");
			        String s = resultSet.getString("name");
			        s = s.split(" ")[1];
			        
			        result += "pkey = "+x+ " "+"name = "+s+" "+"\n"; 
			    }
			    log.info("First query result");
			    log.info(result);
			    
			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY2.getQuery());
			    resultSet = statement.executeQuery();
			    result="\n\nSECOND_QUERY_RESULT:\n";
			    while (resultSet.next()) {
			        int x = resultSet.getInt("pkey");
			        String s = resultSet.getString("name");
			        s = s.split(" ")[1];
			        result += "pkey = "+x+ " "+"name = "+s+" "+"\n";			        
			    }		
			    log.info("Second query result");
			    log.info(result);
			    
			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY3.getQuery());
			    resultSet = statement.executeQuery();
			    result="\nTHIRD_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {
			        int x = resultSet.getInt("pkey");
			        String s = resultSet.getString("name");
			        s = s.split(" ")[1];
			        result += "pkey="+x+ " "+"name="+s+" "+"\n";
			    }
			    log.info("Third query result");
			    log.info(result);
			    
			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY4.getQuery());			    
			    resultSet = statement.executeQuery();			    
			    result="\nFOURTH_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {			        
			        String s = resultSet.getString("name");            
			        result += "name="+s+" "+"\n";			        
			    }
			    log.info("Fourth query result");
			    log.info(result);
			    
			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY5.getQuery());			    
			    resultSet = statement.executeQuery();			    
			    result="\nFIFTH_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {			        
			        int s = resultSet.getInt("maxAge");			        
			        result += "max_age="+s+" "+"\n";			        
			    }
			    
			    log.info(result);

			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY6.getQuery());
			    resultSet = statement.executeQuery();			    
			    result="\nSIXTH_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {			        
			        String s = resultSet.getString("name");			        
			        result += "name="+s+" "+"\n";			        
			    }
			    log.info("Sixth query result");
			    log.info(result);
			    
			    statement = connection.prepareStatement(DatabaseDictionary.SELECT_QUERY7.getQuery());
			    resultSet = statement.executeQuery();
			    result="\nSEVENTH_QUERY_SELECT_RESULT :\n";
			    while (resultSet.next()) {			        
			        int s = resultSet.getInt("level");
			        double a = resultSet.getDouble("av");
			        result += "level="+s+" "+"avg="+a+"\n";			        
			    }
			    log.info("Seventh query result");
			    log.info(result);
			    	
		} catch (SQLException e) {

			e.printStackTrace();
		}
		

	}

}
