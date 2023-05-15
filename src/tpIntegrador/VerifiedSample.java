package tpIntegrador;

public class VerifiedSample implements SampleState {

	public void saveOpinion(Opinion opinion,Sample sample) {
		throw new RuntimeException ("No se puede opinar, la muestra ya está verificada");
	}
	public Opinion getCurrentResult(Sample sample) {
		return null;
		
	}

		
	}
}
