package vehicleGUI;

public class DatabaseTable {

	public final int N = 10;
	
	public String fieldsNames[];
	
	public String name; // nom de la table
	
	public DatabaseTable(String name)
	{
		this.name = name;
				
		fieldsNames = new String[N];
	}
	
	public DatabaseTable setFieldValue(int i, String value)
	{
		if( !(i >= 0 && i < N) ) return this;
		
		fieldsNames[i] = value;	
		return this;
	}
}
