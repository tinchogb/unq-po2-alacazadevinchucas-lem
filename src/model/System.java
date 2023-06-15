package model;

import java.util.List;
import java.util.ArrayList;

public class System {

	private List<ZoneCoverage> listenerZones = new ArrayList<ZoneCoverage>();

	private static System instance;


	private  List<User>			users     = new ArrayList<User>(); 
	private  List<Sample>		samples   = new ArrayList<Sample>(); 
	private  List<Location>		locations = new ArrayList<Location>();
	private  List<ZoneCoverage>	zones 	  = new ArrayList<ZoneCoverage>();

	// Constructor
	protected System() {}

	// Constructor for test
	protected System(List<User> users) {
		this.users = users;
	}

	protected System(List<ZoneCoverage> listeners,int num) {
		this.listenerZones = listeners;
	}

	public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }

	public List<User> 			getUsers() 		{ return users; 	}
	public List<Sample> 		getSamples() 	{ return samples; 	}
	public List<Location> 		getLocations() 	{ return locations; }
	public List<ZoneCoverage> 	getZones() 		{ return zones; }


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
	public void add(ZoneCoverage aZone) {
		// TODO Auto-generated method stub
		if (! this.isIn(aZone)) {
			this.getZones().add(aZone);
		}
	}

	private boolean isIn(User aUser) {
		return this.getUsers().stream()
				.anyMatch(user -> user == aUser); // TODO: implementar equals para User
	}
	private boolean isIn(Sample aSample) {
		return this.getSamples().stream()
				.anyMatch(sample -> sample == aSample); // TODO: implementar equals para Samples
	}
	private boolean isIn(Location aLocation) {
		return this.getLocations().stream()
				.anyMatch(location -> location == aLocation); // TODO: implementar equals para Location
	}
	private boolean isIn(ZoneCoverage aZone) {
		return this.getZones().stream()
				.anyMatch(zone -> zone == aZone); // TODO: implementar equals para ZoneCoverage
	}

	private boolean isInListeners(ZoneCoverage aZone) {
		return this.listenerZones.stream()
				.anyMatch(zone -> zone == aZone); // TODO: implementar equals para ZoneCoverage
	}

	// Nota: No se usa interfaz ya que el sistema es único.
	public void Attach(ZoneCoverage aZone) {
		if (! this.isInListeners(aZone)) {
			this.listenerZones.add(aZone);
		}
	}

	public void Detach(ZoneCoverage aZone) {
		this.listenerZones.removeIf(zone -> zone == aZone);
	}

	public void Notify(Sample aSample) {
		this.listenerZones.stream().forEach(aZone -> aZone.Update(aSample));
	}

	// Quizás no se necesitan ya que se pasa el cambio por parámetro en el Notify.
	public void GetState() {}
	public void SetState() {}
	public void subjectState() {}

}
