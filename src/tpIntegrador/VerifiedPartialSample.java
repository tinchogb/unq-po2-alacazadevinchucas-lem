package tpIntegrador;

public class VerifiedPartialSample implements SampleState{

	@Override
	public void saveOpinion(Opinion opinion, Sample sample) {
		sample.addOpinion(opinion);	
	}
	public Opinion getCurrentResult(Sample sample) {
		
	}

}
