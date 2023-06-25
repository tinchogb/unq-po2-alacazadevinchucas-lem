package model;

import java.util.List;

public class InsectType implements IQuery {
	
	private OpinionType		type;
	private List<Sample>	samples;

	public InsectType(OpinionType anOpinionType, List<Sample> aSamples) {
		this.type 	 = anOpinionType;
		this.samples = aSamples;
	}

	@Override
	public List<Sample> filter() {
		return this.samples.stream()
				.filter(s -> s.getCurrentResult().equals(this.type))
				.toList();
	}

}
