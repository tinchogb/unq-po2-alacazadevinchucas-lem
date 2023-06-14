package model;

import java.util.List;

public class Organization implements IOrganization {

	
	private List<ZoneCoverage> 		registeredZones;

	public  OrganizationType		type;
	private Location 				location;
	private ExternalFunctionality 	pluginSample;
	private ExternalFunctionality 	pluginValidation;

	public Organization(OrganizationType type, List<ZoneCoverage> registeredZones, Location location,
			ExternalFunctionality pluginSample, ExternalFunctionality pluginValidation) {
		// TODO Auto-generated constructor stub
		this.type				= type;
		this.registeredZones 	= registeredZones;
		this.location 			= location;
		this.pluginSample 		= pluginSample;
		this.pluginValidation 	= pluginValidation;
	}


	public OrganizationType   getType() 		   { return type; }
	public List<ZoneCoverage> getRegisteredZones() { return registeredZones; }
	public Location 		  getLocation() 	   { return location; }

	@Override
	public void Update(ZoneCoverage aZone, Sample aSample) {
		if (aSample.isValidated()) { this.newValidationExtFunc(aZone, aSample); }
		else					   { this.newSampleExtFunc(aZone, aSample); }
	}

	public Integer nbCurrentWorkers() {
		// TODO Auto-generated method stub
		return null;
	}

	private void newSampleExtFunc(ZoneCoverage zone, Sample sample) {
		// TODO Auto-generated method stub
		pluginSample.newEvent(this, zone, sample);
	}

	private void newValidationExtFunc(ZoneCoverage zone, Sample sample) {
		// TODO Auto-generated method stub
		pluginValidation.newEvent(this, zone, sample);
	}


}
