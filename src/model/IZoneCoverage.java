package model;

import java.util.List;
import model.Sample;

public interface IZoneCoverage {

	public void Attach(IOrganization anOrg);
	public void Detach(IOrganization anOrg);
	public void Notify(Sample aSample);
}
