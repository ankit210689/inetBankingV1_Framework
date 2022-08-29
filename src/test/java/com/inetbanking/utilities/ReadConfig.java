package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;//pro object created to fetch data from config.properties file
	
	public ReadConfig()//Creating a constructor to load the file
	{
		
	File src=new File ("./ConfigurationFiles/config.properties");
	
	try {
		FileInputStream fis=new FileInputStream(src);
		pro=new Properties();
		pro.load(fis);//load is a method that will load complete config.properties file at run-time
	}
	catch (Exception e) //Exception defined in case the file doesn't load
	{ 
		System.out.println("Exception is "+ e.getMessage());
	}
	}

	//Now all the methods to fetch values from config.properties file will be defined here
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	public String getUsername()
	{
		String username=pro.getProperty("username");
		return username;
	}
	public String getPassword()
	{
		String password=pro.getProperty("password");
		return password;
	}
	public String getChromePath()
	{
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	public String getEdgePath()
	{
		String edgepath=pro.getProperty("edgepath");
		return edgepath;
	}
}
