package model;

import model.Location;
import model.OrganizationType;
import model.ZoneCoverage;
import model.ExternalFunctionality;

import java.util.List;

public class Organization {

	public  OrganizationType		type;
	private List<ZoneCoverage> 		registeredZones;
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
