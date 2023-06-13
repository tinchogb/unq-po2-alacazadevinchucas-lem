package model;

import java.util.List;
import java.util.ArrayList;

public class ZoneCoverage implements IZoneCoverage{

	private System 		system = System.getInstance("systemLEM");
	private String 	 	name;
	private Location 	epicenter;
	private Integer  	radiusInKm;

	private List<IOrganization> organizations = new ArrayList<>();

	// Constructor for testing purpose only 
	public ZoneCoverage(String name, Location epicenter, Integer radiusInKm, List<IOrganization> orgs) {
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
		this.organizations	= orgs;
	}	

	// Real constructor
	public ZoneCoverage(String name, Location epicenter, Integer radiusInKm) {
		// TODO Auto-generated constructor stub
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
	}

	@Override
	public void Attach(IOrganization anOrg) {
		if (! this.isIn(anOrg)) {
			this.organizations.add(anOrg);
		}
	}
	private boolean isIn(IOrganization anOrg) {
		// TODO Auto-generated method stub
		return this.organizations.stream().anyMatch(org -> org == anOrg); // TODO: implementar equals para IOrg
	}

	@Override
	public void Detach(IOrganization anOrg) {
		List<IOrganization> newOrgs = this.organizations.stream().filter(org -> org != anOrg).toList();
		this.organizations = newOrgs;
	}

	@Override
	public void Notify(Sample aSample) {
		this.organizations.stream().forEach(org -> org.Update(this, aSample));
	}

	// Quizás no se necesitan ya que se pasa el cambio por parámetro en el Notify.
	public void GetState() {};
	public void SetState() {};
	public void subjectState() {};

	public void Update(Sample aSample) { // ZoneCoverage es intermediario en el cambio de estado.
		this.Notify(aSample);
	}
	
	public System 	getSystem() 		{ return system; }
	public String 	getName() 			{ return name; }
	public Location getEpicenter() 		{ return epicenter; }
	public Integer 	getRadiusInKm() 	{ return radiusInKm; }

	public List<Sample> samplesInZone() {
		// TODO Auto-generated method stub
		return this.system.getSamples().stream()
					.filter(sample -> this.inZone(sample.getLocation()))
					.toList();
	}

	private boolean inZone(Location location) {
		// TODO Auto-generated method stub
		return this.epicenter.distance(location) <= this.radiusInKm;
	}


	public List<ZoneCoverage> intersectionZones() {
		// TODO Auto-generated method stub
		return null;
	}

}
