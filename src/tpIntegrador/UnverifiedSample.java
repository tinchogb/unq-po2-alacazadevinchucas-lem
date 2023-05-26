package tpIntegrador;

public class UnverifiedSample implements SampleState{
	
	public void saveOpinion(Opinion opinion,Sample sample) {
		sample.addOpinion(opinion);
	}
	public Opinion getCurrentResult(Sample sample) {
		return null;
		
	}
}
