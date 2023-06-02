package model;


public class UnverifiedSample implements SampleState{
	
	public VerifiedPartialSample nextState = new VerifiedPartialSample();
	
	public VerifiedPartialSample getNextState() {
		return nextState; 
	}
	
	public void saveOpinion(Opinion opinion,Sample sample) {
		sample.addOpinion(opinion);
		this.verifyStatusChange(opinion,sample);
	}
	private void verifyStatusChange(Opinion opinion, Sample sample) {
		if (sample.mustChangeState()) {
			sample.changeSampleState(nextState);
		}
	}
	
	@Override
	public void getCurrrentResult(Sample sample) {

		sample.calculateResult(sample.basicOpinions());
			
	}
			
	
}
