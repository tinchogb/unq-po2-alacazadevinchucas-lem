package model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collector;

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
		if (opinion.mustChangeState()) {
			sample.changeSampleState(nextState);
		}
	}
	
	@Override
	public void getCurrrentResult(Sample sample) {

		sample.calculateResult(sample.basicOpinions());
			
	}
			
	
}
