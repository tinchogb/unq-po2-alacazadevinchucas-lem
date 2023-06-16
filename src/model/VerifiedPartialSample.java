package model;

import java.util.stream.Stream;

public class VerifiedPartialSample implements SampleState{
	

	@Override
	public void saveOpinion(Opinion opinion, Sample sample)throws RuntimeException {
		if (opinion.getIsExpertOpinion()) {
		sample.addOpinion(opinion);	
		this.verifyStatusChange(opinion,sample);
		}
		else {
			throw new RuntimeException ("El usuario no está habilitado a comentar en esta muestra");
		}	
	}
	
	void verifyStatusChange(Opinion opinion, Sample sample) {

		Stream <Opinion> sOpinions = sample.getOpinions().stream();
		if (sample.calculateResult(sample.expertsOpinions()) == opinion.getType()) {
			sample.changeSampleState(new VerifiedSample());
		}
		
		else if (sOpinions.filter(o -> o.getIsExpertOpinion())
				 .map(o -> o.getType())
				 .anyMatch(o -> o == opinion.getType())){
			sample.changeSampleState(new VerifiedSample());
		}
	}


	@Override
	public Answer getCurrentResult(Sample sample) {
		
		return sample.calculateResult(sample.expertsOpinions());
	}
	
	public boolean isValidated() {
		return false;
	}
	
	 @Override // Para poder testear
	    public boolean equals(Object o) {
	        return this.getClass().getName() == o.getClass().getName();
	 }
}
