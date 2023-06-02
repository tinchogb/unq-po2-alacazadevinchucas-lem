package model;

import java.util.List;
import java.util.ArrayList;

public class SystemSingleton{

	private static SystemSingleton instance;
	private  String				name;

	private  List<User>			users     = new ArrayList<User>(); 
	private  List<Sample>		samples   = new ArrayList<Sample>(); 
	private  List<Location>		locations = new ArrayList<Location>();

	// Constructor
	protected SystemSingleton(String aName) {
		
		this.name = aName;
	}

	public static SystemSingleton getInstance(String aName) {
        if (instance == null) {
            instance = new SystemSingleton(aName);
        }
        return instance;
    }
   
	public String 			getName() 		{ return name; 		}
	public List<User> 		getUsers() 		{ return users; 	}
	public List<Sample> 	getSamples() 	{ return samples; 	}
	public List<Location> 	getLocations() 	{ return locations; }


	public void add(User aUser) {
		// TODO Auto-generated method stub
		if (! this.isIn(aUser)) {
			this.getUsers().add(aUser);
		}
	}
	public void add(Sample aSample) {
		// TODO Auto-generated method stub
		if (! this.isIn(aSample)) {
			this.getSamples().add(aSample);
		}
	}
	public void add(Location aLocation) {
		// TODO Auto-generated method stub
		if (! this.isIn(aLocation)) {
			this.getLocations().add(aLocation);
		}
	}

	private boolean isIn(User aUser) {
		// TODO Auto-generated method stub
		return this.getUsers().stream()
				.anyMatch(user -> user == aUser); // TODO: implementar equals para User
	}
	private boolean isIn(Sample aSample) {
		// TODO Auto-generated method stub
		return this.getSamples().stream()
				.anyMatch(sample -> sample == aSample); // TODO: implementar equals para Samples
	}
	private boolean isIn(Location aLocation) {
		// TODO Auto-generated method stub
		return this.getLocations().stream()
				.anyMatch(location -> location == aLocation); // TODO: implementar equals para Location
	}

}
