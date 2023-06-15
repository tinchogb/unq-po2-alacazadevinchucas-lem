package model;

import java.util.List;
import java.util.ArrayList;

public class Organization implements IOrganization {

	
	private List<ZoneCoverage> 		registeredZones;
	public List<Empleado>			empleados = new ArrayList<Empleado>();				

	public  OrganizationType		type;
	public  Location 				location;
	private ExternalFunctionality 	pluginSample;
	private ExternalFunctionality 	pluginValidation;

	public Organization(OrganizationType type, List<ZoneCoverage> registeredZones, Location location,
		ExternalFunctionality pluginSample, ExternalFunctionality pluginValidation, List<Empleado> empleados) {
		this.type				= type;
		this.registeredZones 	= registeredZones;
		this.location 			= location;
		this.pluginSample 		= pluginSample;
		this.pluginValidation 	= pluginValidation;
		this.empleados 			= empleados;
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
		return this.empleados.size();
	}

	private void newSampleExtFunc(ZoneCoverage zone, Sample sample) {
		pluginSample.newEvent(this, zone, sample);
	}

	private void newValidationExtFunc(ZoneCoverage zone, Sample sample) {
		pluginValidation.newEvent(this, zone, sample);
	}


}
