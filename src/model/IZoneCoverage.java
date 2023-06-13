package model;

import java.util.List;
import model.Sample;

public interface IZoneCoverage {

	public void Attach(Organization anOrg);
	public void Detach(Organization anOrg);
	public void Notify(Sample aSample);
}
