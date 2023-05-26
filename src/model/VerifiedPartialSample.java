package model;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VerifiedPartialSample implements SampleState{
	
	public VerifiedSample nextState = new VerifiedSample();
	
	 
	
	public VerifiedSample getSiguiente() {
		return nextState;
	}

	@Override
	public void saveOpinion(Opinion opinion, Sample sample)throws RuntimeException {
		if (opinion.ableToCommentInPartialVerified()) {
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
			sample.changeSampleState(nextState);
		}
		
		else if (sOpinions.filter(o -> o.mustChangeState())
				 .map(o -> o.getType())
				 .anyMatch(o -> o == opinion.getType())){
			sample.changeSampleState(nextState);
		}
	}


	@Override
	public void getCurrrentResult(Sample sample) {
		
		sample.calculateResult(sample.expertsOpinions());
	}
	
}
