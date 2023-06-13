package com.smp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.cj.protocol.Resultset;
import com.smp.Report.Student;

public class DBMS 
{
	String driverClassName="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/smp";
	String username="root";
	String password="root";

	public void saveStudent(Student stud) 
	{


		String query="insert into StudentAdd values('"+stud.getID()+"','"+stud.getFIRSTNAME()+"','"+stud.getLASTNAME()+"','"+stud.getCITY()+"')";
		try 
		{
			Class.forName(driverClassName);
			Connection con=DriverManager.getConnection(url, username, password);
			Statement st1=con.createStatement();
			int num=st1.executeUpdate(query);
			System.out.println("Number of rows="+num);
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}

	}

	public ArrayList<Student> getStudent() throws ClassNotFoundException 
	{
		ArrayList<Student> slist=new ArrayList<Student>();


		String query="select * from StudentAdd";

		try {
			Class.forName(driverClassName);
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st=con.createStatement();

			ResultSet rs=st.executeQuery(query);

			while(rs.next())
			{
				Student s=new Student();
				int roll=rs.getInt(1);
				s.setID(roll);
				String name=rs.getString(2);
				s.setFIRSTNAME(name);
				String lastname=rs.getString(3);
				s.setLASTNAME(lastname);
				String City=rs.getString(4);
				s.setCITY(City);

				slist.add(s);
			}
			con.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return slist;
	}
}

