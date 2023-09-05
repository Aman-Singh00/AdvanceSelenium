package com.PractiseDataDrivenTesting;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutlity.DataBaseUtility;

public class demo{
	
	
	@Test
	public void testing() throws Throwable

	{
		DataBaseUtility utility = new DataBaseUtility();
		utility.getDbconnection("jdbc:mysql://localhost:3306/TYSS", "root", "root");
		
		ResultSet res=utility.executeSelectQuery("Select * from customer;");
		
		while(res.next()) {
			System.out.println(res.getString(1)+"\t"+res.getString(2)+"\t"+res.getString(3)+"\t"+res.getString(4)+"\t"+res.getString(5));

			
		}
		
		utility.closeDbconnection();
		
	}
}
