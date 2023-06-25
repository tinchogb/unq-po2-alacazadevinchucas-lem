package model;

import java.util.List;

public class VerificationLevel implements IQuery {
	
	private SampleState		level;
	private List<Sample>	samples;

	public VerificationLevel(SampleState aLevel, List<Sample> aSamples) {
		this.level 	 = aLevel;
		this.samples = aSamples;
	}

	@Override
	public List<Sample> filter() {
		return this.samples.stream()
				.filter(s -> s.getState().equals(this.level))
				.toList();
	}
}
