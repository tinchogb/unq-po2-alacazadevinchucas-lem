package model;

import java.time.LocalDate;
import java.util.List;

public class LastVerificationDate implements IQuery {

	private LocalDate		date;
	private List<Sample>	samples;

	public LastVerificationDate(LocalDate aDate, List<Sample> aSamples) {
		this.date 	 = aDate;
		this.samples = aSamples;
	}

	@Override
	public List<Sample> filter() {
		return this.samples.stream()
				.filter(s -> s.getLastOpinion().getDate().isAfter(date))
				.toList();
	}

}
