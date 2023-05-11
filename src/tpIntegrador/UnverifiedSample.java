package tpIntegrador;

public class UnverifiedSample implements SampleState{
	
	public void saveOpinion(Opinion opinion,Sample sample) {
		sample.addOpinion(opinion);
	}
}
