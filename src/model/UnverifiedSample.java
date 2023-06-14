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
		if (opinion.getIsExpertOpinion()) {
			sample.changeSampleState(nextState);
		}
	}
	
	@Override
	public Answer getCurrentResult(Sample sample) {

		return sample.calculateResult(sample.basicOpinions());
			
	}
	
	public boolean isValidated() {
		return false;
	}
			
	
}
