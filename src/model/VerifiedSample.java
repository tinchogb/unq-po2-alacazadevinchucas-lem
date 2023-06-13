package model;


public class VerifiedSample implements SampleState {
	
	public void saveOpinion(Opinion opinion,Sample sample) {
		throw new RuntimeException ("No se puede opinar, la muestra ya está verificada");
	}
	
	@Override
	public void getCurrrentResult(Sample sample) {
		sample.calculateResult(sample.expertsOpinions());
	} 
	
	public boolean isValidated() {
		return true;
	}
}

