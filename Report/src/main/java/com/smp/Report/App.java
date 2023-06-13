package com.smp.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smp.dao.DBMS;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class App 
{
	public static void main( String[] args ) throws ClassNotFoundException
	{
		Student st=new Student();
		ArrayList<Student>Studlist=new ArrayList<Student>();
		st.setID(11);
		st.setFIRSTNAME("smp");
		st.setLASTNAME("Pawar");
		st.setCITY("Ankoli");
		DBMS d=new DBMS();
		d.saveStudent(st);
		Studlist=d.getStudent();
		for(int i=0;i<Studlist.size();i++)
		{
			Student s=Studlist.get(i);
			//System.out.println(st.toString());
			System.out.println("Student detail="+s.getID()+" "+s.getFIRSTNAME()+" "+s.getLASTNAME()+""+s.getCITY());
		}

		try 
		{
			String filepath="C:\\Users\\Samadhan\\"
					+ "eclipse-workspace1\\Report\\src\\main\\java//Smp.jrxml";
			Map<String,Object>parameters=new HashMap<String,Object>();
			parameters.put("StudentName", "Suvarna");


			JRBeanCollectionDataSource datasource=
					new JRBeanCollectionDataSource(Studlist);
			JasperReport report= JasperCompileManager.compileReport(filepath);
			JasperPrint print= JasperFillManager.fillReport(report, parameters, datasource);
			JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\Samadhan\\eclipse-workspace1\\Report\\src\\main\\java//Smp.pdf");	

			System.out.println("create"); 
		}
		catch(Exception e)
		{
			System.out.println("Exception while creating Report");
		}
	}
}
