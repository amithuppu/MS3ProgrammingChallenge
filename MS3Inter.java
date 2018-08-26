import java.io.*;
import java.sql.*;
import java.util.*;

class MS3Inter
{
	public static void main(String args[]) throws SQLException, IOException
	{
		String dbname = "MS3";
		String row = "";
		int totalRecords =0;
		int failedRecords=0;
		int successRecords=0;
		String[] values = null;
		Connection conn = null;
		BufferedReader br = null;
		FileWriter writer =null;
		FileWriter logwriter =null;
		PreparedStatement stmt =null;
		try
		{
			conn = DriverManager.getConnection("jdbc:sqlite:C://sqlitestudio-3.1.1/SQLiteStudio/MS3.db");
			String sql = "create table IF NOT EXISTS MS3(  A varchar(50), B varchar(50), C varchar(50), D varchar950, E blob, F varchar(100),G varchar(100), H varchar(50), I varchar(100), J varchar(100) );";
			Statement st = conn.createStatement();		
			st.executeUpdate(sql);
			st.close();
			 br = new BufferedReader(new FileReader("C:\\MyProjects\\ms3interview.csv"));
		    writer = new FileWriter("C:\\MyProjects\\bad-data-"+new java.util.Date().getTime()+".csv");	        
	        logwriter = new FileWriter("C:\\MyProjects\\Successlog.txt");
	        br.readLine();
			while((row = br.readLine())!=null && row.trim().length()!=0)
			{
				values = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				totalRecords++;
				
				
			
			 stmt = conn.prepareStatement("Insert into MS3(A,B,C,D,E,F,G,H,I,J) values (?,?,?,?,?,?,?,?,?,?)");
		  
			 if(values.length!=10)
		   {
			   failedRecords++;
			   writer.append(row);
			   writer.append(System.lineSeparator());
		   }
		   else
		   {
			   successRecords++;
			   for(int i=0;i<10;i++)
			   {
				   stmt.setString(i+1, values[i]);
			   }
			   stmt.executeUpdate();
		   }
			
			
	    } 
		logwriter.append("Statistics log executed at "+ new java.util.Date().getTime());
		logwriter.append(System.lineSeparator());
		logwriter.append(totalRecords+ "is the # of records received\n");
		logwriter.append(System.lineSeparator());
		logwriter.append(successRecords+ "is the # of records Successful\n");
		logwriter.append(System.lineSeparator());
		logwriter.append(failedRecords+ "is the # of records failed\n");
		logwriter.append(System.lineSeparator());	
		}
		catch(Exception e)
		{
			System.out.println(e);	
		}
		finally
		{
			    stmt.close();
				conn.close();
				logwriter.flush();
				logwriter.close();
				writer.flush();
		        writer.close();
	    }
     }
}