package model;

import java.util.List;
import java.util.ArrayList;

public class SystemSingleton{

	private static SystemSingleton instance;
	private  String				name;

	private  List<String>		users     = new ArrayList<String>(); 
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
	public List<String> 	getUsers() 		{ return users; 	}
	public List<Sample> 	getSamples() 	{ return samples; 	}
	public List<Location> 	getLocations() 	{ return locations; }

	public void addToList(Sample aSample) {// Cómo saber qué elem es para operar sobre su respectiva lista?
		// TODO Auto-generated method stub
		if (! this.isInSamples(aSample)) {
			this.getSamples().add(aSample);
		}
	}
	private boolean isInSamples(Sample aSample) {
		// TODO Auto-generated method stub
		return this.getSamples().stream()
				.anyMatch(sample -> sample == aSample); // TODO: implementar equals para Samples
	}

	public void addToList(Location aLocation) {
		// TODO Auto-generated method stub
		
	}
}
