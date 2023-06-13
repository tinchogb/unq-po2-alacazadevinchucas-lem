package model;

public interface SampleState {
	
	public void saveOpinion(Opinion opinion, Sample sample);

	public void getCurrrentResult(Sample sample);
	
	public boolean isValidated();
	
	
}
