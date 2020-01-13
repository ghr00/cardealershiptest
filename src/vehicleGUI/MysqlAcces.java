package vehicleGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlAcces {

	public String hostname;
	
	public String port;
	
	public String user;
	
	public String password;
	
	public String database;
	
	public Connection connection;
	
	public MysqlAcces()
	{
		this.database = "vehiclesdb";
	}
	
	public MysqlAcces(String hostname, String port, String user, String password)
	{
		this.database = "vehiclesdb";
		
		this.configure(hostname, port, user, password);
	}
	
	public void configure(String hostname, String port, String user, String password)
	{
		this.hostname = hostname;
		this.port = port;
		this.user = user;
		this.password = password;
		
	}
	
	public String getURL()
	{
		return "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"; 
	}
	
	public void connect()
	{
		try
		{
			connection = DriverManager.getConnection(this.getURL(), this.user, this.password);
		} 
		
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
