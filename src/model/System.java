package model;

import java.util.List;
import java.util.ArrayList;

public class System{

	private List<ZoneCoverage> listenerZones;

	private static System instance;
	private  String				name;

	private  List<User>			users     = new ArrayList<User>(); 
	private  List<Sample>		samples   = new ArrayList<Sample>(); 
	private  List<Location>		locations = new ArrayList<Location>();

	// Constructor
	protected System(String aName) {
		
		this.name = aName;
	}

	public static System getInstance(String aName) {
        if (instance == null) {
            instance = new System(aName);
        }
        return instance;
    }
   
	public String 				getName() 		{ return name; 		}
	public List<User> 			getUsers() 		{ return users; 	}
	public List<Sample> 		getSamples() 	{ return samples; 	}
	public List<Location> 		getLocations() 	{ return locations; }


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
	private boolean isIn(ZoneCoverage aZone) {
		// TODO Auto-generated method stub
		return this.listenerZones.stream()
				.anyMatch(zone -> zone == aZone); // TODO: implementar equals para ZoneCoverage
	}

	// Nota: No se usa interfaz ya que el sistema es único.
	public void Attach(ZoneCoverage aZone) {
		if (! this.isIn(aZone)) {
			this.listenerZones.add(aZone);
		}
	};
	public void Detach(ZoneCoverage aZone) {};
	public void Notify(Sample aSample) {
		this.listenerZones.stream().forEach(aZone -> aZone.Update(aSample));
	}

	// Quizás no se necesitan ya que se pasa el cambio por parámetro en el Notify.
	public void GetState() {};
	public void SetState() {};
	public void subjectState() {};

}
