package model;

public interface SampleState {
	
	public void saveOpinion(Opinion opinion, Sample sample);

	public Answer getCurrentResult(Sample sample);
	
	public boolean isValidated();
	
	
}
